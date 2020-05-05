package com.bishe.houduan.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
@Data
@ToString
@Entity
@Table(name = "shop")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @ExcelProperty("地址")
    String address;
    @ExcelProperty("管理员姓名")
    String username;
    @ExcelProperty("管理员电话")
    String phone;
    @ExcelProperty("管理员密码")
    String password;
    @ExcelProperty("店名")
    String name;
    int enabled;
}
