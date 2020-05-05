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
    @Autowired
    UserService userService;
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
//    统计需要调用 根据用户查询角色
    public int findrole(String username)
    {
//        根据username查到user对象 获得userid
        User user = userService.getByName(username);
        int uid = user.getId();
        AdminUserRole adminUserRole = adminUserRoleDAO.findByUid(uid);
//        找到对应的角色值返回
        return adminUserRole.getRid();
    }
}
