package com.bishe.houduan.service;

import com.bishe.houduan.dao.CategoryDAO;
import com.bishe.houduan.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService  {
    @Autowired
    CategoryDAO categoryDAO;
    public List<Category>list()
    {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return categoryDAO.findAll(sort);
    }
    public Category get(int id)
    {
        Category c = categoryDAO.findById(id).orElse(null);
        return c;
    }
}
