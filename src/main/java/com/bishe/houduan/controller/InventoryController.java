package com.bishe.houduan.controller;

import com.bishe.houduan.pojo.Inventory;
import com.bishe.houduan.result.Result;
import com.bishe.houduan.result.ResultFactory;
import com.bishe.houduan.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventoryController {
@Autowired
    InventoryService inventoryService;
    @GetMapping("api/inventory")
    public List<Inventory> list()
    {
       return inventoryService.list();
    }
    @PostMapping("api/upfood")
    public Result upfood(@RequestBody Inventory inventory)
    {
        int id = inventory.getId();
        int amount = inventory.getAmount();
        Inventory inventory1 = inventoryService.findbyid(id);
        inventory1.setAmount(amount);
        inventoryService.addorupdate(inventory1);
        return ResultFactory.buildSuccessResult("成功");
    }
}
