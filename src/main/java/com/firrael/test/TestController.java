package com.firrael.test;

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
    String addNewUser(@RequestParam String name,
                      @RequestParam String token,
                      @RequestParam(defaultValue = "") String application) {

        TestUser n = new TestUser(name, token, application);
        testUserRepository.save(n);

        return token;
    }

    @RequestMapping(path = "/findUserByToken")
    public @ResponseBody
    TestUser findUserByToken(@RequestParam String token) {
        TestUser user = testUserRepository.findByToken(token);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }
}