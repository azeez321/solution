package com.example.assignapp.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserVo {

    private Long id;
    private String username;
    private Date dateOfBirth;

}
