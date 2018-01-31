package com.firrael.test;

import com.firrael.base.response.AddResponse;
import com.firrael.base.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class TestController {

    @Autowired
    private TestUserRepository testUserRepository;

    @RequestMapping(path = "/add")
    public @ResponseBody
    AddResponse addNewUser(@RequestParam String name,
                           @RequestParam String token,
                           @RequestParam(defaultValue = "") String application) {

        TestUser n = new TestUser(name, token, application);
        testUserRepository.save(n);

        AddResponse response = new AddResponse();
        response.setToken(token);
        return response;
    }

    @RequestMapping(path = "/findUserByToken")
    public @ResponseBody
    UserResponse findUserByToken(@RequestParam String token) {
        TestUser user = testUserRepository.findByToken(token);
        UserResponse response = new UserResponse();
        if (user != null) {
            response.setUser(user);
        } else {
            response.setError("No user found.");
        }

        return response;
    }

    @RequestMapping(path = "/all")
    public @ResponseBody
    Iterable<TestUser> getAllUsers() {
        return testUserRepository.findAll();
    }
}