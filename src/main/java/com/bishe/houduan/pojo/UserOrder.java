package com.bishe.houduan.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@ToString
@Table(name = "orderform")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String shopname;
    String username;
    Date time;

}
