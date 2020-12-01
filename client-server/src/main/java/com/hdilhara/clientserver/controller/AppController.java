package com.hdilhara.clientserver.controller;

import java.security.Principal;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.WebUtils;

import com.hdilhara.clientserver.model.Token;
import com.hdilhara.clientserver.model.User;

@CrossOrigin
@Controller
public class AppController {

	@Value("${webapp.home.url}")
	private String homePageUrl;
	
	@GetMapping("/")
	public String home(HttpServletResponse response, HttpServletRequest request) {
		System.out.println(request.getCookies()[0].getName());
		response.addCookie(new Cookie("Thilina", "Dilhara"));
		return "home";
	}
	
	
	@GetMapping("/login")
	public void getTokenByCode( @RequestParam("code") String code, HttpServletResponse response ) {
		String clientId = "client";
        String clientSecret = "secret";
        String tokenUri = "http://localhost:9006/oauth/token";
        String authorizationUri = "http://localhost:9006/oauth/authorize";
        String redirectUri = "http://localhost:9000/login";		
		HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        
        MultiValueMap<String,String> jsonObject = new LinkedMultiValueMap<>();
        
        String auth = clientId + ":" + clientSecret;
        String authHeader = "Basic " +Base64.getEncoder().encodeToString(auth.getBytes());
        headers.set( "Authorization", authHeader);

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        jsonObject.add("code", code);
        jsonObject.add("redirect_uri",redirectUri);
        jsonObject.add("grant_type","authorization_code");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(jsonObject,headers);
//        Token token = restTemplate.postForObject(tokenUri,request,Token.class);
        ResponseEntity<Token> re = restTemplate.postForEntity(tokenUri, request,Token.class);
        Token token = re.getBody(); 
        System.out.println("Token: "+token.getAccess_token());
        System.out.println("Type: "+token.getToken_type());
        System.out.println("Expires in: "+token.getExpires_in());
        System.out.println("Expires in: "+re.getHeaders());
        System.out.println("Expires in: "+re.getHeaders());
        
        response.setHeader("Location", homePageUrl+"?message=You have logout successfully");
        response.setStatus(302);
        response.setHeader("Set-Cookie", "token="+token.getAccess_token()
        			+";type="+token.getToken_type()+";expires_in="+token.getExpires_in());
	}
	
	@ResponseBody
	@GetMapping("/user")
	public String getUserData(@RequestParam String token, HttpServletResponse res) {
		RestTemplate restTemplate = new RestTemplate();
//		return restTemplate.getForObject("http://localhost:9006/oauth/check_token?token="+token, User.class);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+token);
		ResponseEntity<String> principal = restTemplate.exchange("http://localhost:9006/user/me",HttpMethod.GET,new HttpEntity(headers),String.class);
		System.out.println(principal);
		User user = new User();
		user.setUser_name("User");
		user.setAuthorities(new String[]{"ADMIN"});
		return principal.getBody();
	}


	
}
