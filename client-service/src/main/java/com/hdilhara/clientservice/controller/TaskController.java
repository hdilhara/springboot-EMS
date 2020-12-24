 package com.hdilhara.clientservice.controller;

 import com.hdilhara.clientservice.model.Task;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.core.ParameterizedTypeReference;
 import org.springframework.http.*;
 import org.springframework.web.bind.annotation.*;
 import org.springframework.web.client.HttpClientErrorException;
 import org.springframework.web.client.RestTemplate;

 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import java.util.List;

// @CrossOrigin
 @RestController
 @RequestMapping("task")
 public class TaskController {


     @Value("${task.service.url}")
     public String taskServiceURL;

     public void setAuthorizationHeader(HttpServletRequest request, HttpHeaders headers ) {
         try {
             headers.set("Authorization", request.getHeader("Authorization") );
         }catch (NullPointerException e) {
             //log error
         }
     }

     @GetMapping("/{id}")
     public ResponseEntity<Task> getProject(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
         RestTemplate rt = new RestTemplate();
         try {
             HttpHeaders headers = new HttpHeaders();
             setAuthorizationHeader(request, headers);
             return rt.exchange(taskServiceURL+"/task/"+id, HttpMethod.GET, new HttpEntity<Task>(headers), Task.class);//exchange(projectServiceURL, HttpMethod.GET, new Request, responseType)
         }
         catch(HttpClientErrorException e) {
             response.setStatus(404);
             return null;
         }
     }

     @GetMapping("/")
     public ResponseEntity<List<Task>> getProjects(HttpServletResponse response, HttpServletRequest request) {
         RestTemplate rt = new RestTemplate();
         try {
             HttpHeaders headers = new HttpHeaders();
             setAuthorizationHeader(request, headers);
             return rt.exchange(taskServiceURL+"/task/", HttpMethod.GET, new HttpEntity<Task>(headers), new ParameterizedTypeReference<List<Task>>(){});//exchange(projectServiceURL, HttpMethod.GET, new Request, responseType)
         }
         catch(HttpClientErrorException e) {
             response.setStatus(404);
             return null;
         }
     }

     @GetMapping("/ids/{ids}")
     public ResponseEntity<List<Task>> getProjectsByIds(@PathVariable String ids, HttpServletRequest request , HttpServletResponse response) {
         RestTemplate rt = new RestTemplate();
         try {
             HttpHeaders headers = new HttpHeaders();
             setAuthorizationHeader(request, headers);
             return rt.exchange(taskServiceURL+"/task/ids/"+ids, HttpMethod.GET, new HttpEntity<Task>(headers), new ParameterizedTypeReference<List<Task>>(){});//exchange(projectServiceURL, HttpMethod.GET, new Request, responseType)
         }
         catch(HttpClientErrorException e) {
             response.setStatus(404);
             return null;
         }
     }

     @PostMapping("/")
     public ResponseEntity<Task> createTask(@RequestBody Task task, HttpServletRequest request, HttpServletResponse response){
         RestTemplate rt = new RestTemplate();
         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.APPLICATION_JSON);
         setAuthorizationHeader(request, headers);
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
     public ResponseEntity<Task> deleteTask(@PathVariable int id, HttpServletRequest request, HttpServletResponse response){
         RestTemplate rt = new RestTemplate();
         HttpHeaders headers = new HttpHeaders();
         setAuthorizationHeader(request, headers);
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
     public ResponseEntity<Task> updateTask(@RequestBody Task emp, HttpServletRequest request, HttpServletResponse response){
         RestTemplate rt = new RestTemplate();
         HttpHeaders headers = new HttpHeaders();
         setAuthorizationHeader(request, headers);
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
