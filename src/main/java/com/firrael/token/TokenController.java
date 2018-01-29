package com.firrael.token;

import com.firrael.base.*;
import com.firrael.base.response.*;
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

    private final TokenUserRepository tokenUserRepository;

    private final TokenAppInfoRepository tokenAppInfoRepository;

    @Autowired
    public TokenController(TokenUserRepository tokenUserRepository, TokenAppInfoRepository tokenAppInfoRepository) {
        this.tokenUserRepository = tokenUserRepository;
        this.tokenAppInfoRepository = tokenAppInfoRepository;
    }

    @RequestMapping(path = "/add")
    public @ResponseBody
    TokenResponse addNewUser(@RequestParam String name,
                             @RequestParam String password,
                             @RequestParam(defaultValue = "") String application) {

        if (tokenAppInfoRepository.existsByUsername(name)) {
            TokenResponse response = new TokenResponse();
            response.setError("User already exists, try another name.");
            return response;
        }

        String token = generateToken(password);

        TokenResponse finalResponse = new TokenResponse();

        if (application.equalsIgnoreCase("token")) {
            TokenUser n = new TokenUser(name, token, application);
            tokenUserRepository.save(n);

            TokenAppInfo tokenAppInfo = new TokenAppInfo(name, token, application);
            tokenAppInfoRepository.save(tokenAppInfo);
            finalResponse.setToken(token);
        } else {
            RestTemplate template = new RestTemplate();
            AddResponse response = template.getForObject(GATEWAY_HOST + application + "/add?name=" + name + "&token=" + token + "&application=" + application,
                    AddResponse.class);

            if (response.hasErrors()) {
                finalResponse.setError(response.getError());
                return finalResponse;
            } else {
                TokenAppInfo tokenAppInfo = new TokenAppInfo(name, token, application);
                tokenAppInfoRepository.save(tokenAppInfo);
                finalResponse.setToken(token);
            }
        }

        return finalResponse;
    }

    private String generateToken(String password) {
        String token = BCrypt.hashpw(password, BCrypt.gensalt(12));
        return token;
    }

    @RequestMapping(path = "/all")
    public @ResponseBody
    Iterable<TokenUser> getAllUsers() {
        return tokenUserRepository.findAll();
    }

    @RequestMapping(path = "/findApplicationByToken")
    public @ResponseBody
    ApplicationResponse findApplicationByToken(@RequestParam String token) {
        TokenAppInfo info = tokenAppInfoRepository.findByToken(token);
        ApplicationResponse response = new ApplicationResponse();
        if (info != null) {
            response.setApplication(info.getApplication());
        } else {
            response.setError("No user found.");
        }

        return response;
    }

    @RequestMapping(path = "/findUserByToken")
    public @ResponseBody
    UserResponse findUserByToken(@RequestParam String token) {
        User user = tokenUserRepository.findByToken(token);
        UserResponse response = new UserResponse();
        if (user != null) {
            response.setUser(user);
        } else {
            response.setError("No user found.");
        }

        return response;
    }

    @RequestMapping(path = "/login")
    public @ResponseBody
    TokenResponse login(@RequestParam String username,
                        @RequestParam String password) {
        TokenAppInfo info = tokenAppInfoRepository.findByUsername(username);
        if (info == null) {
            TokenResponse response = new TokenResponse();
            response.setError("Invalid password");
            return response;
        }

        RestTemplate template = new RestTemplate();
        FindByTokenResponse response = template.getForObject(GATEWAY_HOST + "find" + "/findByToken?token=" + info.getToken(),
                FindByTokenResponse.class);

        TokenResponse finalResponse = new TokenResponse();

        if (response.hasErrors()) {
            finalResponse.setError(response.getError());
            return finalResponse;
        } else {
            User user = response.getUser();
            if (user != null) {
                boolean valid = checkPassword(user, password);
                if (valid) {
                    finalResponse.setToken(info.getToken());
                } else {
                    finalResponse.setError("Invalid password");
                }

                return finalResponse;
            }
        }


        return finalResponse;
    }

    private boolean checkPassword(User user, String password) {
        return BCrypt.checkpw(password, user.getToken());
    }
}