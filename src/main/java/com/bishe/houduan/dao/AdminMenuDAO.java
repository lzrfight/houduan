package com.bishe.houduan.dao;

import com.bishe.houduan.pojo.AdminMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminMenuDAO extends JpaRepository<AdminMenu,Integer> {
    AdminMenu findAllById(int id);
    List<AdminMenu>findAllByParentId(int ParentId);
}
