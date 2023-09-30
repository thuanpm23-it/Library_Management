package com.example.controller;

import com.example.model.Author;
import com.example.model.Book;
import com.example.model.Category;
import com.example.model.Publisher;
import com.example.service.AuthorService;
import com.example.service.BookService;
import com.example.service.CategoryService;
import com.example.service.PublisherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;

	@Autowired
	private PublisherService publisherService;

	@Autowired
	private CategoryService categoryService;

	private static final String UPLOAD_DIR = "src/main/resources/static/images/";

	@GetMapping("/list")
	public String getAllBook(Model model) {
		List<Book> book = bookService.getAllBook();
		List<Author> author = authorService.getAllAuthor();
		List<Publisher> publisher = publisherService.getAllPublisher();
		List<Category> category = categoryService.getAllCategory();
		model.addAttribute("category", category);
		model.addAttribute("publisher", publisher);
		model.addAttribute("author", author);
		model.addAttribute("book", book);
		return "book/list";
	}

	@PostMapping("/list")
	public String getAllBookByCategory(@RequestParam(name = "categoryId", required = false) Integer categoryId,
			@RequestParam(name = "publisherId", required = false) Integer publisherId,
			@RequestParam(name = "authorId", required = false) Integer authorId, Model model) {
		List<Category> category = categoryService.getAllCategory();
		List<Book> book = bookService.findBookByCategoryAndPublisherAndAuthor(categoryId, publisherId, authorId);
		model.addAttribute("category", category);
		model.addAttribute("book", book);
		model.addAttribute("categoryId", categoryId);
		return "book/list";
	}

	@GetMapping("/add")
	public String addBook(Model model) {
		Book book = new Book();
		List<Author> author = authorService.getAllAuthor();
		List<Publisher> publisher = publisherService.getAllPublisher();
		List<Category> category = categoryService.getAllCategory();
		model.addAttribute("category", category);
		model.addAttribute("publisher", publisher);
		model.addAttribute("author", author);
		model.addAttribute("book", book);
		return "book/add";
	}

	@PostMapping("/save")
	public String saveBook(@ModelAttribute("book") Book book, @RequestParam("imageFile") MultipartFile imageFile)
			throws IOException {
		if (!imageFile.isEmpty()) {
			String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
			Path path = Paths.get(UPLOAD_DIR + fileName);
			Files.copy(imageFile.getInputStream(), path);
			book.setImage(fileName);
		}

		bookService.addBook(book);
		return "redirect:/book/list";
	}

	@GetMapping("/edit/{bookId}")
	public String editBook(@PathVariable("bookId") Integer bookId, Model model) {
		Book book = bookService.getBookById(bookId);
		List<Author> author = authorService.getAllAuthor();
		List<Publisher> publisher = publisherService.getAllPublisher();
		List<Category> category = categoryService.getAllCategory();
		model.addAttribute("category", category);
		model.addAttribute("publisher", publisher);
		model.addAttribute("author", author);
		model.addAttribute("book", book);
		return "book/edit";
	}

	@GetMapping("/delete/{bookId}")
	public String deleteBook(@PathVariable("bookId") Integer bookId) {
		bookService.deleteBook(bookId);
		return "redirect:/book/list";
	}
}
