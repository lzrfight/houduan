package com.bishe.houduan.controller;

import com.bishe.houduan.pojo.Food;
import com.bishe.houduan.pojo.Shopping;
import com.bishe.houduan.result.Result;
import com.bishe.houduan.result.ResultFactory;
import com.bishe.houduan.service.FoodService;
import com.bishe.houduan.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {
    @Autowired
    FoodService foodService;
    @Autowired
    ShoppingService shoppingService;

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
    @GetMapping("/api/shopping")
    public List<Shopping> listshit() throws Exception {
        return shoppingService.list();
    }
   @CrossOrigin
    @PostMapping("/api/addshopping")
    public Result addshopping(@RequestBody Shopping shopping)
   {
       String name = shopping.getName();
       int money = shopping.getMoney();
       int amount = 1;
       System.out.println(money);
       System.out.println(name);
       shopping.setName(name);
       shopping.setMoney(money);
       shopping.setAmount(amount);
       shoppingService.add(shopping);
       return ResultFactory.buildSuccessResult(shopping);
   }
}