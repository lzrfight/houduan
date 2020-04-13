package com.bishe.houduan.service;

import com.bishe.houduan.dao.ShoppingDAO;
import com.bishe.houduan.pojo.Shopping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShoppingService {
    @Autowired
    ShoppingDAO shoppingDAO;
    @Autowired
    UserService userService;
  public List<Shopping> list()
  {
      return shoppingDAO.findAll();
  }


  public void add(Shopping shopping)
  {
      shoppingDAO.save(shopping);
  }
}
