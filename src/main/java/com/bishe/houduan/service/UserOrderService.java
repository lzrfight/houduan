package com.bishe.houduan.service;

import com.bishe.houduan.dao.UserOrderDAO;
import com.bishe.houduan.pojo.Shopping;
import com.bishe.houduan.pojo.User;
import com.bishe.houduan.pojo.UserOrder;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderService {
    @Autowired
    UserOrderDAO userOrderDAO;
    @Autowired
    UserService userService;
    public  List<UserOrder> list()
    {
        return userOrderDAO.findAll();
    }
    public void addupdate(UserOrder userOrder)
    {
        userOrderDAO.save(userOrder);
    }
//    根据连锁店进行查询
    public List<UserOrder> findbyshopname(String shopname)
    {
            return userOrderDAO.findAllByShopname(shopname);
    }
    public  List<UserOrder> findbyusername(String username)
    {
        return userOrderDAO.findAllByUsername(username);
    }
    //支付的处理
    public boolean pay(List<Shopping> shoppingusername)
    {
        int summoney = 0;
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        for (int i=0; i<shoppingusername.size();i++)
        {
            Shopping shopping = shoppingusername.get(i);
            int money = shopping.getMoney();
            summoney = summoney+money;
        }
        User user = userService.getByName(username);
        int usermoney = user.getMoney();
        if (usermoney>=summoney)
        {
            int remain = usermoney - summoney;
            user.setMoney(remain);
            userService.add(user);
            return true;
        }else
            return false;
    }
}
