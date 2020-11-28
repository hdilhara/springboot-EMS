package com.hdilhara.clientserver.model;

import java.sql.Date;

public class Task {

	int taskId;
	String taskTitle;
	String description;
	Date asignDate;
	Date dueDate;
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getAsignDate() {
		return asignDate;
	}
	public void setAsignDate(Date asignDate) {
		this.asignDate = asignDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
}
