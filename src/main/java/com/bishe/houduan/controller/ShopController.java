package com.bishe.houduan.controller;

import com.bishe.houduan.pojo.Shop;
import com.bishe.houduan.pojo.User;
import com.bishe.houduan.result.Result;
import com.bishe.houduan.result.ResultFactory;
import com.bishe.houduan.service.AdminUserRoleService;
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
    @GetMapping("api/shop")
    public List<Shop> list() throws Exception {
        return shopService.list();
    }
    @PostMapping("api/addshop")
    public Result addorupdate(@RequestBody Shop shop)throws Exception{
        shop.setName(shop.getName());
        shop.setAddress(shop.getAddress());
        shop.setPhone(shop.getPhone());
        String username = shop.getUsername();
        String password =shop.getPassword();
        shop.setUsername(username);
        shop.setPassword(password);

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
        return ResultFactory.buildSuccessResult("成功");
    }
    @PostMapping("api/deleteshop")
    public void deleteshop(@RequestBody Shop shop)throws Exception
    {
        int id = shop.getId();
        shopService.deleteshop(id);
    }
}
