package com.bishe.houduan.dao;

import com.bishe.houduan.pojo.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingDAO extends JpaRepository<Shopping,Integer> {
    List<Shopping> findAll();
    List<Shopping> findAllByUid(int uid);
//    Shopping findAllByUid(int uid);
//    Shopping findAllByusername(String username);
    List<Shopping> findAllByname(String foodname);
    void deleteAllById(int id);
    void deleteAllByname(String foodname);
}
