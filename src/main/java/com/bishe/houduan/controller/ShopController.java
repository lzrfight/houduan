package com.bishe.houduan.controller;

import com.bishe.houduan.pojo.Shop;
import com.bishe.houduan.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShopController {
    @Autowired
    ShopService shopService;
    @GetMapping("api/shop")
    public List<Shop> list() throws Exception {
        return shopService.list();
    }

    @PostMapping("api/addshop")
    public Shop addorupdate(@RequestBody Shop shop)throws Exception{
        shopService.add(shop);
        return shop;
    }
}
