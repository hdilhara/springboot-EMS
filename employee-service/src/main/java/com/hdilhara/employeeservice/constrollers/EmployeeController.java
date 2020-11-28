package com.hdilhara.employeeservice.constrollers;

import java.util.List;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.hdilhara.employeeservice.entities.Employee;
import com.hdilhara.employeeservice.repositories.EmployeeRepo;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeRepo employeeRepo;
	@GetMapping("/")
	public List<Employee> getEmployees( HttpServletResponse response) {
		List<Employee> employees = null;
		try {
			employees = (List<Employee>)employeeRepo.findAll();
		}catch (Exception e) {
//			System.out.println(e);
		}
//		if(employees == null)
//			response.setStatus(404);
		return employees;
	}
	
	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable int id, HttpServletResponse response) {
		Employee emp = null;
		try {
			emp = employeeRepo.findById(id).get();
		}catch (Exception e) {
//			System.out.println(e);
		}
		if(emp == null)
			response.setStatus(404);
		System.out.println(emp);
		return emp;
	}
	
	@PostMapping("/")
	public Employee createEmployee(@RequestBody Employee emp) {
		Employee result = null;
		try {
			result = employeeRepo.save(emp);
		}catch (Exception e) {
//			System.out.println(e);
		}
		return result;
	}
	
	@DeleteMapping("/{id}")
	public Employee deleteEmployee(@PathVariable int id, HttpServletResponse response){
		Employee emp = null;
		ResponseEntity<Employee> res;
		try {
			emp = employeeRepo.findById(id).get();
			employeeRepo.deleteById(id);
			return emp;
		}
		catch(Exception e){
			response.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("/")
	public Employee updateEmployee(@RequestBody Employee emp,HttpServletResponse response){
		try {
			if(!employeeRepo.findById(emp.getEmpId()).isPresent()) {
				response.setStatus(404);
				return null;
			}
			employeeRepo.save(emp);
			return emp;
		}
		catch(Exception e){
			response.setStatus(404);
			return null;
		}
	}
	
}