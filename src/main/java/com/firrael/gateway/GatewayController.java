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
    private GatewayUserRepository gatewayUserRepository;

    @RequestMapping(path = "/add")
    public @ResponseBody
    String addNewUser(@RequestParam String name,
                      @RequestParam String token,
                      @RequestParam(defaultValue = "") String application) {

        GatewayUser n = new GatewayUser(name, token, application);
        gatewayUserRepository.save(n);

        return token;
    }

    @RequestMapping(path = "/all")
    public @ResponseBody
    Iterable<GatewayUser> getAllUsers() {
        return gatewayUserRepository.findAll();
    }

    @RequestMapping(path = "/findUserByToken")
    public @ResponseBody
    GatewayUser findUserByToken(@RequestParam String token) {
        GatewayUser user = gatewayUserRepository.findByToken(token);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }
}