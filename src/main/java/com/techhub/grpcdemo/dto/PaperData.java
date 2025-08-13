package com.techhub.grpcdemo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

//@Getter
//@Builder
//@ToString
@Data
public class PaperData {
    private List<PaperElement<?>> elements;
}
