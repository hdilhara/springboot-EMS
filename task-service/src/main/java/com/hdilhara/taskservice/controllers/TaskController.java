package com.hdilhara.taskservice.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hdilhara.taskservice.entities.Task;
import com.hdilhara.taskservice.repositories.TaskRepo;





@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	TaskRepo taskRepo;
	@GetMapping("/")
	public List<Task> getTasks( HttpServletResponse response) {
		List<Task> tasks = null;
		try {
			tasks = (List<Task>)taskRepo.findAll();
		}catch (Exception e) {
//			System.out.println(e);
		}
//		if(Tasks == null)
//			response.setStatus(404);
		return tasks;
	}
	
	@GetMapping("/{id}")
	public Task getTask(@PathVariable int id, HttpServletResponse response) {
		Task task = null;
		try {
			task = taskRepo.findById(id).get();
		}catch (Exception e) {
//			System.out.println(e);
		}
		if(task == null)
			response.setStatus(404);
		return task;
	}
	
	@PostMapping("/")
	public Task createTask(@ModelAttribute Task task) {
		Task result = null;
		try {
			result = taskRepo.save(task);
		}catch (Exception e) {
//			System.out.println(e);
		}
		return result;
	}
	
	@DeleteMapping("/{id}")
	public Task deleteTask(@PathVariable int id){
		Task task = null;
		try {
			task = taskRepo.findById(id).get();
			taskRepo.deleteById(id);
			return task;
		}
		catch(Exception e){
			return null;
		}
	}
	
	@PutMapping("/")
	public Task updateTask(@RequestBody Task task){
		try {
			taskRepo.save(task);
			return task;
		}
		catch(Exception e){
			return null;
		}
	}
	
}