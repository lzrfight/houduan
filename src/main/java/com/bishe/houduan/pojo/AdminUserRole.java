package com.bishe.houduan.pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
@Data
@Entity
@ToString
@Table(name = "admin_user_role")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class AdminUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    int uid;
    int rid;
}
