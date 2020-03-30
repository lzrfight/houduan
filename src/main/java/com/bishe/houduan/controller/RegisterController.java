package com.bishe.houduan.controller;

import com.bishe.houduan.pojo.User;
import com.bishe.houduan.result.Result;
import com.bishe.houduan.service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/register")
    @ResponseBody
    public Result register(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        boolean exist = userService.isExist(username);
        if (exist)
        {
            return new Result(200);
        }
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String encodedPassword = new SimpleHash("md5", password , salt , times).toString();
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        userService.add(user);
        if (user == null) {
            System.out.println("注册失败");
            return new Result(400);
        }else {
            System.out.println("注册成功");
            System.out.println("注册的账户名为: " + user.getUsername());
            System.out.println("注册的密码为: " + user.getPassword());
            return new Result(200);

        }
        }
    }