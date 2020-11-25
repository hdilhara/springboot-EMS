package com.hdilhara.employeeservice.repositories;

import org.springframework.data.repository.CrudRepository;

import com.hdilhara.employeeservice.entities.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Integer> {

	
	
}
