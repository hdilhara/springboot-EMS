package com.hdilhara.employeeservice.constrollers;

import com.hdilhara.employeeservice.entities.EmpProTask;
import com.hdilhara.employeeservice.entities.EmpProTaskId;
import com.hdilhara.employeeservice.repositories.EmpProTaskRepo;
import com.hdilhara.employeeservice.service.EmpProTaskService;
import com.hdilhara.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/emp-pro-task")
public class EmpProTaskController {

	@Autowired
	EmpProTaskService service;
	
	@GetMapping("/")
	public ResponseEntity<List<EmpProTask>> getEmpProTasks(HttpServletResponse response){
		List<EmpProTask> empProTasks = service.fetchEmpProTasks();
		if(empProTasks == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(empProTasks);
		}
	}
	@GetMapping("/{eId}")
	public ResponseEntity<List<EmpProTask>> getEmpProTasksByEId(@PathVariable int eId, HttpServletResponse response){
		List<EmpProTask> empProTasks = service.fetchEmpProTasksByEid(eId);
		if(empProTasks == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(empProTasks);
		}
	}
	
	@GetMapping("/eid/pid/{eId}/{pId}")
	public ResponseEntity<List<EmpProTask>> getEmpProTasksByEIdPId(@PathVariable int eId, @PathVariable int pId, HttpServletResponse response){
		List<EmpProTask> empProTasks = service.fetchEmpProTasksByEIdPId(eId,pId);
		if(empProTasks == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(empProTasks);
		}
	}


	@PostMapping("/")
	public ResponseEntity<EmpProTask> addEmpProTask(@RequestBody EmpProTaskId empProTaskId, HttpServletResponse response) {
		EmpProTask empProTask = service.saveEmpProTasks(empProTaskId);
		if(empProTask == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(empProTask);
		}
	}
	@PostMapping("/remove")
	public ResponseEntity<EmpProTask> removeEmpProTask(@RequestBody EmpProTaskId empProTaskId, HttpServletResponse response) {
		EmpProTask empProTasks = service.removeEmpProTasks(empProTaskId);
		if(empProTasks == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(empProTasks);
		}

	}
	
}
