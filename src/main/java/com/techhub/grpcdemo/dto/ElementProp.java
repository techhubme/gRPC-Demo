package com.techhub.grpcdemo.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

//@Getter
//@Builder
//@ToString
@Data
public class ElementProp {

    private FontStyle fontStyle;
    private int fontSize;
    private String fontName;
    private Alignment alignment;

    public enum FontStyle {
        NORMAL, BOLD, ITALIC
    }

    public enum Alignment {
        LEFT, CENTER, RIGHT
    }
}
