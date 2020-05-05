package com.bishe.houduan.controller;

import com.bishe.houduan.chart.OrderChart;
import com.bishe.houduan.chart.OrderPie;
import com.bishe.houduan.pojo.UserOrder;
import com.bishe.houduan.service.AdminUserRoleService;
import com.bishe.houduan.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class StatisticsController
{
    @Autowired
    UserOrderService userOrderService;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @GetMapping("/api/orderchart")
    public List<OrderChart> list() {
//        先取到所有订单
        List<UserOrder> allorder = userOrderService.list();
//        创建前端所需要上传的chartlist
        ArrayList<OrderChart> chartdata = new ArrayList<OrderChart>();

//       遍历所有订单
        for (int i=0; i<allorder.size();i++) {
            //        两个计数器 全部置为0 一个线上 一个线下
            int onlineamount = 0;
            int offlineamount = 0;
//            取一个对象出来
            UserOrder userOrder = allorder.get(i);
//          得到这个订单对应的角色id
            String username = userOrder.getUsername();
            int rid = adminUserRoleService.findrole(username);
//            取出这个订单时间
            Date time = userOrder.getTime();
//          格式化到天
            SimpleDateFormat timetoday = new SimpleDateFormat("yyyy-MM-dd");
            String timeday = timetoday.format(time);
            OrderChart orderChart = new OrderChart();
//         遍历订单列表里所有订单进行检索有没有同天的订单
            for (int j = i + 1; j < allorder.size(); j++) {
                UserOrder alluserorder = allorder.get(j);
                Date alltime = alluserorder.getTime();
                String alltimeday = timetoday.format(alltime);
                String allusername = alluserorder.getUsername();
                int allrid = adminUserRoleService.findrole(allusername);
                if (timeday.equals(alltimeday)) {
                    if (allrid == 1) {
                        orderChart.setOnlineorder(onlineamount + 1);
                    }
//            如果不是 就是线下
                    else {
                        orderChart.setOnlineorder(offlineamount + 1);
                    }
                }
//                这里肯定有问题
//                if (!timeday.equals(alltimeday) && )
//                {
//
//                }
                if (j + 1 == allorder.size()) {
                    orderChart.setOrdertime(timeday);
                    chartdata.add(orderChart);
                }

            }
        }

//        时间操作不懂行不行
//          SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//       SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss SSS");
//        Rows rows=new Rows();
//        rows.setName("未出租房屋");
//        rows.setNumber(houseService.noIsOrder(0));
//        Rows rox=new Rows();
//        rox.setName("已出租房屋");
//        rox.setNumber(houseService.noIsOrder(1));
//        ArrayList temp=new ArrayList();
//        temp.add(rows);
//        temp.add(rox);
//        return temp;
//        return list;
        return chartdata;
    }
    @GetMapping("/api/orderpie")
    public List<OrderPie> listpie()
    {
        OrderPie orderPie = new OrderPie();
        orderPie.setName("线上订单");
        orderPie.setNumber(userOrderService.onlineorder());
        OrderPie orderPie1 = new OrderPie();
        orderPie1.setName("线下订单");
        orderPie1.setNumber(userOrderService.offlineorder());
        ArrayList<OrderPie> temp= new ArrayList<OrderPie>();
        temp.add(orderPie);
        temp.add(orderPie1);
        return temp;
    }
}
