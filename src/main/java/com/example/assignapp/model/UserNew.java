package com.example.assignapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UserNew {
    @JsonProperty("username")
    private String username;

    @JsonProperty("date_of_birth")
    private Date dateOfBirth;
}
