package com.bishe.houduan.controller;

import com.bishe.houduan.dao.ShopDAO;
import com.bishe.houduan.pojo.Shop;
import com.bishe.houduan.pojo.User;
import com.bishe.houduan.result.Result;
import com.bishe.houduan.result.ResultFactory;
import com.bishe.houduan.service.AdminUserRoleService;
import com.bishe.houduan.service.InventoryService;
import com.bishe.houduan.service.ShopService;
import com.bishe.houduan.service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RestController
public class ShopController {
    @Autowired
    ShopService shopService;
    @Autowired
    UserService userService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    InventoryService inventoryService;
    @Autowired
    ShopDAO shopDAO;
    @GetMapping("api/shop")
    public List<Shop> list() throws Exception {
        return shopService.list();
    }
    @PostMapping("api/addshop")
    public Result addorupdate(@RequestBody Shop shop)throws Exception{
//        增加和修改能写一起：增加如果没有id 会自增 如果有id则会修改对应shopid的值
//        这里找到原来的userid了;
        int uid = shopService.findusername(shop);
        shop.setName(shop.getName());
        shop.setAddress(shop.getAddress());
        shop.setPhone(shop.getPhone());
        System.out.println("前shop.getEnabled"+shop.getEnabled());
        shop.setEnabled(shop.getEnabled());
        System.out.println("后shop.getEnabled"+shop.getEnabled());
        String username = shop.getUsername();
        String password =shop.getPassword();
        shop.setUsername(username);
        shop.setPassword(password);
        String oldusername = userService.findusernamebyid(uid);
        if (username.equals(oldusername))
        {
            User user = new User();
            user.setId(uid);
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            int times = 2;
            String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
            user.setSalt(salt);
            user.setUsername(username);
            user.setPassword(encodedPassword);
            userService.add(user);
            shopService.add(shop);
            return  ResultFactory.buildSuccessResult("成功");
        }
//注册
        if (uid == 0) {
            User user = new User();
            username = HtmlUtils.htmlEscape(username);
            user.setUsername(username);
            boolean exist = userService.isExist(username);
            if (exist) {
                String message = "用户已被使用";
                return ResultFactory.buildFailResult(message);
            }
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            int times = 2;
            String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
            user.setSalt(salt);
            user.setPassword(encodedPassword);
            userService.add(user);
            adminUserRoleService.addshopadmin(user);
            shopService.add(shop);
            inventoryService.newinventory(shop);
            return ResultFactory.buildSuccessResult("成功");
        }
//        这里是为了区分增加和修改
        else
        {
            User user = new User();
            user.setId(uid);
            user.setUsername(username);
            boolean exist = userService.isExist(username);
            if (exist) {
                String message = "用户已被使用";
                return ResultFactory.buildFailResult(message);
            }
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            int times = 2;
            String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
            user.setSalt(salt);
            user.setPassword(encodedPassword);
            userService.add(user);
            shopService.add(shop);
            return ResultFactory.buildSuccessResult("成功");
        }
    }
    @PostMapping("api/deleteshop")
    public void deleteshop(@RequestBody Shop shop)throws Exception
    {
        int id = shop.getId();
        Shop shop1 = shopService.findbyid(id);
       String username = shop1.getUsername();
        User user =   userService.getByName(username);
        shopService.deleteshop(id);
        userService.deletebyId(user.getId());
    }
    @PostMapping("api/address")
    public List<String> address() throws Exception
    {
        List<Shop> shop = shopService.list();
        ArrayList<String> temp=new ArrayList<>();
        String address;
        for (int i = 0; i<shop.size();i++)
        {
            Shop shop1 = shop.get(i);
            address= shop1.getAddress();
            temp.add(address);
        }
        return temp;
    }
    @GetMapping("api/findopenshop")
    public List<Shop> openshop() throws Exception
    {
        return shopDAO.findAllByEnabled(1);
    }
}
