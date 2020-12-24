package com.hdilhara.employeeservice.constrollers;

import com.hdilhara.employeeservice.entities.Employee;
import com.hdilhara.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/employee")
//@CrossOrigin
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@GetMapping("/")
	public ResponseEntity<List<Employee>> getEmployees(HttpServletResponse response) {
		List<Employee> employees = null;
		employees = service.fetchEmployees();

		if(employees == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(employees);
		}

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable int id, HttpServletResponse response) {
		Employee employee = null;
		employee = service.fetchEmployeeById(id);

		if(employee == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(employee);
		}

	}
	
	@PostMapping("/")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee emp) {
		Employee employee = null;
		employee = service.saveEmployee(emp);

		if(employee == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(employee);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable int id, HttpServletResponse response){
		Employee employee = null;
		employee = service.deleteEmployee(id);

		if(employee == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(employee);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp, HttpServletResponse response){
		Employee employee = null;
		employee = service.updateEmployee(emp);

		if(employee == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(employee);
		}

	}
	
}
