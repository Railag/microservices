package com.firrael.gateway;

import com.firrael.base.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class GatewayController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/add")
    public @ResponseBody
    String addNewUser(@RequestParam String name,
                      @RequestParam String token,
                      @RequestParam(defaultValue = "") String application) {

        User n = new User(name, token, application);
        userRepository.save(n);

        return token;
    }

    @RequestMapping(path = "/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
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