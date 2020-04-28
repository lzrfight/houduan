package com.bishe.houduan.service;

import com.bishe.houduan.dao.AdminRoleMenuDAO;
import com.bishe.houduan.pojo.AdminRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminRoleMenuService {
    @Autowired
    AdminRoleMenuDAO adminRoleMenuDAO;

    public List<AdminRoleMenu> findAllByRid(int rid) {

        return adminRoleMenuDAO.findAllByRid(rid);
    }

    public List<AdminRoleMenu> findAllByRidIn(List<Integer> rids) {
        return adminRoleMenuDAO.findAllByRidIn(rids);
    }

    public void save(AdminRoleMenu rm) {
        adminRoleMenuDAO.save(rm);
    }

//    @Modifying
//    @Transactional
//    public void updateRoleMenu(int rid, Map<String, List<Integer>> menusIds) {
//        adminRoleMenuDAO.deleteAllByRid(rid);
//        List<AdminRoleMenu> rms = new ArrayList<>();
//        for (Integer mid : menusIds.get("menusIds")) {
//            AdminRoleMenu rm = new AdminRoleMenu();
//            rm.setMid(mid);
//            rm.setRid(rid);
//            rms.add(rm);
//        }
//
//        adminRoleMenuDAO.saveAll(rms);
//    }
}