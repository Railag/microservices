package com.firrael.token;

import com.firrael.base.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static com.firrael.base.Constants.GATEWAY_HOST;

@RestController
@RequestMapping(path = "/")
public class TokenController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/add")
    public @ResponseBody
    String addNewUser(@RequestParam String name,
                      @RequestParam String password,
                      @RequestParam(defaultValue = "") String application) {

        String token = generateToken(password);

        if (application.equalsIgnoreCase("token")) {
            User n = new User(name, token, application);
            userRepository.save(n);
        } else {
            RestTemplate template = new RestTemplate();
            String response = template.getForObject(GATEWAY_HOST + application + "/add?name=" + name + "&token=" + token + "&application=" + application, String.class);
            // TODO handle response
        }

        return token;
    }

    private String generateToken(String password) {
        String token = BCrypt.hashpw(password, BCrypt.gensalt(12));
        //    BCrypt.checkpw(password, token);
        return token;
    }

    @RequestMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(path = "/findApplicationByToken")
    public @ResponseBody
    String findApplicationByToken(@RequestParam String token) {
        User user = userRepository.findByToken(token);
        if (user != null) {
            return user.getApplication();
        } else {
            return "";
        }
    }

    @RequestMapping(path = "/findUserByToken")
    public @ResponseBody
    User findUserByToken(@RequestParam String token) {
        User user = userRepository.findByToken(token);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }
}