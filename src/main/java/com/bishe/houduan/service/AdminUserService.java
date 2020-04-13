package com.bishe.houduan.service;

import com.bishe.houduan.dao.AdminUserDAO;
import com.bishe.houduan.pojo.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {
    @Autowired
    AdminUserDAO adminUserDAO;
    public boolean isExist(String adminname)
    {
        AdminUser adminUser = getByAdminname(adminname);
        return null != adminUser;
    }
    public AdminUser getByAdminname(String adminname)
    {
        return adminUserDAO.findByAdminname(adminname);
    }
    public AdminUser get(int id)
    {
        AdminUser aid = adminUserDAO.findById(id).orElse(null);
        return aid;
    }
    public AdminUser get(String adminname, String password)
    {
        return adminUserDAO.getByAdminnameAndPassword(adminname,password);
    }

}
