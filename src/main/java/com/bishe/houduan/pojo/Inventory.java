package com.bishe.houduan.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "inventory")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String foodname;
    int amount;
    int shopid;

}
