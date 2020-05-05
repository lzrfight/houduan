package com.bishe.houduan.controller;

import com.bishe.houduan.dao.DealDAO;
import com.bishe.houduan.pojo.*;
import com.bishe.houduan.result.Result;
import com.bishe.houduan.result.ResultFactory;
import com.bishe.houduan.service.*;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class UserOrderController {
    @Autowired
    UserOrderService userOrderService;
    @Autowired
    UserService userService;
    @Autowired
    ShopService shopService;
    @Autowired
    OrderDetailService orderDetailService;
    @Autowired
    ShoppingService shoppingService;
    @Autowired
    DealDAO dealDAO;
//    查询订单 后台页面可使用
    @PostMapping("api/order")
    public List<UserOrder> list() throws Exception {
        return userOrderService.list();
    }
//    用户结算之后的订单 并且菜品加入详情表里
    @CrossOrigin
    @PostMapping("api/userorder")
    public Result addorupdate(@RequestBody UserOrder userOrder)throws Exception{
        System.out.println(userOrder);
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        if (userOrderService.findrole(username) == 3)
        {
            List<Shopping> shoppingusername = shoppingService.list(username);
            //通过订单获得连锁店名字
            String shopname= userOrder.getShopname();
            //通过订单获得时间
            Date time = userOrder.getTime();
            //以下就是几个信息加入数据库
            userOrder.setTime(time);
            System.out.println(time);
            System.out.println(shopname);
            userOrder.setUsername(username);
            userOrder.setShopname(shopname);
            userOrderService.addupdate(userOrder);
            //获取订单id
            int orderid =  userOrder.getId();
            for (int i = 0; i < shoppingusername.size(); i++) {
                System.out.println("进入for循环");
                Shopping shopping2 = shoppingusername.get(i);
                String foodname = shopping2.getName();
                int amount = shopping2.getAmount();
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setFoodname(foodname);
                orderDetail.setAmount(amount);
                orderDetail.setOrderid(orderid);
                orderDetailService.addorupdate(orderDetail);
                System.out.println(orderDetail);
            }
            userOrderService.dealremain(userOrder);
            DealRecord dealRecord = new DealRecord();
            return ResultFactory.buildSuccessResult(orderid);
        }
        //通过username获得购物车信息
        List<Shopping> shoppingusername = shoppingService.list(username);
        boolean ablepay = userOrderService.pay(shoppingusername);
        if (ablepay == false)
        {
            return ResultFactory.buildFailResult("余额不足请前往个人中心充值");
        }else {
        //通过订单获得连锁店名字
        String shopname= userOrder.getShopname();
        //通过订单获得时间
            Date time = userOrder.getTime();
        //以下就是几个信息加入数据库
        userOrder.setTime(time);
        System.out.println(time);
        System.out.println(shopname);
        userOrder.setUsername(username);
        userOrder.setShopname(shopname);
        userOrderService.addupdate(userOrder);
        //获取订单id
        int orderid =  userOrder.getId();
        //遍历循环数组加入订单详情
        for (int i = 0; i < shoppingusername.size(); i++) {
            System.out.println("进入for循环");
            Shopping shopping2 = shoppingusername.get(i);
            String foodname = shopping2.getName();
            int amount = shopping2.getAmount();
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setFoodname(foodname);
            orderDetail.setAmount(amount);
            orderDetail.setOrderid(orderid);
            orderDetailService.addorupdate(orderDetail);
            System.out.println(orderDetail);
        }
        userOrderService.dealremain(userOrder);
        DealRecord dealRecord = new DealRecord();
        return ResultFactory.buildSuccessResult(orderid);
        }
    }

//    这里是可用的添加订单的接口
//    @CrossOrigin
//    @PostMapping("api/userorder")
//    public UserOrder addorupdate(@RequestBody UserOrder userOrder)throws Exception{
//        String username = SecurityUtils.getSubject().getPrincipal().toString();
//        String shopname= userOrder.getShopname();
//        Date time = userOrder.getTime();
//        userOrder.setTime(time);
//        System.out.println(time);
//        System.out.println(shopname);
//        userOrder.setUsername(username);
//        userOrder.setShopname(shopname);
//        userOrderService.addupdate(userOrder);
//        return userOrder;
//    }
//    这里是动态加载哪家连锁店(选择那里)
    @CrossOrigin
    @GetMapping("api/findshop")
  public List<Shop> shopname()throws Exception
    {
      return shopService.list();
    }
//    查询orderdetail这里要根据订单号查询detail了
    @CrossOrigin
    @PostMapping("api/orderdetail")
  public List<OrderDetail>orderDetail(@RequestBody UserOrder userOrder) throws Exception
    {
        int orderid = userOrder.getId();
        return  orderDetailService.list(orderid);
    }
//   这里根据shopid查询订单
    @CrossOrigin
    @PostMapping("api/shoporder")
    public List<UserOrder>userOrder() throws Exception
    {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        Shop shop = shopService.findbyusername(username);
        String shopname = shop.getName();
        return userOrderService.findbyshopname(shopname);
    }
//    这里根据用户id查订单
    @CrossOrigin
    @PostMapping("api/userordertest")
    public List<UserOrder>userOrders()throws Exception
    {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        return userOrderService.findbyusername(username);
    }
//    退单
    @CrossOrigin
    @PostMapping("api/orderback")
    public void orderback(@RequestBody UserOrder userOrder)throws Exception
    {

    }
    @CrossOrigin
    @GetMapping("api/userdeal")
    public List<DealRecord> finduserdeal()throws Exception
    {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getByName(username);
        return dealDAO.findAllByUserid(user.getId());
    }
//    这边实现根据shopname实现查询订单
    @CrossOrigin
    @PostMapping("api/orderbyshop")
    public List<UserOrder> shoporder(@RequestBody Shop shop ) throws Exception
    {
        String shopname = shop.getName();
        return userOrderService.findbyshopname(shopname);
    }
}
