package com.techhub.grpcdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

@Data
@JsonDeserialize(
        builder = EmployeeDTO.EmployeeDTOBuilder.class
)
@Builder
public class EmployeeDTO  {

    private String name;

    private int age;

    private boolean enable;

    @JsonProperty("address")
    private AddressDTO address;
}
