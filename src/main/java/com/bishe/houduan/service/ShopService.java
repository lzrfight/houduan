package com.bishe.houduan.service;

import com.bishe.houduan.dao.ShopDAO;
import com.bishe.houduan.pojo.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    @Autowired
    ShopDAO shopDAO;
    public List<Shop> list()
    {
        return shopDAO.findAll();
    }
    public void add(Shop shop)
    {
        shopDAO.save(shop);
    }
//    根据username查找shopname来显示数据
    public Shop findbyusername(String username)
    {
        return shopDAO.findAllByUsername(username);
    }
    public void deleteshop(int id)
    {
        shopDAO.deleteAllById(id);
    }
}
