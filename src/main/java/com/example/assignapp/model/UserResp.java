package com.example.assignapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class UserResp {

    private String message;

    @JsonIgnore
    private HttpStatus httpStatus;

}
