package com.bishe.houduan.service;

import com.bishe.houduan.dao.AdminMenuDAO;
import com.bishe.houduan.pojo.AdminMenu;
import com.bishe.houduan.pojo.AdminRoleMenu;
import com.bishe.houduan.pojo.AdminUser;
import com.bishe.houduan.pojo.AdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminMenuService {
    @Autowired
    AdminMenuDAO adminMenuDAO;
//    @Autowired
//    AdminRoleService adminRoleService;
    @Autowired
    AdminMenuService adminMenuService;
    @Autowired
    AdminUserService adminUserService;
    @Autowired
    AdminRoleMenuService adminRoleMenuService;
    @Autowired
    AdminUserRoleService adminUserRoleService;

    public List<AdminMenu> getAllByParentId(int parentId)
    {
        return adminMenuDAO.findAllByParentId(parentId);
    }
    public List<AdminMenu>getMenuByCurrentUser(String adminname)
    {
//        这里要改 我没用到shiro,,试试直接从前端获取用户名过来, 获取当前登陆用户的姓名,然后去查找角色的id,再去查找菜单
//        String adminname = SecurityUtils.getSubject().getPrincipal().toString();
        AdminUser adminUser = adminUserService.getByAdminname(adminname);

        List<Integer> rids = adminUserRoleService.listAllByAid(adminUser.getId())
                .stream().map(AdminUserRole::getRid).collect(Collectors.toList());
        List<Integer> menuIds = adminRoleMenuService.findAllByRidIn(rids)
                .stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDAO.findAllById(menuIds).stream().distinct().collect(Collectors.toList());

        //处理菜单的表结构
        handleMenus(menus);
        return menus;
    }
//    根据角色id查询菜单
    public List<AdminMenu>getMenusByRoleId(int rid)
    {
        List<Integer>menuIds = adminRoleMenuService.findAllByRid(rid).stream().map(AdminRoleMenu::getMid).collect(Collectors.toList());
        List<AdminMenu> menus = adminMenuDAO.findAllById(menuIds);
        handleMenus(menus);
        return menus;
    }
//    处理菜单,这里还看不明白 实现功能回头再看
    public  void handleMenus(List<AdminMenu>menus)
    {
        menus.forEach(m->
        {
            List<AdminMenu>children = getAllByParentId(m.getId());
            m.setChildren(children);
        });
        menus.removeIf(m->m.getParentId()!= 0);
    }
}

