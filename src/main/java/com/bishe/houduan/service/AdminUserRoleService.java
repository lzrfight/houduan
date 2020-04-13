package com.bishe.houduan.service;

import com.bishe.houduan.dao.AdminUserRoleDAO;
import com.bishe.houduan.pojo.AdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserRoleService {
    @Autowired
    AdminUserRoleDAO adminUserRoleDAO;

    public List<AdminUserRole> listAllByAid(int aid) {
        return adminUserRoleDAO.findAllByAid(aid);
    }
}
//    @Modifying
//    @Transactional
//    public void saveRoleChanges(int aid, List<AdminRole>roles)
//    {
//        adminUserRoleDAO.deleteAllByAid(aid);
//        List<AdminUserRole>urs = new ArrayList<>();
//        roles.forEach(r ->
//        {
//            AdminUserRole ur = new AdminUserRole();
//            ur.setAid(aid);
//            ur.setRid(r.getId());
//            urs.add(ur);
//        });
//        adminUserRoleDAO.saveAll(urs);
//    }
//}
