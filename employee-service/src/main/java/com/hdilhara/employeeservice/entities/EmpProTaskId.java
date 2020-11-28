package com.hdilhara.employeeservice.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class EmpProTaskId implements Serializable {

	int empId;
	int proId;
	int taskId;
	
	public EmpProTaskId() {
		super();
	}

	public EmpProTaskId(int empId, int proId, int taskId) {
		super();
		this.empId = empId;
		this.proId = proId;
		this.taskId = taskId;
	}
	
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + empId;
		result = prime * result + proId;
		result = prime * result + taskId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmpProTaskId other = (EmpProTaskId) obj;
		if (empId != other.empId)
			return false;
		if (proId != other.proId)
			return false;
		if (taskId != other.taskId)
			return false;
		return true;
	}
	
	
	
}
