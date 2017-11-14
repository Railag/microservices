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
    private TokenUserRepository tokenUserRepository;

    @Autowired TokenAppInfoRepository tokenAppInfoRepository;

    @RequestMapping(path = "/add")
    public @ResponseBody
    String addNewUser(@RequestParam String name,
                      @RequestParam String password,
                      @RequestParam(defaultValue = "") String application) {

        String token = generateToken(password);

        if (application.equalsIgnoreCase("token")) {
            TokenUser n = new TokenUser(name, token, application);
            tokenUserRepository.save(n);
        } else {
            RestTemplate template = new RestTemplate();
            String response = template.getForObject(GATEWAY_HOST + application + "/add?name=" + name + "&token=" + token + "&application=" + application, String.class);

            TokenAppInfo tokenAppInfo = new TokenAppInfo(token, application);
            tokenAppInfoRepository.save(tokenAppInfo);
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
    Iterable<TokenUser> getAllUsers() {
        return tokenUserRepository.findAll();
    }

    @RequestMapping(path = "/findApplicationByToken")
    public @ResponseBody
    String findApplicationByToken(@RequestParam String token) {
        TokenAppInfo info = tokenAppInfoRepository.findByToken(token);
        if (info != null) {
            return info.getApplication();
        } else {
            return "";
        }
    }

    @RequestMapping(path = "/findUserByToken")
    public @ResponseBody
    User findUserByToken(@RequestParam String token) {
        User user = tokenUserRepository.findByToken(token);
        if (user != null) {
            return user;
        } else {
            return null;
        }
    }
}