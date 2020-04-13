//package com.bishe.houduan.service;
//
//import com.bishe.houduan.dao.AdminRoleDAO;
//import com.bishe.houduan.pojo.AdminMenu;
//import com.bishe.houduan.pojo.AdminRole;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AdminRoleService {
//    @Autowired
//    AdminRoleDAO adminRoleDAO;
//    @Autowired
//    AdminUserService adminUserService;
//    @Autowired
//    AdminUserRoleService adminUserRoleService;
//    @Autowired
//    AdminMenuService adminMenuService;
//    public List<AdminRole> listWithAndMenus()
//    {
//        List<AdminRole> roles = adminRoleDAO.findAll();
//        List<AdminMenu>menus;
//        for (AdminRole role : roles)
//        {
//            menus = adminMenuService.getMenusByRoleId(role.getId());
//            ;
//        }
//        return roles;
//    }
//    public List<AdminRole> findAll()
//    {
//        return adminRoleDAO.findAll();
//    }
//    public void addOrUpdate(AdminRole adminRole)
//    {
//        adminRoleDAO.save(adminRole);
//    }
//    public List<AdminRole>listRolesByUser(String adminname)
//    {
//        int Aid = adminUserService.getByAdminname(adminname).getId();
//        roleInDB.setEnabled(role.isEnabled());
//        return adminRoleDAO.save(roleInDB);
//    }
//}
