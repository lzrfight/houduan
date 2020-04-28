package com.bishe.houduan.dao;

import com.bishe.houduan.pojo.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserOrderDAO  extends JpaRepository<UserOrder,Integer> {
    List<UserOrder> findAll();
    List<UserOrder> findAllByShopname(String shopname);
    List<UserOrder> findAllByUsername(String username);
}
