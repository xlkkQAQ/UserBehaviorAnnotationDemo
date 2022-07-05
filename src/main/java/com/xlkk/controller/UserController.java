package com.xlkk.controller;

import com.xlkk.annotation.PrintLog;
import com.xlkk.vo.HandEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/user/info")
    @PrintLog(value = "/user/info",handle = HandEnum.SELECT)
    public String getUserInfo(String username){
        return "用户"+username+"登录成功";
    }

    @RequestMapping("/")
    public String hello(){
        return "hello world";
    }

    @RequestMapping("/test")
    public String Test(String name){
        return "测试接口"+name;
    }

}
