package com.hdilhara.taskservice.repositories;

import org.springframework.data.repository.CrudRepository;

import com.hdilhara.taskservice.entities.Task;

public interface TaskRepo extends CrudRepository<Task, Integer> {

}
