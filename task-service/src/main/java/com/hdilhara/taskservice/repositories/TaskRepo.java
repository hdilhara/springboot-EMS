package com.hdilhara.taskservice.repositories;

import com.hdilhara.taskservice.entities.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepo extends CrudRepository<Task, Integer> {

}
