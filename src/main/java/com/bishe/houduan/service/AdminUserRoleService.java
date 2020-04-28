package com.bishe.houduan.service;

import com.bishe.houduan.dao.AdminUserRoleDAO;
import com.bishe.houduan.pojo.AdminUserRole;
import com.bishe.houduan.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserRoleService {
    @Autowired
    AdminUserRoleDAO adminUserRoleDAO;

    public List<AdminUserRole> listAllByUid(int uid) {

        return adminUserRoleDAO.findAllByUid(uid);
    }
//    注册时调用他注册对应角色
    public void add(User user)
    {
        AdminUserRole adminUserRole = new AdminUserRole();
        int userId = user.getId();
        System.out.println(userId);
        int rid = 1;
        adminUserRole.setUid(userId);
        adminUserRole.setRid(rid);
        adminUserRoleDAO.save(adminUserRole);
    }
    public void addshopadmin(User user)
    {
        AdminUserRole adminUserRole = new AdminUserRole();
        int userId = user.getId();
        System.out.println(userId);
        int rid = 3;
        adminUserRole.setUid(userId);
        adminUserRole.setRid(rid);
        adminUserRoleDAO.save(adminUserRole);
    }
}
