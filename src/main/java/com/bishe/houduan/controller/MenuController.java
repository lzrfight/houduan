package com.bishe.houduan.controller;

import com.bishe.houduan.pojo.AdminMenu;
import com.bishe.houduan.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MenuController {
    @Autowired
    AdminMenuService adminMenuService;
    @CrossOrigin
    @PostMapping("api/menu")

    public List<AdminMenu> menu()
    {
        return adminMenuService.getMenusByCurrentUser();
    }
}
