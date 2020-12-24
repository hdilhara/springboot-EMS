package com.hdilhara.clientservice.service;

import com.hdilhara.clientservice.model.EmpProTask;
import com.hdilhara.clientservice.model.EmpProTaskId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class EmpProTaskService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${employee.service.url}")
    private String empServiceURL;

    public void setAuthorizationHeader(String token, HttpHeaders headers ) {
        try {
            headers.set("Authorization", token );
        }catch (NullPointerException e) {
            //log error
        }
    }


    public List<EmpProTask> fetchEmpProTasks(String token) {
        HttpHeaders headers = new HttpHeaders();
        setAuthorizationHeader(token, headers);
        try {
            return restTemplate.exchange(empServiceURL+"/emp-pro-task/", HttpMethod.GET, new HttpEntity<EmpProTask>(headers), new ParameterizedTypeReference<List<EmpProTask>>(){}).getBody();
        }
        catch(HttpClientErrorException e) {
            return null;
        }
    }

    public List<EmpProTask> fetchEmpProTasksByEid(int eId,String authorization) {
        HttpHeaders headers = new HttpHeaders();
        setAuthorizationHeader(authorization, headers);
        try {
            return restTemplate.exchange(empServiceURL+"/emp-pro-task/"+eId, HttpMethod.GET, new HttpEntity<EmpProTask>(headers), new ParameterizedTypeReference<List<EmpProTask>>(){}).getBody();
        }
        catch(HttpClientErrorException e) {
            return null;
        }
    }

    public List<EmpProTask> fetchEmpProTasksByEidAndPid(int eId, int pId, String authorization) {
		HttpHeaders headers = new HttpHeaders();
		setAuthorizationHeader(authorization, headers);
		try {
			return restTemplate.exchange(empServiceURL+"/emp-pro-task/eid/pid/"+eId+"/"+pId, HttpMethod.GET, new HttpEntity<EmpProTask>(headers), new ParameterizedTypeReference<List<EmpProTask>>(){}).getBody();
		}
		catch(HttpClientErrorException e) {
			return null;
		}
    }

    public EmpProTask saveempProTask(EmpProTaskId empProTaskId, String authorization) {

//        		List<EmpProTask> res = null;
		HttpHeaders headers = new HttpHeaders();
		setAuthorizationHeader(authorization, headers);
//		RestTemplate rt = new RestTemplate();

		try {
			return restTemplate.exchange(empServiceURL+"/emp-pro-task/", HttpMethod.POST, new HttpEntity<EmpProTaskId>(empProTaskId,headers), EmpProTask.class).getBody();
		}
		catch(HttpClientErrorException e) {
			return null;
		}
    }

    public EmpProTask removeEmpProTask(EmpProTaskId empProTaskId, String authorization) {
        HttpHeaders headers = new HttpHeaders();
        setAuthorizationHeader(authorization, headers);
        try {
            return restTemplate.exchange(empServiceURL+"/emp-pro-task/remove/", HttpMethod.POST, new HttpEntity<EmpProTaskId>(empProTaskId,headers), EmpProTask.class).getBody();
        }
        catch(HttpClientErrorException e) {
            return null;
        }
    }
}
