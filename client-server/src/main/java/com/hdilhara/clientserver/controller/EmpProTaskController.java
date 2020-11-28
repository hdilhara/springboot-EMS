package com.hdilhara.clientserver.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.hdilhara.clientserver.model.EmpProTask;
import com.hdilhara.clientserver.model.EmpProTaskId;
import com.hdilhara.clientserver.model.Employee;


@CrossOrigin
@RestController
@RequestMapping("/emp-pro-task")
public class EmpProTaskController {

	@Value("${employee.service.url}")
	private String empServiceURL;
	
	@GetMapping("/")
	public ResponseEntity<List<EmpProTask>> getEmpProTasks(HttpServletResponse response){
		List<EmpProTask> res = null;
		HttpHeaders headers = new HttpHeaders();
		RestTemplate rt = new RestTemplate();
		try {
			return rt.exchange(empServiceURL+"/emp-pro-task/", HttpMethod.GET, new HttpEntity<EmpProTask>(headers), new ParameterizedTypeReference<List<EmpProTask>>(){});
		}
		catch(HttpClientErrorException e) {
			response.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("/{eId}")
	public ResponseEntity<List<EmpProTask>> getEmpProTask( @PathVariable int eId, HttpServletResponse response){
		List<EmpProTask> res = null;
		HttpHeaders headers = new HttpHeaders();
		RestTemplate rt = new RestTemplate();
		try {
			return rt.exchange(empServiceURL+"/emp-pro-task/"+eId, HttpMethod.GET, new HttpEntity<EmpProTask>(headers), new ParameterizedTypeReference<List<EmpProTask>>(){});
		}
		catch(HttpClientErrorException e) {
			response.setStatus(404);
			return null;
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<EmpProTask> addEmpProTask(@RequestBody EmpProTaskId empProTaskId, HttpServletResponse response) {
		List<EmpProTask> res = null;
		HttpHeaders headers = new HttpHeaders();
		RestTemplate rt = new RestTemplate();

		try {
			return rt.exchange(empServiceURL+"/emp-pro-task/", HttpMethod.POST, new HttpEntity<EmpProTaskId>(empProTaskId,headers), EmpProTask.class);
		}
		catch(HttpClientErrorException e) {
			response.setStatus(404);
			return null;
		}		
	}
	@PostMapping("/remove")
	public ResponseEntity<EmpProTask> removeEmpProTask(@RequestBody EmpProTaskId empProTaskId, HttpServletResponse response) {
		List<EmpProTask> res = null;
		HttpHeaders headers = new HttpHeaders();
		RestTemplate rt = new RestTemplate();

		try {
			return rt.exchange(empServiceURL+"/emp-pro-task/remove/", HttpMethod.POST, new HttpEntity<EmpProTaskId>(empProTaskId,headers), EmpProTask.class);
		}
		catch(HttpClientErrorException e) {
			response.setStatus(404);
			return null;
		}		
	}
}
