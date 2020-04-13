package com.bishe.houduan.service;

import com.bishe.houduan.dao.FoodDAO;
import com.bishe.houduan.pojo.Category;
import com.bishe.houduan.pojo.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {
    @Autowired
    FoodDAO foodDAO;
    @Autowired
    CategoryService categoryService;
    public List<Food> list() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return foodDAO.findAll(sort);
    }

    public void addOrUpdate(Food food) {
       foodDAO.save(food);
    }

    public void deleteById(int id) {
       foodDAO.deleteById(id);
    }

    public List<Food> listByCategory(int cid) {
        Category category = categoryService.get(cid);
        return foodDAO.findAllByCategory(category);
    }
}
