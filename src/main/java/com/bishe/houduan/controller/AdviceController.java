package com.bishe.houduan.controller;

import com.bishe.houduan.pojo.Advice;
import com.bishe.houduan.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdviceController  {
    @Autowired
    AdviceService adviceService;
    @PostMapping("api/findadvice")
    public List<Advice> advicebyshop(@RequestBody String shopname)
    {
        return adviceService.advicebyshop(shopname);
    }
    @PostMapping("api/comment")
    public void addadvice(@RequestBody Advice advice)
    {
        adviceService.add(advice);
    }
    @PostMapping("api/alladvice")
    public List<Advice> alladvice()
    {
       return adviceService.alladvice();
    }
}
