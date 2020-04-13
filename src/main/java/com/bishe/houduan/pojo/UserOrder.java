package com.bishe.houduan.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "purchase")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class UserOrder {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    public String getCaole() {
        return caole;
    }

    public void setCaole(String caole) {
        this.caole = caole;
    }

    String caole;

}
