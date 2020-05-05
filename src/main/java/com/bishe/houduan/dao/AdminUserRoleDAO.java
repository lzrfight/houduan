package com.bishe.houduan.dao;

import com.bishe.houduan.pojo.AdminUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminUserRoleDAO extends JpaRepository<AdminUserRole,Integer> {
    List<AdminUserRole> findAllByUid(int uid);
    AdminUserRole findByUid(int uid);
}
