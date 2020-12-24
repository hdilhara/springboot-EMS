package com.hdilhara.clientservice.controller;

import com.hdilhara.clientservice.model.Project;
import com.hdilhara.clientservice.model.Project;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/project")
public class ProjectController {

	
	@Value("${project.service.url}")
	public String projectServiceURL; 
	
	public void setAuthorizationHeader(HttpServletRequest request, HttpHeaders headers ) {
		try {
			headers.set("Authorization", request.getHeader("Authorization") );
		}catch (NullPointerException e) {
			//log error
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Project> getProject(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		System.out.println(projectServiceURL);
		RestTemplate rt = new RestTemplate();
		try {
			HttpHeaders headers =new HttpHeaders();
			setAuthorizationHeader(request, headers);
			return rt.exchange(projectServiceURL+"/project/"+id, HttpMethod.GET, new HttpEntity<Project>(headers), Project.class);//exchange(projectServiceURL, HttpMethod.GET, new Request, responseType)
		}
		catch(HttpClientErrorException e) {
			response.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Project>> getProjects(HttpServletRequest request, HttpServletResponse response) {
		RestTemplate rt = new RestTemplate();
		try {
			HttpHeaders headers =new HttpHeaders();
			setAuthorizationHeader(request, headers);
			return rt.exchange(projectServiceURL+"/project/", HttpMethod.GET, new HttpEntity<Project>(headers), new ParameterizedTypeReference<List<Project>>(){});//exchange(projectServiceURL, HttpMethod.GET, new Request, responseType)
		}
		catch(HttpClientErrorException e) {
			response.setStatus(404);
			return null;
		}
	}
	@GetMapping("/ids/{ids}")
	public ResponseEntity<List<Project>> getProjectsByIds(@PathVariable String ids, HttpServletRequest request, HttpServletResponse response) {
		RestTemplate rt = new RestTemplate();
		try {
			HttpHeaders headers =new HttpHeaders();
			setAuthorizationHeader(request, headers);
			return rt.exchange(projectServiceURL+"/project/ids/"+ids, HttpMethod.GET, new HttpEntity<Project>(headers), new ParameterizedTypeReference<List<Project>>(){});//exchange(projectServiceURL, HttpMethod.GET, new Request, responseType)
		}
		catch(HttpClientErrorException e) {
			response.setStatus(404);
			return null;
		}
	}
	@PostMapping("/")
	public ResponseEntity<Project> createProject(@RequestBody Project project, HttpServletRequest request, HttpServletResponse response){
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		setAuthorizationHeader(request, headers);
		HttpEntity<Project> he = new HttpEntity<Project>(project,headers);
		try {
			return rt.exchange(projectServiceURL+"/project/", HttpMethod.POST, he, Project.class);//exchange(employeeServiceURL, HttpMethod.GET, new Request, responseType)
		}
		catch(HttpClientErrorException e) {
			response.setStatus(404);
			return null;
		}	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Project> deleteProject(@PathVariable int id, HttpServletRequest request, HttpServletResponse response){
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		setAuthorizationHeader(request, headers);
		ResponseEntity<Project> res;
		HttpEntity<Project> he = new HttpEntity<Project>(headers);
		try {
			return rt.exchange(projectServiceURL+"/project/"+id, HttpMethod.DELETE, he, Project.class);
		}
		catch(HttpClientErrorException e) {
			res = new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
			return res;
		}	
	}
	
	@PutMapping("/")
	public ResponseEntity<Project> updateProject(@RequestBody Project emp, HttpServletRequest request, HttpServletResponse response){
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		setAuthorizationHeader(request, headers);
		HttpEntity<Project> he = new HttpEntity<Project>(emp,headers);
		try {
			return rt.exchange(projectServiceURL+"/project/", HttpMethod.PUT, he, Project.class);//exchange(employeeServiceURL, HttpMethod.GET, new Request, responseType)
		}
		catch(HttpClientErrorException e) {
			return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
		}
	}
	
}
