package com.bishe.houduan.service;

import com.bishe.houduan.dao.UserOrderDAO;
import com.bishe.houduan.pojo.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOrderService {
    @Autowired
    UserOrderDAO userOrderDAO;
    public  List<UserOrder> list()
    {
        return userOrderDAO.findAll();
    }
    public void addupdate(UserOrder userOrder)
    {
        userOrderDAO.save(userOrder);
    }
}
