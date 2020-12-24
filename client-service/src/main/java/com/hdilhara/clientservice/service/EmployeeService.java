package com.hdilhara.clientservice.service;

import com.hdilhara.clientservice.model.Employee;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class EmployeeService {

    @Value("${employee.service.url}")
    public String employeeServiceURL;

    @Autowired
    RestTemplate restTemplate;

    public void setAuthorizationHeader(String token, HttpHeaders headers ) {
        try {
            headers.set("Authorization", token );
        }catch (NullPointerException e) {
            //log error
        }
    }

    public Employee fetchEmployeeById(int id,String token){
//        Employee emp = null;
        RestTemplate rt = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            setAuthorizationHeader(token, headers);
            return  restTemplate.exchange(employeeServiceURL+"/employee/"+id, HttpMethod.GET, new HttpEntity<Employee>(headers), Employee.class).getBody();//exchange(employeeServiceURL, HttpMethod.GET, new Request, responseType)
        }
        catch(HttpClientErrorException e) {
            return null;
        }
    }


    public List<Employee> fetchAllEmployees(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            setAuthorizationHeader(token, headers);
            return restTemplate.exchange(employeeServiceURL+"/employee/", HttpMethod.GET, new HttpEntity<Employee>(headers), new ParameterizedTypeReference<List<Employee>>(){}).getBody();//exchange(employeeServiceURL, HttpMethod.GET, new Request, responseType)
        }
        catch(HttpClientErrorException e) {
            return null;
        }
    }

    public Employee saveEmployee(Employee employee, String token) {
		HttpHeaders headers = new HttpHeaders();
		setAuthorizationHeader(token, headers);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Employee> he = new HttpEntity<Employee>(employee,headers);
		try {
			return restTemplate.exchange(employeeServiceURL+"/employee/", HttpMethod.POST, he, Employee.class).getBody();//exchange(employeeServiceURL, HttpMethod.GET, new Request, responseType)
		}
		catch(HttpClientErrorException e) {
			return null;
		}
    }

    public Employee deleteEmployee(int id, String token) {
        HttpHeaders headers = new HttpHeaders();
        setAuthorizationHeader(token, headers);
        ResponseEntity<Employee> res;
        HttpEntity<Employee> he = new HttpEntity<Employee>(headers);
        try {
            return restTemplate.exchange(employeeServiceURL+"/employee/"+id, HttpMethod.DELETE, he, Employee.class).getBody();
        }
        catch(HttpClientErrorException e) {
            return null;
        }
    }

    public Employee updateEmployee(Employee emp, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        setAuthorizationHeader(token, headers);
        HttpEntity<Employee> he = new HttpEntity<Employee>(emp,headers);
        try {
            return restTemplate.exchange(employeeServiceURL+"/employee/", HttpMethod.PUT, he, Employee.class).getBody();//exchange(employeeServiceURL, HttpMethod.GET, new Request, responseType)
        }
        catch(HttpClientErrorException e) {
            return null;
        }
    }
}
