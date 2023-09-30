package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.repository.AuthorRepository;
import com.example.repository.BookRepository;
import com.example.repository.CategoryRepository;
import com.example.repository.IssueBookRepository;
import com.example.repository.MemberRepository;
import com.example.repository.PublisherRepository;

@Controller
public class HomeController {
	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private PublisherRepository publisherRepository;

	@Autowired
	private IssueBookRepository issueBookRepository;

	@GetMapping("/")
	public String Home(Model model) {
		long authorCount = authorRepository.count();
		model.addAttribute("authorCount", authorCount);

		// Count Categories
		long categoryCount = categoryRepository.count();
		model.addAttribute("categoryCount", categoryCount);

		// Count Books
		long bookCount = bookRepository.count();
		model.addAttribute("bookCount", bookCount);

		// Count Members
		long memberCount = memberRepository.count();
		model.addAttribute("memberCount", memberCount);

		// Count Publishers
		long publisherCount = publisherRepository.count();
		model.addAttribute("publisherCount", publisherCount);

		// Count IssueBooks with returned = false
		long issueBookCount = issueBookRepository.countByReturnedFalse();

		model.addAttribute("issueBookCount", issueBookCount);
		return "home";
	}
}
