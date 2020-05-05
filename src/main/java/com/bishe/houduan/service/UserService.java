package com.bishe.houduan.service;

import com.bishe.houduan.dao.DealDAO;
import com.bishe.houduan.dao.UserDAO;
import com.bishe.houduan.pojo.DealRecord;
import com.bishe.houduan.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    DealDAO dealDAO;
    public boolean isExist(String username)
    {
        User user = getByName(username);
                return null !=user;
    }

    public User getByName(String username) {

        return userDAO.findByUsername(username);
    }
    public User get(String username, String password)
    {

        return userDAO.getByUsernameAndPassword(username,password);
    }
    public void add(User user)
    {
        userDAO.save(user);
    }
    public User get(int id)
    {
        User u = userDAO.findById(id).orElse(null);
        return u;
    }
    public void deletebyId(int id)
    {
        userDAO.deleteById(id);
    }
//    充值放入记录表
    public void addorupdaterecord(DealRecord dealRecord)
    {
        dealDAO.save(dealRecord);
    }
    public String findusernamebyid(int uid)
    {
        User user = userDAO.findAllById(uid);
        String username = user.getUsername();
        return username;
    }
}
