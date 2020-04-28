package com.bishe.houduan.service;

import com.bishe.houduan.dao.OrderDetailDAO;
import com.bishe.houduan.pojo.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDetailService {
    @Autowired
    OrderDetailDAO orderDetailDAO;
    public List<OrderDetail> list(int orderid)
    {
        return orderDetailDAO.findAllByOrderid(orderid);
    }
    public void addorupdate(OrderDetail orderDetail)
    {
        orderDetailDAO.save(orderDetail);
    }
}
