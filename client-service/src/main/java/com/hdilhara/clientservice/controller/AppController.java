package com.hdilhara.clientservice.controller;

import com.hdilhara.clientservice.model.Token;
import com.hdilhara.clientservice.model.User;
import com.hdilhara.clientservice.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

//@CrossOrigin
@Controller
public class AppController {

	@Autowired
	AppService service;

	@Value("${webapp.home.url}")
	private String homePageUrl;


//	@GetMapping("/")
//	public String home(HttpServletResponse response, HttpServletRequest request) {
//		System.out.println(request.getCookies()[0].getName());
//		response.addCookie(new Cookie("Thilina", "Dilhara"));
//		return "home";
//	}
	
	
	@GetMapping("/login")
	public void getTokenByCode(@RequestParam("code") String code, HttpServletResponse response ) {

        Token token = service.getToken(code);
//        System.out.println("Token: "+token.getAccess_token());
//        System.out.println("Type: "+token.getToken_type());
//        System.out.println("Expires in: "+token.getExpires_in());
//        System.out.println("Expires in: "+re.getHeaders());
//        System.out.println("Expires in: "+re.getHeaders());

        response.setHeader("Location", homePageUrl+"?token="+token.getAccess_token());
        response.setStatus(302);
//        response.setHeader("Set-Cookie", "token="+token.getAccess_token()
//        			+";type="+token.getToken_type()+";expires_in="+token.getExpires_in());
	}
	
	@ResponseBody
	@GetMapping("/user")
	public String getUserData(@RequestParam String token, HttpServletResponse res) {

		return service.getPrinciple(token);
	}


	
}
