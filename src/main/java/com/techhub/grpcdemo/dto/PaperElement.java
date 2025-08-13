package com.techhub.grpcdemo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

//@Getter
//@Builder
//@ToString
@Data
public class PaperElement<D> {

    private ElementType elementType;
    private D data;
    private ElementProp props;


    public enum ElementType {
        TEXT, IMAGE, TABLE
    }
}
