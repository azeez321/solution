package com.example.assignapp.controller;

import com.example.assignapp.model.UserNew;
import com.example.assignapp.model.UserResp;
import com.example.assignapp.model.UserUpdate;
import com.example.assignapp.model.UserVo;
import com.example.assignapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class UserApi {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public ResponseEntity<UserResp> save(@RequestBody(required = true) UserNew userNew){
        UserResp userResp = userService.saveUser(userNew);
        return new ResponseEntity<>(userResp, userResp.getHttpStatus());
    }

    @RequestMapping(value = "/hello/{username}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@PathVariable(required = true) String username, @RequestBody(required = true) UserUpdate userUpdate){

        UserResp userResp = userService.updateUser(username, userUpdate.getDateOfBirth());
        return new ResponseEntity<>(userResp, userResp.getHttpStatus());
    }

    @RequestMapping(value = "/hello/{username}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@PathVariable(required = true) String username){
        UserResp userResp =  userService.getUser(username);
        return new ResponseEntity<>(userResp, userResp.getHttpStatus());

    }


}
