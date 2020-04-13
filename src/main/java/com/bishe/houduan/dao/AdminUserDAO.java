package com.bishe.houduan.dao;

import com.bishe.houduan.pojo.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserDAO extends JpaRepository<AdminUser,Integer> {
    AdminUser findByAdminname(String adminname);
    AdminUser getByAdminnameAndPassword(String adminname, String password);
}
