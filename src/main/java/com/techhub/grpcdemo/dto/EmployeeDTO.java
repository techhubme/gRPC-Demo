package com.techhub.grpcdemo.dto;

import lombok.Data;

@Data
public class EmployeeDTO {

    private String name;
    private int age;
    private boolean enable;
    private AddressDTO address;
}
