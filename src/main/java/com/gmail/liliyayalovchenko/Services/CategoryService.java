package com.gmail.liliyayalovchenko.Services;

import com.gmail.liliyayalovchenko.DAO.CategoryDAO;
import com.gmail.liliyayalovchenko.Domains.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CategoryService {

    @Autowired
    private CategoryDAO categoryDAO;

    @Transactional
    public Category getCategoryByName(String  categoryName) {
         return categoryDAO.getCategoryByName(categoryName);
    }

    @Transactional
    public Category getCategoryById(int id) {
        return categoryDAO.getCategoryById(id);
    }

    @Transactional
    public void saveCategory(Category category) {
        categoryDAO.saveCategory(category);
    }

    @Transactional
    public void saveCategory(int id, String name, String info,
                             String metaDescription, String metaKeyWords, String metaTitle) {
        categoryDAO.saveCategory(id, name, info, metaDescription, metaKeyWords, metaTitle);
    }

    @Transactional
    public List<Category> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    @Transactional
    public void remove(int id) {
        categoryDAO.remove(id);
    }
}
