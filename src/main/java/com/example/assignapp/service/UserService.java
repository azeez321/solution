package com.example.assignapp.service;

import com.example.assignapp.model.UserNew;
import com.example.assignapp.model.UserResp;
import com.example.assignapp.model.UserVo;

import java.util.Date;

public interface UserService {

    public UserResp saveUser(UserNew userNew);

    public UserResp updateUser(String username, Date date);

    public UserResp getUser(String username);

}
