package com.bishe.houduan.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
@Entity
@Data
@ToString
@Table(name = "admin_menu")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    String path;
    String name;
    String namezh;
    String component;
    String icon;
    int parentId;

    @Transient
    List<AdminMenu> children;
}
