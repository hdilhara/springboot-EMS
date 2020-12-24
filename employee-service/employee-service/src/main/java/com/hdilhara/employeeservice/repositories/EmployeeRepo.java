package com.hdilhara.employeeservice.repositories;

import com.hdilhara.employeeservice.entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepo extends CrudRepository<Employee, Integer> {

	
	
}
