package com.hdilhara.projectservice.repositories;

import com.hdilhara.projectservice.entities.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepo extends CrudRepository<Project, Integer> {

}
