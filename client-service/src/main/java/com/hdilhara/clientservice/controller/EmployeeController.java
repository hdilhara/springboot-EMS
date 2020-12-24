package com.hdilhara.clientservice.controller;

import com.hdilhara.clientservice.model.Employee;
import com.hdilhara.clientservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Value("${employee.service.url}")
	public String employeeServiceURL;

	@Autowired
	EmployeeService service;
	
	public void setAuthorizationHeader(HttpServletRequest request, HttpHeaders headers ) {
		try {
			headers.set("Authorization", request.getHeader("Authorization") );
		}catch (NullPointerException e) {
			//log error
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable int id, HttpServletResponse response, HttpServletRequest request) {

		Employee employee = service.fetchEmployeeById(id,request.getHeader("Authorization"));
		if(employee == null){
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok().body(employee);
		}

//		Employee emp = null;
//		RestTemplate rt = new RestTemplate();
//		try {
//			HttpHeaders headers = new HttpHeaders();
//			setAuthorizationHeader(request, headers);
//			return rt.exchange(employeeServiceURL+"/employee/"+id, HttpMethod.GET, new HttpEntity<Employee>(headers), Employee.class);//exchange(employeeServiceURL, HttpMethod.GET, new Request, responseType)
//		}
//		catch(HttpClientErrorException e) {
//			response.setStatus(404);
//			return null;
//		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Employee>> getEmployees( HttpServletRequest request, HttpServletResponse response) {
		List<Employee> emp = null;
//		RestTemplate rt = new RestTemplate();
//		try {
//			HttpHeaders headers = new HttpHeaders();
//			setAuthorizationHeader(request, headers);
//			return rt.exchange(employeeServiceURL+"/employee/", HttpMethod.GET, new HttpEntity<Employee>(headers), new ParameterizedTypeReference<List<Employee>>(){});//exchange(employeeServiceURL, HttpMethod.GET, new Request, responseType)
//		}
//		catch(HttpClientErrorException e) {
//			response.setStatus(404);
//			return null;
//		}
		emp = service.fetchAllEmployees(request.getHeader("Authorization"));
		if(emp == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(emp);
		}
	}
	
//	@PostMapping("/t")
//	public String testPost() {
//		return "test post";
//	}
	
	@PostMapping("/")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee, HttpServletRequest request, HttpServletResponse response){

		Employee emp = null;
		emp= service.saveEmployee(employee, request.getHeader("Authorization"));
		if(emp == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(emp);
		}

//		RestTemplate rt = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		setAuthorizationHeader(request, headers);
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<Employee> he = new HttpEntity<Employee>(employee,headers);
//		try {
//			return rt.exchange(employeeServiceURL+"/employee/", HttpMethod.POST, he, Employee.class);//exchange(employeeServiceURL, HttpMethod.GET, new Request, responseType)
//		}
//		catch(HttpClientErrorException e) {
//			response.setStatus(404);
//			return null;
//		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable int id, HttpServletRequest request, HttpServletResponse response){
		Employee emp = null;
		emp= service.deleteEmployee(id, request.getHeader("Authorization"));
		if(emp == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(emp);
		}


//		RestTemplate rt = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		setAuthorizationHeader(request, headers);
//		ResponseEntity<Employee> res;
//		HttpEntity<Employee> he = new HttpEntity<Employee>(headers);
//		try {
//			return rt.exchange(employeeServiceURL+"/employee/"+id, HttpMethod.DELETE, he, Employee.class);
//		}
//		catch(HttpClientErrorException e) {
//			res = new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
//			return res;
//		}
	}
	
	@PutMapping("/")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee, HttpServletRequest request, HttpServletResponse response){
		Employee emp = null;
		emp = service.updateEmployee(employee,request.getHeader("Authorization"));
		if(emp == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(emp);
		}
//		RestTemplate rt = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		setAuthorizationHeader(request, headers);
//		HttpEntity<Employee> he = new HttpEntity<Employee>(emp,headers);
//		try {
//			return rt.exchange(employeeServiceURL+"/employee/", HttpMethod.PUT, he, Employee.class);//exchange(employeeServiceURL, HttpMethod.GET, new Request, responseType)
//		}
//		catch(HttpClientErrorException e) {
//			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
//		}
	}
	
}
