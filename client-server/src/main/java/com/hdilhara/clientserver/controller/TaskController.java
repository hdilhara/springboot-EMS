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
import com.hdilhara.clientserver.model.Task;

@CrossOrigin
@RestController
@RequestMapping("task")
public class TaskController {

	
	@Value("${task.service.url}")
	public String taskServiceURL; 
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getProject(@PathVariable int id, HttpServletResponse response) {
		RestTemplate rt = new RestTemplate();
		try {
			return rt.exchange(taskServiceURL+"/task/"+id, HttpMethod.GET, new HttpEntity<Task>(new HttpHeaders()), Task.class);//exchange(projectServiceURL, HttpMethod.GET, new Request, responseType)
		}
		catch(HttpClientErrorException e) {
			response.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Task>> getProjects( HttpServletResponse response) {
		RestTemplate rt = new RestTemplate();
		try {
			return rt.exchange(taskServiceURL+"/task/", HttpMethod.GET, new HttpEntity<Task>(new HttpHeaders()), new ParameterizedTypeReference<List<Task>>(){});//exchange(projectServiceURL, HttpMethod.GET, new Request, responseType)
		}
		catch(HttpClientErrorException e) {
			response.setStatus(404);
			return null;
		}
	}
	@PostMapping("/")
	public ResponseEntity<Task> createTask(@RequestBody Task task, HttpServletResponse response){
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);		
		HttpEntity<Task> he = new HttpEntity<Task>(task,headers);
		try {
			return rt.exchange(taskServiceURL+"/task/", HttpMethod.POST, he, Task.class);//exchange(employeeServiceURL, HttpMethod.GET, new Request, responseType)
		}
		catch(HttpClientErrorException e) {
			response.setStatus(404);
			return null;
		}	
	}
	
	@DeleteMapping("/{id}")
	public  ResponseEntity<Task> deleteTask(@PathVariable int id, HttpServletResponse response){
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<Task> res;
		HttpEntity<Task> he = new HttpEntity<Task>(headers);
		try {
			return rt.exchange(taskServiceURL+"/task/"+id, HttpMethod.DELETE, he, Task.class);
		}
		catch(HttpClientErrorException e) {
			res = new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
			return res;
		}	
	}
	
	@PutMapping("/")
	public ResponseEntity<Task> updateTask(@RequestBody Task emp, HttpServletResponse response){
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);		
		HttpEntity<Task> he = new HttpEntity<Task>(emp,headers);
		try {
			return rt.exchange(taskServiceURL+"/task/", HttpMethod.PUT, he, Task.class);//exchange(employeeServiceURL, HttpMethod.GET, new Request, responseType)
		}
		catch(HttpClientErrorException e) {
			return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
		}
	}
	
}
