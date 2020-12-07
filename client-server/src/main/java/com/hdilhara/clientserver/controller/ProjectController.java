package com.hdilhara.clientserver.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.hdilhara.clientserver.model.Project;

@CrossOrigin
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
	@GetMapping("/ids/{ids}")
	public ResponseEntity<List<Project>> getProjectsByIds( @PathVariable String ids, HttpServletResponse response) {
		RestTemplate rt = new RestTemplate();
		try {
			return rt.exchange(projectServiceURL+"/project/ids/"+ids, HttpMethod.GET, new HttpEntity<Project>(new HttpHeaders()), new ParameterizedTypeReference<List<Project>>(){});//exchange(projectServiceURL, HttpMethod.GET, new Request, responseType)
		}
		catch(HttpClientErrorException e) {
			response.setStatus(404);
			return null;
		}
	}
	@PostMapping("/")
	public ResponseEntity<Project> createProject(@RequestBody Project project, HttpServletResponse response){
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);		
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
	public  ResponseEntity<Project> deleteProject(@PathVariable int id, HttpServletResponse response){
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
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
	public ResponseEntity<Project> updateProject(@RequestBody Project emp, HttpServletResponse response){
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);		
		HttpEntity<Project> he = new HttpEntity<Project>(emp,headers);
		try {
			return rt.exchange(projectServiceURL+"/project/", HttpMethod.PUT, he, Project.class);//exchange(employeeServiceURL, HttpMethod.GET, new Request, responseType)
		}
		catch(HttpClientErrorException e) {
			return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
		}
	}
	
}
