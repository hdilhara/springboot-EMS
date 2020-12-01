package com.hdilhara.projectservice.repositories;

import org.springframework.data.repository.CrudRepository;

import com.hdilhara.projectservice.entities.Project;

public interface ProjectRepo extends CrudRepository<Project, Integer> {

}
