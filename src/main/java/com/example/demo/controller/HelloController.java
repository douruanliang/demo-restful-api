package com.example.demo.controller;


import com.example.demo.bean.User;
import com.example.demo.comm.anno.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@ResponseResult
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String index() {
        return "hello";
    }


    @RequestMapping("/users")
    public List<String> users() {

        List<String> names = new ArrayList<>();
        names.add("ali");
        names.add("dou");
        names.add("meituan");
        names.add("baidu");
        return names;
    }


    @RequestMapping("/getUser")
    public List<User> getUsers() {
        System.out.println("客户端参数：{list}");
        List<User> names = new ArrayList<>();


        User u = new User("car", "niu");
        names.add(u);
        User u1 = new User("car1", "niu2");
        names.add(u1);
        return names;
    }

    @RequestMapping("/user")
    public User user(String username) {
        System.out.println("客户端参数：{}" + username);
        User u = new User("car", "niu");

        return u;
    }

}
