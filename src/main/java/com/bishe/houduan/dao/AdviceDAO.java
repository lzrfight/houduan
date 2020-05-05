package com.bishe.houduan.dao;

import com.bishe.houduan.pojo.Advice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdviceDAO extends JpaRepository<Advice,Integer> {
    List<Advice> findAllByShopname(String shopname);
}
