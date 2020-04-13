package com.bishe.houduan.dao;

import com.bishe.houduan.pojo.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRoleDAO extends JpaRepository<AdminRole, Integer> {
    AdminRole findAllById(int id);

}
