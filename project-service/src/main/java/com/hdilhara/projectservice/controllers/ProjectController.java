package com.hdilhara.projectservice.controllers;

import com.hdilhara.projectservice.entities.Project;
import com.hdilhara.projectservice.repositories.ProjectRepo;
import com.hdilhara.projectservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	ProjectService service;


	@GetMapping("/")
	public ResponseEntity<List<Project>> getProjects(HttpServletResponse response) {
		List<Project> projects = service.fetchAllProjects();
		if(projects == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(projects);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Project> getProject(@PathVariable int id, HttpServletResponse response) {
		Project projects = service.fetchrojectById(id);
		if(projects == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(projects);
		}
	}
	
	@GetMapping("/ids/{ids}")
	public ResponseEntity<List<Project>> getProjectsUsingIds(@PathVariable List<Integer> ids, HttpServletResponse response) {
		List<Project> projects = service.fetchrojectsByIds(ids);
		if(projects == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(projects);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Project> createProject(@RequestBody Project pro) {
		Project project = service.saveProject(pro);
		if(project == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(project);
		}

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Project> deleteProject(@PathVariable int id, HttpServletResponse response){
		Project project = service.deleteProject(id);
		if(project == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(project);
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<Project> updateProject(@RequestBody Project pro, HttpServletResponse response){
		Project project = service.updateProjectById(pro);
		if(project == null){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok().body(project);
		}

	}
	
}