package com.hdilhara.employeeservice.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class EmpProTask {

	@EmbeddedId
	EmpProTaskId id;

	public EmpProTask() {
		super();
	}

	public EmpProTask(EmpProTaskId id) {
		super();
		this.id = id;
	}

	public EmpProTaskId getId() {
		return id;
	}

	public void setId(EmpProTaskId id) {
		this.id = id;
	}
	
	
}
