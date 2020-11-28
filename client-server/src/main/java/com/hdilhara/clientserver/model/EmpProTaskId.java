package com.hdilhara.clientserver.model;

public class EmpProTaskId {

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
	
	
	
}
