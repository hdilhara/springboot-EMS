package com.hdilhara.employeeservice.service;

import com.hdilhara.employeeservice.entities.EmpProTask;
import com.hdilhara.employeeservice.entities.EmpProTaskId;
import com.hdilhara.employeeservice.repositories.EmpProTaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpProTaskService {

    @Autowired
    EmpProTaskRepo empProTaskRepo;

    public List<EmpProTask> fetchEmpProTasks() {
        List<EmpProTask> res = null;
        try {
            return (List<EmpProTask>)empProTaskRepo.findAll();
        }
        catch (Exception e) {
            return  null;
        }
    }

    public List<EmpProTask> fetchEmpProTasksByEid(int eId) {
        try {
            List<EmpProTask> res =(List<EmpProTask>)empProTaskRepo.findAll();
            return res.stream().filter( v -> v.getId().getEmpId() == eId ).collect(Collectors.toList());
        }
        catch (Exception e) {
           return null;
        }
    }

    public List<EmpProTask> fetchEmpProTasksByEIdPId(int eId, int pId) {
        try {
            List<EmpProTask> res =(List<EmpProTask>)empProTaskRepo.findAll();
            res = res.stream().filter( v -> {
                if((v.getId().getEmpId() == eId) && (v.getId().getProId() == pId) )
                    return true;
                else
                    return false;
            } ).collect(Collectors.toList());
            return res;
        }
        catch (Exception e) {
            return null;
        }
    }

    public EmpProTask saveEmpProTasks(EmpProTaskId empProTaskId) {
        EmpProTask empProTask = new EmpProTask(empProTaskId);
        try {
            empProTaskRepo.save(empProTask);
            return empProTask;
        }
        catch (Exception e) {
            return null;
        }
    }

    public EmpProTask removeEmpProTasks(EmpProTaskId empProTaskId) {
        EmpProTask empProTask = new EmpProTask(empProTaskId);
        try {
            empProTaskRepo.delete(empProTask);
            return empProTask;
        }
        catch (Exception e) {
            return null;
        }
    }
}
