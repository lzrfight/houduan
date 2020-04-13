package com.bishe.houduan.dao;

import com.bishe.houduan.pojo.Shopping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingDAO extends JpaRepository<Shopping,Integer> {
    List<Shopping> findAll();
}
