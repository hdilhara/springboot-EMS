package com.hdilhara.authorizationserver;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
public class UserController {
	 @GetMapping("/user/me")
	    public Principal user(Principal principal) {
	        return principal;
	    }
	 
	   @RequestMapping(value="/logout/me", method=RequestMethod.GET)  
	    public void logoutPage(HttpServletRequest request, HttpServletResponse response) {
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();  
	        if (auth != null){      
	           new SecurityContextLogoutHandler().logout(request, response, auth);  
	        }  
	        response.setHeader("Location", "http://localhost:3000?message=You have logout");
	        response.setStatus(302);
	     }  
}
