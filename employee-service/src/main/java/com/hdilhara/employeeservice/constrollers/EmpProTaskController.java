package com.hdilhara.employeeservice.constrollers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdilhara.employeeservice.entities.EmpProTask;
import com.hdilhara.employeeservice.entities.EmpProTaskId;
import com.hdilhara.employeeservice.repositories.EmpProTaskRepo;

@RestController
@RequestMapping("/emp-pro-task")
public class EmpProTaskController {

	@Autowired
	EmpProTaskRepo empProTaskRepo;
	
	@GetMapping("/")
	public List<EmpProTask> getEmpProTasks(HttpServletResponse response){
		List<EmpProTask> res = null;
		try {
			res =(List<EmpProTask>)empProTaskRepo.findAll();
		}
		catch (Exception e) {
			response.setStatus(404);
		}
		return res;
	}
	@GetMapping("/{eId}")
	public List<EmpProTask> getEmpProTasksByEId( @PathVariable int eId, HttpServletResponse response){
		List<EmpProTask> res = null;
		try {
			res =(List<EmpProTask>)empProTaskRepo.findAll();
			res = res.stream().filter( v -> v.getId().getEmpId() == eId ).collect(Collectors.toList());
		}
		catch (Exception e) {
			response.setStatus(404);
		}
		return res;
	}

	@PostMapping("/")
	public EmpProTask addEmpProTask(@RequestBody EmpProTaskId empProTaskId, HttpServletResponse response) {
		EmpProTask empProTask = new EmpProTask(empProTaskId);
		try {
			empProTaskRepo.save(empProTask);
			return empProTask;
		}
		catch (Exception e) {
			response.setStatus(404);
			return null;
		}		
	}
	@PostMapping("/remove")
	public EmpProTask removeEmpProTask(@RequestBody EmpProTaskId empProTaskId, HttpServletResponse response) {
		EmpProTask empProTask = new EmpProTask(empProTaskId);
		try {
			empProTaskRepo.delete(empProTask);
			return empProTask;
		}
		catch (Exception e) {
			response.setStatus(404);
			return null;
		}		
	}
	
}