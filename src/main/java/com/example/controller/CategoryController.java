package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Category;
import com.example.service.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/list")
	public String getAllCategory(Model model) {
		List<Category> category = categoryService.getAllCategory();
		model.addAttribute("category", category);
		return "category/list";
	}
	
	@GetMapping("/add")
	public String addCategory(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "category/add";
	}
	
	@PostMapping("/save")
	public String saveCategory(@ModelAttribute("category") @Validated Category category, BindingResult result) {
		   if (result.hasErrors()) {
			   return "category/add";
		   } else {
		 
		categoryService.addCategory(category);
			return "redirect:/category/list";
		
		   }
		
	}
	@GetMapping("/edit/{id}")
	public String editCategory(@PathVariable("id") Integer id, Model model) {
		Category category = categoryService.editCategory(id);
		model.addAttribute("category", category);
		return "category/edit";
	}

	@GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id) {
		categoryService.deleteCategory(id);
        return "redirect:/category/list";
    }
}


