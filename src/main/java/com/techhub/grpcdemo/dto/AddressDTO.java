package com.techhub.grpcdemo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonDeserialize(
        builder = AddressDTO.AddressDTOBuilder.class
)
public class AddressDTO {

    private String locality;
    private int pinCode;
    private String state;
}
