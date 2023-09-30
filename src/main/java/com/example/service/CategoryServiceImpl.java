package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Category;
import com.example.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public void addCategory(Category category) {
		// TODO Auto-generated method stub
		categoryRepository.save(category);
		
	}

	@Override
	public Category editCategory(Integer id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Category Id: " + id));
	}

//	@Override
//	public void updateCategory(Category category) {
//		// TODO Auto-generated method stub
//		categoryRepository.save(category);
//		
//	}

	@Override
	public void deleteCategory(Integer id) {
		// TODO Auto-generated method stub
		categoryRepository.deleteById(id);
	}
}
