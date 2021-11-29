package com.example.assignapp.service;

import com.example.assignapp.model.UserNew;
import com.example.assignapp.model.UserResp;
import com.example.assignapp.model.UserVo;
import com.example.assignapp.model.entity.User;
import com.example.assignapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserResp saveUser(UserNew userNew){

        UserResp userResp=new UserResp();
        boolean reply = userNew.getUsername().matches("[a-zA-Z]+");
        if(!reply){
            userResp.setMessage("Letter only required");
            userResp.setHttpStatus(HttpStatus.BAD_REQUEST);
            return userResp;
        }

        if(userRepository.findByUsername(userNew.getUsername())!=null){
            userResp.setMessage("User already exist");
            userResp.setHttpStatus(HttpStatus.OK);
            return userResp;
        }

        if(userNew.getDateOfBirth().after(new Date()) || userNew.getDateOfBirth().equals(new Date())){
            userResp.setMessage("Date must be less than today");
            userResp.setHttpStatus(HttpStatus.BAD_REQUEST);
            return userResp;
        }

        User user=new User();
        user.setUsername(userNew.getUsername());
        user.setDateOfBirth(userNew.getDateOfBirth());
        userRepository.save(user);

        userResp.setMessage("User Created");
        userResp.setHttpStatus(HttpStatus.NO_CONTENT);
        return userResp;

    }

    @Override
    public UserResp updateUser(String username, Date date) {

        UserResp userResp = new UserResp();
        if(date.after(new Date()) || date.equals(new Date())){
            userResp.setMessage("Date must be less than today");
            userResp.setHttpStatus(HttpStatus.BAD_REQUEST);
            return userResp;
        }
        User user = userRepository.findByUsername(username);
        if(user==null){
            userResp.setMessage("Record Not Found");
            userResp.setHttpStatus(HttpStatus.BAD_REQUEST);
            return userResp;
        }
        user.setDateOfBirth(date);
        userRepository.save(user);
        userResp.setMessage("No message");
        userResp.setHttpStatus(HttpStatus.NO_CONTENT);

        return userResp;
    }

    @Override
    public UserResp getUser(String username) {

        UserResp userResp = new UserResp();

        User user = userRepository.findByUsername(username);

        if(user==null){
            userResp.setMessage("Record Not Found");
            userResp.setHttpStatus(HttpStatus.BAD_REQUEST);
            return userResp;
        }

        Calendar c = Calendar.getInstance();
        int today = c.get(Calendar.DAY_OF_MONTH);
        int day = user.getDateOfBirth().getDay();
        System.out.println("day: " + day);
        if(day <= today){
            userResp.setMessage("Hello, " + user.getUsername() + "! Your birth day is in " + today + " days");
            userResp.setHttpStatus(HttpStatus.OK);
        } else if(today == day){
            userResp.setMessage("Hello, " + user.getUsername() + "! Happy birthday");
            userResp.setHttpStatus(HttpStatus.OK);
        }

        return userResp;
    }
}
