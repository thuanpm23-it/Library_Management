package com.example.service;

import java.util.List;

import com.example.model.Category;

public interface CategoryService {

	List<Category> getAllCategory();

	void addCategory(Category category);

	Category editCategory(Integer id);

//	void updateCategory(Category category);

	void deleteCategory(Integer id);

}
