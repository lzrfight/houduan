package com.bishe.houduan.controller;

import com.bishe.houduan.pojo.User;
import com.bishe.houduan.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {
    @Autowired
    UserService userService;
    @CrossOrigin
    @PostMapping("api/findmoney")
    public int money() throws Exception
    {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user= userService.getByName(username);
        int money = user.getMoney();
        System.out.println(money);
        return money;
    }
    @GetMapping("api/30")
    public void money30()
    {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getByName(username);
        int money = user.getMoney();
        int addmoney = money+30;
        user.setMoney(addmoney);
        userService.add(user);
    }
    @GetMapping("api/60")
    public void money60()
    {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getByName(username);
        int money = user.getMoney();
        int addmoney = money+60;
        user.setMoney(addmoney);
        userService.add(user);
    }
    @GetMapping("api/150")
    public void money150()
    {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getByName(username);
        int money = user.getMoney();
        int addmoney = money+150;
        user.setMoney(addmoney);
        userService.add(user);
    }
    @GetMapping("api/300")
    public void money300()
    {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getByName(username);
        int money = user.getMoney();
        int addmoney = money+300;
        user.setMoney(addmoney);
        userService.add(user);
    }
    @GetMapping("api/600")
    public void money600()
    {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getByName(username);
        int money = user.getMoney();
        int addmoney = money+600;
        user.setMoney(addmoney);
        userService.add(user);
    }
}
