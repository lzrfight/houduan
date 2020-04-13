package com.bishe.houduan.controller;

import com.bishe.houduan.pojo.AdminUser;
import com.bishe.houduan.result.Result;
import com.bishe.houduan.result.ResultFactory;
import com.bishe.houduan.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUserController {
    @Autowired
    AdminUserService adminUserService;

    @CrossOrigin
    @PostMapping("api/adminlogin")
    public Result adminlogin(@RequestBody AdminUser adminUser)
    {
        String adminname = adminUser.getAdminname();
        String password = adminUser.getPassword();
        System.out.println(adminname);
        System.out.println(password);
       AdminUser adminUser1 = adminUserService.get(adminname,password);
       if (null == adminUser1)
       {
           return ResultFactory.buildFailResult("登陆失败");
       }else
       {
           return ResultFactory.buildSuccessResult("成功");
       }

    }
}
