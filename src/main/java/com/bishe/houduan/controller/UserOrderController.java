package com.bishe.houduan.controller;

import com.bishe.houduan.pojo.UserOrder;
import com.bishe.houduan.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserOrderController {
    @Autowired
    UserOrderService userOrderService;
//    查询订单 后台页面可使用
    @GetMapping("api/order")
    public List<UserOrder> list() throws Exception {
        return userOrderService.list();
    }
//    添加订单的接口
    @CrossOrigin
    @PostMapping("api/userorder")
    public UserOrder addorupdate(@RequestBody UserOrder userOrder)throws Exception{
        String cheng = userOrder.getCaole();
        userOrder.setCaole(cheng);
        userOrderService.addupdate(userOrder);
        return userOrder;
    }
}
