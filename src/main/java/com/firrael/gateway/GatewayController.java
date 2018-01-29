package com.firrael.gateway;

import com.firrael.base.response.AddResponse;
import com.firrael.base.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class GatewayController {

    private final GatewayUserRepository gatewayUserRepository;

    @Autowired
    public GatewayController(GatewayUserRepository gatewayUserRepository) {
        this.gatewayUserRepository = gatewayUserRepository;
    }

    @RequestMapping(path = "/add")
    public @ResponseBody
    AddResponse addNewUser(@RequestParam String name,
                      @RequestParam String token,
                      @RequestParam(defaultValue = "") String application) {

        GatewayUser n = new GatewayUser(name, token, application);
        gatewayUserRepository.save(n);

        AddResponse response = new AddResponse();
        response.setToken(token);
        return response;
    }

    @RequestMapping(path = "/all")
    public @ResponseBody
    Iterable<GatewayUser> getAllUsers() {
        return gatewayUserRepository.findAll();
    }

    @RequestMapping(path = "/findUserByToken")
    public @ResponseBody
    UserResponse findUserByToken(@RequestParam String token) {
        GatewayUser user = gatewayUserRepository.findByToken(token);
        UserResponse response = new UserResponse();
        if (user != null) {
            response.setUser(user);
        } else {
            response.setError("No user found.");
        }

        return response;
    }
}