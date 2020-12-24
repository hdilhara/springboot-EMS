package com.hdilhara.employeeservice.repositories;

import com.hdilhara.employeeservice.entities.EmpProTask;
import com.hdilhara.employeeservice.entities.EmpProTaskId;
import org.springframework.data.repository.CrudRepository;

public interface EmpProTaskRepo extends CrudRepository<EmpProTask, EmpProTaskId> {
	
}
