package com.hdilhara.employeeservice.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.hdilhara.employeeservice.entities.EmpProTask;
import com.hdilhara.employeeservice.entities.EmpProTaskId;

public interface EmpProTaskRepo extends CrudRepository<EmpProTask, EmpProTaskId> {
	
}
