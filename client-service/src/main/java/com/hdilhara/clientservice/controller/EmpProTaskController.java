package com.hdilhara.clientservice.controller;

import com.hdilhara.clientservice.model.EmpProTask;
import com.hdilhara.clientservice.model.EmpProTaskId;
import com.hdilhara.clientservice.service.EmpProTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


//@CrossOrigin
@RestController
@RequestMapping("/emp-pro-task")
public class EmpProTaskController {

	@Autowired
	EmpProTaskService service;
	
	@Value("${employee.service.url}")
	private String empServiceURL;

	@GetMapping("/")
	public ResponseEntity<List<EmpProTask>> getEmpProTasks(HttpServletRequest request, HttpServletResponse response){
		List<EmpProTask> empProTask = null;
		empProTask = service.fetchEmpProTasks(request.getHeader("Authorization"));

		if(empProTask == null){
			return  ResponseEntity.notFound().build();
		}else{
			return ResponseEntity.ok().body(empProTask);
		}
	}
	
	@GetMapping("/{eId}")
	public ResponseEntity<List<EmpProTask>> getEmpProTask(@PathVariable int eId, HttpServletRequest request, HttpServletResponse response){
		List<EmpProTask> empProTask = null;
		empProTask = service.fetchEmpProTasksByEid(eId,request.getHeader("Authorization"));

		if(empProTask == null){
			return  ResponseEntity.notFound().build();
		}else{
			return ResponseEntity.ok().body(empProTask);
		}
	}
	
	@GetMapping("/eid/pid/{eId}/{pId}")
	public ResponseEntity<List<EmpProTask>> getEmpProTaskByEidPid(@PathVariable int eId, @PathVariable int pId, HttpServletRequest request, HttpServletResponse response){
		List<EmpProTask> empProTask = null;
		empProTask = service.fetchEmpProTasksByEidAndPid(eId,pId,request.getHeader("Authorization"));

		if(empProTask == null){
			return  ResponseEntity.notFound().build();
		}else{
			return ResponseEntity.ok().body(empProTask);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<EmpProTask> addEmpProTask(@RequestBody EmpProTaskId empProTaskId, HttpServletRequest request, HttpServletResponse response) {
		EmpProTask empProTask = null;
		empProTask = service.saveempProTask(empProTaskId,request.getHeader("Authorization"));

		if(empProTask == null){
			return  ResponseEntity.notFound().build();
		}else{
			return ResponseEntity.ok().body(empProTask);
		}
	}

	@PostMapping("/remove")
	public ResponseEntity<EmpProTask> removeEmpProTask(@RequestBody EmpProTaskId empProTaskId, HttpServletRequest request, HttpServletResponse response) {
		EmpProTask empProTask = null;
		empProTask = service.removeEmpProTask(empProTaskId,request.getHeader("Authorization"));

		if(empProTask == null){
			return  ResponseEntity.notFound().build();
		}else{
			return ResponseEntity.ok().body(empProTask);
		}
	}
}
