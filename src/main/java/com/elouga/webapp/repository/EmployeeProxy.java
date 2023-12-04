package com.elouga.webapp.repository;

import com.elouga.webapp.configuration.CustomProperties;
import com.elouga.webapp.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class EmployeeProxy {

    @Autowired
    private CustomProperties props;

    public Employee getEmployee(int id){
//        String baseApiUrl = props.getApiUrl();
//
//        String getEmployeesUrl = baseApiUrl + "/employee/" + id;

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Employee> response = restTemplate.exchange(
                "http://localhost:9000/employee/" + id,
                HttpMethod.GET,
                null,
                Employee.class
        );

        log.debug("Get Empployee call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Iterable<Employee> getEmployees(){

        //Car Erreur : URI is not absolute
//        String baseApiUrl = props.getApiUrl();
//
//        String getEmployeesUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
                "http://localhost:9000/employees",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Employee>>() {}
        );

        log.debug("Get Empployees call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Employee createEmployee(Employee employee){

//        String baseApiUrl = props.getApiUrl();
//
//        String createEmployeesUrl = baseApiUrl + "/create";

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Employee> request = new HttpEntity<Employee>(employee);

        ResponseEntity<Employee> response = restTemplate.exchange(
                "http://localhost:9000/create",
                HttpMethod.POST,
                request,
                Employee.class
        );

        log.debug("Create Empployees call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Employee updateEmployee(Employee employee){

//        String baseApiUrl = props.getApiUrl();
//
//        String createEmployeesUrl = baseApiUrl + "/create";

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Employee> request = new HttpEntity<Employee>(employee);

        ResponseEntity<Employee> response = restTemplate.exchange(
                "http://localhost:9000/employee/" + employee.getId(),
                HttpMethod.PUT,
                request,
                Employee.class
        );

        log.debug("Update Empployees call " + response.getStatusCode().toString());

        return response.getBody();
    }
    public void deleteEmployee(Long id){
//        String baseApiUrl = props.getApiUrl();
//
//        String deleteEmployeesUrl = baseApiUrl + "/delete/" + id;

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Void> response = restTemplate.exchange(
                "http://localhost:9000/employee/" + id,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        log.debug("Delete Empployee call " + response.getStatusCode().toString());
    }
}
