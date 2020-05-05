package com.bishe.houduan.dao;

import com.bishe.houduan.pojo.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopDAO extends JpaRepository<Shop,Integer> {
    List<Shop> findAll();
    List<Shop> findAllByEnabled(int enabled);
    Shop findAllByUsername(String username);
    void deleteAllById(int id);
    Shop findAllById(int id);
    Shop findAllByName(String shopname);
}
