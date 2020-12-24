package com.hdilhara.taskservice.service;

import com.hdilhara.taskservice.entities.Task;
import com.hdilhara.taskservice.repositories.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepo taskRepo;

    public List<Task> fetchAllTasks() {
        try {
            return (List<Task>)taskRepo.findAll();
        }catch (Exception e) {
			return null;
        }
    }

    public Task fetchTaskById(int id) {
        try {
            return taskRepo.findById(id).get();
        }catch (Exception e) {
            return null;
        }
    }

    public List<Task> fetchTasksByIds(List<Integer> ids) {
        try {
           return  (List<Task>)taskRepo.findAllById(ids);
        }catch (Exception e) {
			return null;
        }
    }

    public Task createTask(Task task) {
        try {
            return taskRepo.save(task);
        }catch (Exception e) {
            return null;
        }
    }

    public Task deleteTask(int id) {
        try {
            Task task = taskRepo.findById(id).get();
            taskRepo.deleteById(id);
            return task;
        }
        catch(Exception e){
            return null;
        }
    }

    public Task updateTask(Task task){
        try {
            if(!taskRepo.findById(task.getTaskId()).isPresent()) {
                return null;
            }
            return taskRepo.save(task);
        }
        catch(Exception e){
            return null;
        }
    }
}
