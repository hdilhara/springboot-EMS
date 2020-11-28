package com.hdilhara.clientserver.controller;

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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
        Token token = restTemplate.postForObject(tokenUri,request,Token.class);
        System.out.println("Token: "+token.getAccess_token());
        System.out.println("Type: "+token.getToken_type());
        System.out.println("Expires in: "+token.getExpires_in());
        response.setHeader("Location", homePageUrl);
        response.setStatus(302);
        response.setHeader("Set-Cookie", "token="+token.getAccess_token()
        			+";type="+token.getToken_type()+";expires_in="+token.getExpires_in());
	}
	
	@ResponseBody
	@GetMapping("/user")
	public User getUserData(@RequestParam String token) {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject("http://localhost:9006/oauth/check_token?token="+token, User.class);
	}
	
	@ResponseBody
	@GetMapping("/logout")
	public void logOut(HttpSession session,HttpServletResponse response) {
		System.out.println("////////////");
//		System.out.println(session.);
//		session.invalidate();
		HttpHeaders headers = new HttpHeaders();
		RestTemplate rt = new RestTemplate();
	
		
		String str = rt.postForObject("http://localhost:9006/logout", new HttpEntity<>( headers ), String.class);//("http://localhost:9006/login?logout", String.class);

		System.out.println(str);
		//		System.out.println(rt.getForObject("http://localhost:9006/login?logout", String.class));
//		Clear-Site-Data: "cache", "cookies", "storage", "executionContexts"
	}
	
//	@GetMapping("/getToken")
//    public Map<String,String> getToken(@RequestParam("code") String code){
//        String clientId = "59d0733b7f0d1b5be95e";
//        String clientSecret = "d1527c200b67324e180d04ea4b809e7b3638bcf1";
//        String tokenUri = "https://github.com/login/oauth/access_token";
//        String authorizationUri = "https://www.github.com/login/oauth/authorize";
//        String redirectUri = "http://localhost:3000/login";
//        String result;
//        HttpHeaders headers = new HttpHeaders();
//        RestTemplate restTemplate = new RestTemplate();
//        Map<String,String> jsonObject = new HashMap<>();
//
//
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        jsonObject.put("client_id",clientId);
//        jsonObject.put("client_secret",clientSecret);
//        jsonObject.put("code", code);
//        jsonObject.put("redirect_uri",redirectUri);
//
//        HttpEntity<Map<String,String>> request = new HttpEntity<>(jsonObject,headers);
//        result = restTemplate.postForObject(tokenUri,request,String.class);
//
//        String[] res = new String[5];
//        res = result.split("&");
//        String[] token = res[0].split("=");
//
//        Map<String,String> resultJson = new HashMap<>();
//        resultJson.put("token", token[1]);
//
//        System.out.println(result);
//
//        return resultJson;
//    }
	
}
