//package com.bishe.houduan.service;
//
//import com.bishe.houduan.dao.AdminRoleDAO;
//import com.bishe.houduan.pojo.AdminRole;
//import com.bishe.houduan.pojo.AdminUserRole;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class AdminRoleService {
//    @Autowired
//    AdminRoleDAO adminRoleDAO;
//    @Autowired
//    UserService userService;
//    @Autowired
//    AdminUserRoleService adminUserRoleService;
//    @Autowired
//    AdminMenuService adminMenuService;
//
////    public List<AdminRole> listWithPermsAndMenus() {
////        List<AdminRole> roles = adminRoleDAO.findAll();
////        List<AdminMenu> menus;
////        for (AdminRole role : roles) {
////            perms = adminPermissionService.listPermsByRoleId(role.getId());
////            menus = adminMenuService.getMenusByRoleId(role.getId());
////            role.setPerms(perms);
////            role.setMenus(menus);
////        }
////        return roles;
////    }
//
//    public List<AdminRole> findAll() {
//        return adminRoleDAO.findAll();
//    }
//
//
//    public void addOrUpdate(AdminRole adminRole) {
//        adminRoleDAO.save(adminRole);
//    }
//
//    public List<AdminRole> listRolesByUser(String username) {
//        int uid = userService.getByName(username).getId();
//        List<Integer> rids = adminUserRoleService.listAllByUid(uid)
//                .stream().map(AdminUserRole::getRid).collect(Collectors.toList());
//        return adminRoleDAO.findAllById(rids);
//    }
//
////    public AdminRole updateRoleStatus(AdminRole role) {
////        AdminRole roleInDB = adminRoleDAO.findById(role.getId());
////        roleInDB.setEnabled(role.isEnabled());
////        return adminRoleDAO.save(roleInDB);
////    }
////
////    public void editRole(@RequestBody AdminRole role) {
////        adminRoleDAO.save(role);
////        adminRolePermissionService.savePermChanges(role.getId(), role.getPerms());
////    }
//}
