package com.bishe.houduan.dao;

import com.bishe.houduan.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailDAO extends JpaRepository<OrderDetail,Integer> {
List<OrderDetail> findAllByOrderid(int orderid);
}
