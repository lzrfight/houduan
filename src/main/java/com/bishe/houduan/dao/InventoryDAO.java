package com.bishe.houduan.dao;

import com.bishe.houduan.pojo.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryDAO extends JpaRepository<Inventory,Integer>
{
    Inventory findAllById(int id);
    List<Inventory> findAllByShopid(int shopid);
}
