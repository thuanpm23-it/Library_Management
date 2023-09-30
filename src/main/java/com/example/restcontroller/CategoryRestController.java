package com.example.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Category;
import com.example.repository.CategoryRepository;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryRestController {
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("")
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	@GetMapping("/{id}")
	public Category getCategoryById(@PathVariable Integer id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Category Id: " + id));
	}

	@PostMapping("")
	public Category createCategory(@RequestBody Category category) {
		return categoryRepository.save(category);
	}

	@PutMapping("/{id}")
	public Category updateCategory(@PathVariable Integer id, @RequestBody Category updatedCategory) {
		return categoryRepository.findById(id).map(category -> {
			category.setCategoryName(updatedCategory.getCategoryName());
			category.setShortName(updatedCategory.getShortName());
			category.setDescription(updatedCategory.getDescription());
			return categoryRepository.save(category);
		}).orElseThrow(() -> new IllegalArgumentException("Invalid Category Id: " + id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
		try {
			categoryRepository.deleteById(id);
			return ResponseEntity.ok("Delete Category Successfully!");
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Category!");
		}
	}

}
