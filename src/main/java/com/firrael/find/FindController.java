package com.firrael.find;

import com.firrael.base.Group;
import com.firrael.base.SimpleUserRepository;
import com.firrael.base.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.firrael.base.Constants.GATEWAY_HOST;

@RestController
@RequestMapping(path = "/")
public class FindController {

    private final SimpleUserRepository findUserRepository;

    @Autowired
    public FindController(SimpleUserRepository findUserRepository) {
        this.findUserRepository = findUserRepository;
    }

    @RequestMapping(path = "/add")
    public @ResponseBody
    String addNewUser(@RequestParam String name,
                      @RequestParam String token,
                      @RequestParam(defaultValue = "") String application) {

        FindUser n = new FindUser(name, token, application);
        findUserRepository.save(n);

        return token;
    }

    @RequestMapping(path = "/findByToken")
    public @ResponseBody
    List<User> findUsersByToken(@RequestParam String token) {

        RestTemplate restTemplate = new RestTemplate();

        String application = restTemplate.getForObject(GATEWAY_HOST + "token/findApplicationByToken?token=" + token, String.class);
        if (application == null || application.isEmpty()) {
            return new ArrayList<>();
        }

        Group group = Group.findGroupOfApplication(application);

        List<User> users = new ArrayList<>();

        for (String app : group.getApplications()) {
            String query = GATEWAY_HOST + app + "/findUserByToken?token=" + token;
            System.out.println(query);
            User user = restTemplate.getForObject(query, User.class);
            if (user != null) {
                users.add(user);
            }
        }

        List<String> notifications = users
                .stream()
                .map(User::getNotifications)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());

        String result = notifications
                .stream()
                .reduce("", (str, str2) -> str + "\n" + str2);
        System.out.println(result);

        return users;
    }

    @RequestMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return findUserRepository.findAll();
    }

    @RequestMapping(path = "/findUserByToken")
    public @ResponseBody
    User findUserByToken(@RequestParam String token) {
        User user = findUserRepository.findByToken(token);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }
}