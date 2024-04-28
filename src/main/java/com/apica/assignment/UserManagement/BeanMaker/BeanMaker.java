package com.apica.assignment.UserManagement.BeanMaker;

import com.apica.assignment.UserManagement.Model.Journal;
import com.apica.assignment.UserManagement.Model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.ArrayList;
import java.util.List;

@Configuration
public class BeanMaker {

    @Bean
    public List<User> getUsers(){

        return new ArrayList<>();
    }

    @Bean
    public List<Journal> getJournal(){

        return new ArrayList<>();
    }
}
