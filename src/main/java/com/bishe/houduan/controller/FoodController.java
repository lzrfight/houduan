package com.bishe.houduan.controller;

import com.bishe.houduan.pojo.Food;
import com.bishe.houduan.pojo.Shopping;
import com.bishe.houduan.result.Result;
import com.bishe.houduan.result.ResultFactory;
import com.bishe.houduan.service.FoodService;
import com.bishe.houduan.service.ShoppingService;
import com.bishe.houduan.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {
    @Autowired
    FoodService foodService;
    @Autowired
    ShoppingService shoppingService;
    @Autowired
    UserService userService;

    @GetMapping("/api/food")
    public List<Food> list() throws Exception {
        return foodService.list();
    }

    @CrossOrigin
    @PostMapping("/api/food")
    public Food addOrUpdate(@RequestBody Food food) throws Exception {
        foodService.addOrUpdate(food);
        return food;
    }

    @CrossOrigin
    @PostMapping("/api/delete")
    public void delete(@RequestBody Food food) throws Exception {
        foodService.deleteById(food.getId());
    }

    @CrossOrigin
    @GetMapping("/api/categories/{cid}/food")
    public List<Food> listByCategory(@PathVariable("cid") int cid) throws Exception {
//        if (0 != cid) {
        return foodService.listByCategory(cid);
//        } else {
        //return list();
        //}
    }

    @CrossOrigin
    @PostMapping("/api/shopping")
    public List<Shopping> listByUser() throws Exception {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println(username);
        return shoppingService.list(username);
    }

    //    获取当前用户姓名 然后id 存入数据库 ok
    @CrossOrigin
    @PostMapping("/api/addshopping")
    public Result addshopping(@RequestBody Shopping shopping) {
        shoppingService.tidyshopping(shopping);
        return ResultFactory.buildSuccessResult("成了");

////      之前可以的订单
////     1.获得用户名
//        String username = SecurityUtils.getSubject().getPrincipal().toString();
////   2.从user表中获得user对象
//        User user = userService.getByName(username);
////      3.以及获得uid
//        int uid = user.getId();
////     获得根据用户姓名整理好的shopping
//        shoppingService.list(username);
//        String name = shopping.getName();
//        int money = shopping.getMoney();
//        int amount = 1;
//        System.out.println(money);
//        System.out.println(name);
//        shopping.setName(name);
//        shopping.setMoney(money);
//        shopping.setAmount(amount);
//        shopping.setUid(uid);
//        shoppingService.addshopping(shopping);
//        return ResultFactory.buildSuccessResult(shopping);
    }
//    购物车的删除
    @CrossOrigin
    @PostMapping("api/deletef")
    public Result deletebyfood(@RequestBody Shopping shopping)
    {
        shoppingService.deleytebyfoodname(shopping);
        System.out.println("成了");
        return ResultFactory.buildSuccessResult("成了");
    }
//    这里是提交完购物车要清除 注意清除顺序
    @CrossOrigin
    @GetMapping("api/clearshopping")
    public Result clearshopping()
    {
        shoppingService.clearshopping();
        return ResultFactory.buildSuccessResult("成了");
    }
}
