package com.bishe.houduan.dao;

import com.bishe.houduan.pojo.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopDAO extends JpaRepository<Shop,Integer> {
    List<Shop> findAll();
}
