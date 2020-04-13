package com.bishe.houduan.dao;

import com.bishe.houduan.pojo.AdminMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdminMenuDAO extends JpaRepository<AdminMenu, Integer> {
        AdminMenu findById(int id);
//       根据parentid进行进行查找
        List<AdminMenu> findAllByParentId(int parentId);
        }

