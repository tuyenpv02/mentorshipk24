package com.example.demo.service;

import com.example.demo.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    List<Category> getAllBySource(Long sourceId);

    Category findById(Long id);

    Category add(Category category);

    Category update(Long id, Category newCategory);

    Category deleteById(Long id);

    Boolean existsById(Long id);
}
