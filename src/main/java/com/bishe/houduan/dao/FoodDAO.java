package com.bishe.houduan.dao;

import com.bishe.houduan.pojo.Category;
import com.bishe.houduan.pojo.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodDAO extends JpaRepository<Food,Integer> {
    List<Food> findAllByCategory(Category category);

}
