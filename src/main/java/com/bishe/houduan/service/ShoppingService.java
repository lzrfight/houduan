package com.bishe.houduan.service;

import com.bishe.houduan.dao.ShoppingDAO;
import com.bishe.houduan.dao.UserDAO;
import com.bishe.houduan.pojo.Shopping;
import com.bishe.houduan.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service
public class ShoppingService {
    @Autowired
    ShoppingDAO shoppingDAO;
    @Autowired
    UserService userService;
    @Autowired
    UserDAO userDAO;
  public List<Shopping> list(String username)
  {
      User user = userDAO.findByUsername(username);
      int uid = user.getId();
      return shoppingDAO.findAllByUid(uid);
  }
//  对数据库进行处理 一个一个找食品名称 然后把数量和金额加在一起，实现对购物车数据统计
  public void tidyshopping(Shopping shopping)
  {
      String username = SecurityUtils.getSubject().getPrincipal().toString();
      User user = userService.getByName(username);
      int uid = user.getId();
      String foodname = shopping.getName();
      int money = shopping.getMoney();
      int amount = shopping.getAmount();
      List<Shopping>dirtylist = list(username);
      if (dirtylist.size()!=0) {
          for (int i = 0; i <dirtylist.size(); i++) {
              Shopping shopping1 = dirtylist.get(i);
              String DBfoodname = shopping1.getName();
              System.out.println("Dbfoodname "+DBfoodname);
              if (DBfoodname == null)
              {
                  shopping.setUid(uid);
                  shopping.setName(foodname);
                  shopping.setAmount(amount);
              }
              int DBmoney = shopping1.getMoney();
              int DBamount = shopping1.getAmount();
              int id = shopping1.getId();
              if (foodname.equals(DBfoodname))
              {
                  int sumamount = DBamount+amount;
                  int summoney = DBmoney+money;
                  shopping.setAmount(sumamount);
                  shopping.setMoney(summoney);
                  shopping.setUid(uid);
                  addshopping(shopping);
                  deletebyid(id);
              }else
              {
                  shopping.setMoney(money);
                  shopping.setAmount(amount);
                  shopping.setName(foodname);
                  shopping.setUid(uid);
                  addshopping(shopping);
              }
          }
      }
      else {
          shopping.setAmount(amount);
          shopping.setName(foodname);
          shopping.setUid(uid);
          addshopping(shopping);
      }

  }
  public void addshopping(Shopping shopping)
  {

      shoppingDAO.save(shopping);
  }
  public void deletebyid(int id)
  {
    shoppingDAO.deleteAllById(id);
  }
//  这里对应购物车的删操作，这里删操作还没弄好要根据数据删
  public void deleytebyfoodname(Shopping shopping)
  {
      String username = SecurityUtils.getSubject().getPrincipal().toString();
      String foodname = shopping.getName();
      List<Shopping>listuser = list(username);
      for (int i=0; i<listuser.size(); i++)
      {
           Shopping shopping1 = listuser.get(i);
           String DBfoodname = shopping1.getName();
           int shoppingid = shopping1.getId();
           if (DBfoodname.equals(foodname))
           {
               deletebyid(shoppingid);
           }
      }
  }
//  这里写清空购物车功能,这里判空没写好
    public void clearshopping()
    {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        List<Shopping>usershopping = list(username);
        for (int i=0; i<usershopping.size();i++)
        {
            Shopping shopping = usershopping.get(i);
            int id = shopping.getId();
            deletebyid(id);
        }
    }
}
