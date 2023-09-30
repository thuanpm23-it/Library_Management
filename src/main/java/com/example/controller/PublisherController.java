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

import com.example.model.Publisher;
import com.example.service.PublisherService;

@Controller
@RequestMapping("/publisher")
public class PublisherController {
	@Autowired
	private PublisherService publisherService;

	@GetMapping("/list")
	public String getAllPublisher(Model model) {
		List<Publisher> publisher = publisherService.getAllPublisher();
		model.addAttribute("publisher", publisher);
		return "publisher/list";
	}

	@GetMapping("/add")
	public String addPublisher(Model model) {
		Publisher publisher = new Publisher();
		model.addAttribute("publisher", publisher);
		return "publisher/add";
	}

	@PostMapping("/save")
	public String savePublisher(@ModelAttribute("publisher") @Validated Publisher publisher,BindingResult result) {

		if(result.hasErrors()) {
			return "publisher/add";
		}else {
			publisherService.addPublisher(publisher);
			return "redirect:/publisher/list";

		}
		
	}

	@GetMapping("/edit/{id}")
	public String editPublisher(@PathVariable("id") Integer id, Model model) {
		Publisher publisher = publisherService.editPublisher(id);
		model.addAttribute("publisher", publisher);
		return "publisher/edit";
	}

//	@PostMapping("/update")
//	public String updatePublisher(@ModelAttribute("Publisher") Publisher publisher) {
//		publisherService.updatePublisher(publisher);
//		return "redirect:/publisher/list";
//	}

	@GetMapping("/delete/{id}")
	public String deletePublisher(@PathVariable("id") Integer id) {
		publisherService.deletePublisher(id);
		return "redirect:/publisher/list";
	}
}
