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

import com.example.model.Author;
import com.example.service.AuthorService;

@Controller
@RequestMapping("/author")
public class AuthorController {

	@Autowired
	private AuthorService authorService;
	
	@GetMapping("/list")
	public String getAllAuthor(Model model) {
		List<Author> author = authorService.getAllAuthor();
		model.addAttribute("author", author);
		return "author/list";
	}
	
	@GetMapping("/add")
	public String addAuthor(Model model) {
		Author author = new Author();
		model.addAttribute("author", author);
		return "author/add";
	}
	
	@PostMapping("/save")
	public String saveAuthor(@ModelAttribute("author") @Validated Author author, BindingResult result) {
	    if (result.hasErrors()) {
	        return "author/add";
	    } else {
	        authorService.addAuthor(author);
	        return "redirect:/author/list";
	    }
	}
	
	@GetMapping("/edit/{id}")
	public String editAuthor(@PathVariable("id") Integer id, Model model) {
		Author author = authorService.editAuthor(id);
		model.addAttribute("author", author);
		return "author/edit";
	}

//	@PostMapping("/update")
//	public String updateAuthor(@ModelAttribute("Author") Author author) {
//		authorService.updateAuthor(author);
//		return "redirect:/author/list";
//	}
	@GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable("id") Integer id) {
		authorService.deleteAuthor(id);
        return "redirect:/author/list";
    }
}
