package com.bishe.houduan.dao;

import com.bishe.houduan.pojo.DealRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DealDAO extends JpaRepository<DealRecord,Integer> {
    List<DealRecord> findAllByUserid(int uid);
}
