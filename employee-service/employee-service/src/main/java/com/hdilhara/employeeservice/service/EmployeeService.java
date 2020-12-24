package com.hdilhara.employeeservice.service;

import com.hdilhara.employeeservice.entities.Employee;
import com.hdilhara.employeeservice.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    RestTemplate restTemplate;

    public List<Employee> fetchEmployees() {
        try {
            return (List<Employee>)employeeRepo.findAll();
        }catch (Exception e) {
            //Log error
        }
        return null;
    }

    public Employee fetchEmployeeById(int id) {
        try {
            return employeeRepo.findById(id).get();
        }catch (Exception e) {
//			System.out.println(e);
            return null;
        }
    }

    public Employee saveEmployee(Employee emp) {
        try {
            return employeeRepo.save(emp);
        }catch (Exception e) {
			return null;
        }
    }

    public Employee deleteEmployee(int id) {
        try {
            Employee emp = employeeRepo.findById(id).get();
            employeeRepo.deleteById(id);
            return emp;
        }
        catch(Exception e){
            return null;
        }
    }

    public Employee updateEmployee(Employee emp) {
        try {
            if(!employeeRepo.findById(emp.getEmpId()).isPresent()) {
                return null;
            }
            employeeRepo.save(emp);
            return emp;
        }
        catch(Exception e){
            return null;
        }
    }
}
