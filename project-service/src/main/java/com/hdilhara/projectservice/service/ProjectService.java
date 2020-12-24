package com.hdilhara.projectservice.service;

import com.hdilhara.projectservice.entities.Project;
import com.hdilhara.projectservice.repositories.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepo projectRepo;


    public List<Project> fetchAllProjects() {
        try {
            return (List<Project>)projectRepo.findAll();
        }catch (Exception e) {
//			Log error
        }
        return null;
    }

    public Project fetchrojectById(int id) {
        try {
            return  projectRepo.findById(id).get();
        }catch (Exception e) {
			return null;
        }
    }

    public List<Project> fetchrojectsByIds(List<Integer> ids) {
        List<Project> projects = null;
        try {
            return  (List<Project>) projectRepo.findAllById(ids);
        }catch (Exception e) {
            return null;
        }
    }

    public Project saveProject(Project pro) {
        try {
            return projectRepo.save(pro);
        }catch (Exception e) {
            return null;
        }
    }

    public Project deleteProject(int id) {
        try {
            Project pro = projectRepo.findById(id).get();
            projectRepo.deleteById(id);
            return pro;
        }
        catch(Exception e){
            return null;
        }
    }

    public Project updateProjectById(Project pro) {
        try {
            if(!projectRepo.findById(pro.getProId()).isPresent()) {
                return null;
            }
            projectRepo.save(pro);
            return pro;
        }
        catch(Exception e){
            return null;
        }
    }
}
