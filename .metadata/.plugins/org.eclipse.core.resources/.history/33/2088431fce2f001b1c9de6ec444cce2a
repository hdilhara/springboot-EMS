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

import com.hdilhara.clientserver.model.Project;
import com.hdilhara.clientserver.model.Task;

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
	
	
}
