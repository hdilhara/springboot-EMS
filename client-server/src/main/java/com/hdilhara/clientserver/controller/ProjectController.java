package com.hdilhara.clientserver.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.hdilhara.clientserver.model.Employee;
import com.hdilhara.clientserver.model.Project;

@RestController
@RequestMapping("/project")
public class ProjectController {

	
	@Value("${project.service.url}")
	public String projectServiceURL; 
	
	@GetMapping("/{id}")
	public ResponseEntity<Project> getProject(@PathVariable int id, HttpServletResponse response) {
		System.out.println(projectServiceURL);
		RestTemplate rt = new RestTemplate();
		try {
			return rt.exchange(projectServiceURL+"/project/"+id, HttpMethod.GET, new HttpEntity<Project>(new HttpHeaders()), Project.class);//exchange(projectServiceURL, HttpMethod.GET, new Request, responseType)
		}
		catch(HttpClientErrorException e) {
			response.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Project>> getProjects( HttpServletResponse response) {
		RestTemplate rt = new RestTemplate();
		try {
			return rt.exchange(projectServiceURL+"/project/", HttpMethod.GET, new HttpEntity<Project>(new HttpHeaders()), new ParameterizedTypeReference<List<Project>>(){});//exchange(projectServiceURL, HttpMethod.GET, new Request, responseType)
		}
		catch(HttpClientErrorException e) {
			response.setStatus(404);
			return null;
		}
	}
	
	
}
