package com.hdilhara.taskservice.controllers;

import com.hdilhara.taskservice.entities.Task;
import com.hdilhara.taskservice.repositories.TaskRepo;
import com.hdilhara.taskservice.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	TaskService service;

	@GetMapping("/")
	public ResponseEntity<List<Task>> getTasks(HttpServletResponse response) {
		List<Task> tasks = service.fetchAllTasks();
		if(tasks == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(tasks);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> getTask(@PathVariable int id, HttpServletResponse response) {
		Task task = service.fetchTaskById(id);
		if(task == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(task);
		}
	}
	
	@GetMapping("/ids/{ids}")
	public ResponseEntity<List<Task>> getTasksByIds(@PathVariable List<Integer> ids, HttpServletResponse response) {
		List<Task> tasks = service.fetchTasksByIds(ids);
		if(tasks == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(tasks);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Task> createTask(@RequestBody Task ta) {
		Task task = service.createTask(ta);
		if(task == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(task);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Task> deleteTask(@PathVariable int id, HttpServletResponse response){
		Task task = service.deleteTask(id);
		if(task == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(task);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<Task> updateTask(@RequestBody Task ta, HttpServletResponse response){
		Task task = service.updateTask(ta);
		if(task == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(task);
		}
	}
	
}