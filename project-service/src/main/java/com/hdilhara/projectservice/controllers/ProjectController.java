package com.hdilhara.projectservice.controllers;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.hdilhara.projectservice.entities.Project;
import com.hdilhara.projectservice.repositories.ProjectRepo;



@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	ProjectRepo projectRepo;
	@GetMapping("/")
	public List<Project> getProjects( HttpServletResponse response) {
		List<Project> Projects = null;
		try {
			Projects = (List<Project>)projectRepo.findAll();
		}catch (Exception e) {
//			System.out.println(e);
		}
//		if(Projects == null)
//			response.setStatus(404);
		return Projects;
	}
	
	@GetMapping("/{id}")
	public Project getProject(@PathVariable int id, HttpServletResponse response) {
		Project emp = null;
		try {
			emp = projectRepo.findById(id).get();
		}catch (Exception e) {
//			System.out.println(e);
		}
		if(emp == null)
			response.setStatus(404);
		return emp;
	}
	
	@GetMapping("/ids/{ids}")
	public List<Project> getProjectsUsingIds(@PathVariable List<Integer> ids, HttpServletResponse response) {
		List<Project> projects =null;
		try {
			projects  = (List<Project>) projectRepo.findAllById(ids);
		}catch (Exception e) {
//			System.out.println(e);
		}
//		if(Projects == null)
//			response.setStatus(404);
		return projects;
		
	}
	
	@PostMapping("/")
	public Project createProject(@RequestBody Project pro) {
		Project result = null;
		try {
			result = projectRepo.save(pro);
			return result;
		}catch (Exception e) {
//			System.out.println(e);
			return result;
		}
	}
	
	@DeleteMapping("/{id}")
	public Project deleteProject(@PathVariable int id, HttpServletResponse response){
		Project pro = null;
		try {
			pro = projectRepo.findById(id).get();
			projectRepo.deleteById(id);
			return pro;
		}
		catch(Exception e){
			response.setStatus(404);
			return null;
		}
	}
	
	@PutMapping("/")
	public Project updateProject(@RequestBody Project pro,HttpServletResponse response){
		try {
			if(!projectRepo.findById(pro.getProId()).isPresent()) {
				response.setStatus(404);
				return null;
			}
			projectRepo.save(pro);
			return pro;
		}
		catch(Exception e){
			response.setStatus(404);
			return null;
		}
	}
	
}