package com.techhub.grpcdemo.dto;

import lombok.Data;

@Data
public class AddressDTO {

    private String locality;
    private int pinCode;
    private String state;
}
