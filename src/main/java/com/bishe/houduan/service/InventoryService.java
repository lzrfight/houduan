package com.bishe.houduan.service;

import com.bishe.houduan.dao.InventoryDAO;
import com.bishe.houduan.dao.ShopDAO;
import com.bishe.houduan.pojo.Food;
import com.bishe.houduan.pojo.Inventory;
import com.bishe.houduan.pojo.Shop;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    InventoryDAO inventoryDAO;
    @Autowired
    ShopDAO shopDAO;
    @Autowired
    FoodService foodService;

    public List<Inventory> list() {
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        Shop shop = shopDAO.findAllByUsername(username);
        int shopid = shop.getId();
        return inventoryDAO.findAllByShopid(shopid);
    }

    public List<Inventory> listbyshopid(int shopid)
    {
        return inventoryDAO.findAllByShopid(shopid);
    }

    public void addorupdate(Inventory inventory)
    {

        inventoryDAO.save(inventory);
    }
    public Inventory findbyid(int id)
    {

        return inventoryDAO.findAllById(id);
    }
//    创建新shop即可创建新库存，此service必须加载更新完shop之后
    public void newinventory(Shop shop) {
        int shopid = shop.getId();

        List<Food> foodList = foodService.list();
        for (int i = 0; i < foodList.size(); i++)
        {
            Food food = foodList.get(i);
            Inventory inventory = new Inventory();
            inventory.setFoodname(food.getName());
            inventory.setShopid(shopid);
            inventory.setAmount(0);
            inventoryDAO.save(inventory);
        }
    }
}
