package com.bishe.houduan.service;

import com.bishe.houduan.dao.AdminUserRoleDAO;
import com.bishe.houduan.dao.UserOrderDAO;
import com.bishe.houduan.pojo.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserOrderService {
    @Autowired
    UserOrderDAO userOrderDAO;
    @Autowired
    UserService userService;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    ShoppingService shoppingService;
    @Autowired
    ShopService shopService;
    @Autowired
    AdminUserRoleDAO adminUserRoleDAO;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    public  List<UserOrder> list()
    {
        return userOrderDAO.findAll();
    }
    public void addupdate(UserOrder userOrder)
    {
        userOrderDAO.save(userOrder);
    }
//    根据连锁店进行查询
    public List<UserOrder> findbyshopname(String shopname)
    {
            return userOrderDAO.findAllByShopname(shopname);
    }
    public  List<UserOrder> findbyusername(String username)
    {
        return userOrderDAO.findAllByUsername(username);
    }
    //支付的处理
    public boolean pay(List<Shopping> shoppingusername)
    {
        int summoney = 0;
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        for (int i=0; i<shoppingusername.size();i++)
        {
            Shopping shopping = shoppingusername.get(i);
            int money = shopping.getMoney();
            summoney = summoney+money;
        }
        User user = userService.getByName(username);
        int usermoney = user.getMoney();
        if (usermoney>=summoney)
        {
            int remain = usermoney - summoney;
            user.setMoney(remain);
            userService.add(user);
            DealRecord dealRecord = new DealRecord();
            dealRecord.setUserid(user.getId());
            dealRecord.setOperation("付款"+summoney);
            dealRecord.setAccount(remain);
            Date currentDate = new Date(System.currentTimeMillis());
            dealRecord.setOperationtime(currentDate);
            userService.addorupdaterecord(dealRecord);
            return true;
        }else
            return false;
    }
//订单里菜品和库存的处理,用户下单 库存自己减
    public void dealremain(UserOrder order)
    {
//        先得到shopid
        String result = null;
        String shopname = order.getShopname();
        Shop shop = shopService.findbyshopname(shopname);
        int shopid = shop.getId();
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        List<Shopping> shoppingusername = shoppingService.list(username);
        List<Inventory> shopInventory = inventoryService.listbyshopid(shopid);
        for (int i = 0; i<shoppingusername.size();i++)
        {
            Shopping shopping = shoppingusername.get(i);
            String orderfoodname = shopping.getName();
            int orderamount = shopping.getAmount();
           for (int j = 0; j<shopInventory.size();j++)
           {
               Inventory inventory = shopInventory.get(j);
               String inventoryfoodname = inventory.getFoodname();
               int inventoryamount = inventory.getAmount();
               if (orderfoodname.equals(inventoryfoodname))
               {
                   int remain  = inventoryamount-orderamount;
                   inventory.setAmount(remain);
                   inventoryService.addorupdate(inventory);
               }
           }
        }
    }
    public int findrole(String username)
    {
        User user = userService.getByName(username);
        int uid = user.getId();
       AdminUserRole adminUserRole = adminUserRoleDAO.findByUid(uid);
       int role = adminUserRole.getRid();
       return role;
    }
//    统计线上线下订单
    public int onlineorder()
    {
        List<UserOrder> allorder = list();
        int onlineorder = 0;
//        遍历所有订单
        for (int i=0; i<allorder.size();i++) {
//            取一个对象出来
            UserOrder userOrder = allorder.get(i);
//          得到这个订单对应的角色id
            String username = userOrder.getUsername();
            int rid = adminUserRoleService.findrole(username);
            if (rid == 1)
            {
                onlineorder = onlineorder+1;
            }
        }
        return onlineorder;
    }
    public  int offlineorder()
    {
        List<UserOrder> allorder = list();
        int offlineorder = 0;
//        遍历所有订单
        for (int i=0; i<allorder.size();i++) {
//            取一个对象出来
            UserOrder userOrder = allorder.get(i);
//          得到这个订单对应的角色id
            String username = userOrder.getUsername();
            int rid = adminUserRoleService.findrole(username);
            if (rid == 3)
            {
                offlineorder = offlineorder+1;
            }
        }
        return offlineorder;
    }
}
