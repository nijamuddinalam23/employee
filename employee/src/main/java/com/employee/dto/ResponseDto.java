package com.employee.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto<String> {
    private String data;
    private int status;
    private String message;

}
