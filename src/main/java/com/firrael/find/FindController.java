package com.firrael.find;

import com.firrael.base.Group;
import com.firrael.base.User;
import com.firrael.base.response.AddResponse;
import com.firrael.base.response.ApplicationResponse;
import com.firrael.base.response.FindByTokenResponse;
import com.firrael.base.response.UserResponse;
import com.sun.javaws.exceptions.InvalidArgumentException;
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

    private final FindUserRepository findUserRepository;

    @Autowired
    public FindController(UserRepository userRepository) {
        this.findUserRepository = new FindUserRepository(userRepository);
    }

    @RequestMapping(path = "/add")
    public @ResponseBody
    AddResponse addNewUser(@RequestParam String name,
                           @RequestParam String token,
                           @RequestParam(defaultValue = "") String application) {

        FindUser n = new FindUser(name, token, application);
        findUserRepository.save(n);


        AddResponse response = new AddResponse();
        response.setToken(token);
        return response;
    }

    @RequestMapping(path = "/findByToken")
    public @ResponseBody
    FindByTokenResponse findUsersByToken(@RequestParam String token) throws InvalidArgumentException {

        RestTemplate restTemplate = new RestTemplate();

        ApplicationResponse applicationResponse = restTemplate.getForObject(GATEWAY_HOST + "token/findApplicationByToken?token=" + token, ApplicationResponse.class);
        String application = applicationResponse.getApplication();
        if (application == null || application.isEmpty()) {
            throw new InvalidArgumentException(new String[]{"No application provided"});
        }

        Group group = Group.findGroupOfApplication(application);

        List<User> users = new ArrayList<>();

        for (String app : group.getApplications()) {
            String query = GATEWAY_HOST + app + "/findUserByToken?token=" + token;
            System.out.println(query);
            UserResponse userResponse = restTemplate.getForObject(query, UserResponse.class);
            User user = userResponse.getUser();
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

        FindByTokenResponse response = new FindByTokenResponse();

        if (!users.isEmpty()) {
            response.setUser(users.get(0));
            response.setMessages(notifications);
        } else {
            response.setError("No users found");
        }

        return response;
    }

    @RequestMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return findUserRepository.findAll();
    }

    @RequestMapping(path = "/findUserByToken")
    public @ResponseBody
    UserResponse findUserByToken(@RequestParam String token) {
        User user = findUserRepository.findUserByToken(token);
        UserResponse response = new UserResponse();
        if (user != null) {
            response.setUser(user);
            return response;
        } else {
            response.setError("User not found");
        }

        return response;
    }
}