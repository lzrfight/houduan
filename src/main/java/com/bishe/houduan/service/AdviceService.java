package com.bishe.houduan.service;

import com.bishe.houduan.dao.AdviceDAO;
import com.bishe.houduan.pojo.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdviceService {
    @Autowired
    AdviceDAO adviceDAO;
    public List<Advice> advicebyshop(String shopname)
    {
      return adviceDAO.findAllByShopname(shopname);
    }
    public void add(Advice advice)
    {
        adviceDAO.save(advice);
    }
    public List<Advice> alladvice()
    {
        return adviceDAO.findAll();
    }
}
