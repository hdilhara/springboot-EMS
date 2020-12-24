package com.hdilhara.clientservice.service;

import com.hdilhara.clientservice.model.Token;
import com.hdilhara.clientservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class AppService {

    @Value("${webapp.home.url}")
    private String homePageUrl;
    @Value("${auth.service.url}")
    private String authServiceURL;
    @Value("${auth.service.redirect-url}")
    private String authServiceRedirectURL;

    @Autowired
    private RestTemplate restTemplate;


    public Token getToken(String code) {
        String clientId = "client";
        String clientSecret = "secret";
        String tokenUri = authServiceURL+"/oauth/token";
        String authorizationUri = authServiceURL+"/oauth/authorize";
        String redirectUri = authServiceRedirectURL;
        HttpHeaders headers = new HttpHeaders();
//        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String,String> jsonObject = new LinkedMultiValueMap<>();

        String auth = clientId + ":" + clientSecret;
        String authHeader = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());
        headers.set( "Authorization", authHeader);

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        jsonObject.add("code", code);
        jsonObject.add("redirect_uri",redirectUri);
        jsonObject.add("grant_type","authorization_code");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(jsonObject,headers);
//        Token token = restTemplate.postForObject(tokenUri,request,Token.class);
        ResponseEntity<Token> re = restTemplate.postForEntity(tokenUri, request,Token.class);
        return re.getBody();
    }

    public String getPrinciple(String token) {
//        RestTemplate restTemplate = new RestTemplate();
//		return restTemplate.getForObject("http://localhost:9006/oauth/check_token?token="+token, User.class);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer "+token);
        ResponseEntity<String> principal = restTemplate.exchange(authServiceURL+"/user/me", HttpMethod.GET,new HttpEntity(headers),String.class);
        System.out.println(principal);
        User user = new User();
        user.setUser_name("User");
        user.setAuthorities(new String[]{"ADMIN"});
        return principal.getBody();
    }
}
