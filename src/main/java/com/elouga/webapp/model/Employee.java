package com.elouga.webapp.model;

import lombok.Data;

@Data
public class Employee {

    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String password;
}
