package com.hdilhara.clientserver.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.hdilhara.clientserver.model.Employee;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Value("${employee.service.url}")
	public String employeeServiceURL; 
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable int id, HttpServletResponse response) {
		System.out.println(">>>>>");
		Employee emp = null;
		RestTemplate rt = new RestTemplate();
		try {
			return rt.exchange(employeeServiceURL+"/employee/"+id, HttpMethod.GET, new HttpEntity<Employee>(new HttpHeaders()), Employee.class);//exchange(employeeServiceURL, HttpMethod.GET, new Request, responseType)
		}
		catch(HttpClientErrorException e) {
			response.setStatus(404);
			return null;
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Employee>> getEmployees(Principal p, HttpServletResponse response) {
		System.out.println(">>>>"+p);
		List<Employee> emp = null;
		RestTemplate rt = new RestTemplate();
		try {
			return rt.exchange(employeeServiceURL+"/employee/", HttpMethod.GET, new HttpEntity<Employee>(new HttpHeaders()), new ParameterizedTypeReference<List<Employee>>(){});//exchange(employeeServiceURL, HttpMethod.GET, new Request, responseType)
		}
		catch(HttpClientErrorException e) {
			response.setStatus(404);
			return null;
		}
	}
	
	@PostMapping("/t")
	public String testPost() {
		return "test post";
	}
	
	@PostMapping("/")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee, HttpServletResponse response){
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);		
		HttpEntity<Employee> he = new HttpEntity<Employee>(employee,headers);
		try {
			return rt.exchange(employeeServiceURL+"/employee/", HttpMethod.POST, he, Employee.class);//exchange(employeeServiceURL, HttpMethod.GET, new Request, responseType)
		}
		catch(HttpClientErrorException e) {
			response.setStatus(404);
			return null;
		}	
	}
	
	@DeleteMapping("/{id}")
	public  ResponseEntity<Employee> deleteEmployee(@PathVariable int id, HttpServletResponse response){
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<Employee> res;
		HttpEntity<Employee> he = new HttpEntity<Employee>(headers);
		try {
			return rt.exchange(employeeServiceURL+"/employee/"+id, HttpMethod.DELETE, he, Employee.class);
		}
		catch(HttpClientErrorException e) {
			res = new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
			return res;
		}	
	}
	
	@PutMapping("/")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp, HttpServletResponse response){
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);		
		HttpEntity<Employee> he = new HttpEntity<Employee>(emp,headers);
		try {
			return rt.exchange(employeeServiceURL+"/employee/", HttpMethod.PUT, he, Employee.class);//exchange(employeeServiceURL, HttpMethod.GET, new Request, responseType)
		}
		catch(HttpClientErrorException e) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
	}
	
}
