package com.bishe.houduan.service;

import com.bishe.houduan.dao.ShopDAO;
import com.bishe.houduan.dao.UserDAO;
import com.bishe.houduan.pojo.Shop;
import com.bishe.houduan.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    @Autowired
    ShopDAO shopDAO;
    @Autowired
    UserDAO userDAO;
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
    public Shop findbyid(int id)
    {
        return shopDAO.findAllById(id);
    }
    public Shop findbyshopname(String shopname)
    {
       return shopDAO.findAllByName(shopname);
    }
    public int findusername(Shop shop) {
        int id = shop.getId();
        if (id != 0) {
            Shop shopbyid = findbyid(id);
            String username = shopbyid.getUsername();
            User user = userDAO.findByUsername(username);
            return user.getId();
        }else {
            return 0;
        }
    }
}
