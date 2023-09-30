package com.example.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.Book;

import com.example.model.IssueBook;
import com.example.model.Member;

import com.example.service.BookService;

import com.example.service.IssueBookService;
import com.example.service.MemberService;

@Controller
@RequestMapping("/issuebook")
public class IssueBookController {
	@Autowired
	private IssueBookService issueBookService;

	@Autowired
	private BookService bookService;

	@Autowired
	private MemberService memberService;

	@GetMapping("/list")
	public String addIssueBook(Model model) {
		List<Book> book = bookService.getAllBook();
		List<Member> member = memberService.getAllMember();
		List<IssueBook> issueBooks = issueBookService.getAllIssueBook();
		model.addAttribute("book", book);
		model.addAttribute("member", member);
		model.addAttribute("issueBooks", issueBooks);
		model.addAttribute("issueBook", new IssueBook());
		return "issuebook/form";
	}

	@PostMapping("/save")
	public String saveIssueBook(@ModelAttribute("issueBook") IssueBook issueBook) {
		issueBookService.addIssueBook(issueBook);
		return "redirect:/issuebook/list";
	}

	@GetMapping("/details/{issueId}")
	public String getIssueBookDetails(@PathVariable("issueId") Integer issueId, Model model) {
		IssueBook issueBook = issueBookService.getIssueBookById(issueId);
		model.addAttribute("issueBook", issueBook);
		return "issuebook/details";
	}

	@GetMapping("/return/{issueId}")
	public String returnBook(@PathVariable("issueId") Integer issueId) {
		IssueBook issueBook = issueBookService.getIssueBookById(issueId);
		LocalDate currentDate = LocalDate.now();
		issueBook.setReturnDate(currentDate);
		issueBook.setReturned(true);
		issueBookService.addIssueBook(issueBook);
		return "redirect:/issuebook/list";
	}

	@GetMapping("/returnlist")
	public String getReturnList(Model model) {
		List<IssueBook> returnList = issueBookService.getReturnList();
		model.addAttribute("returnList", returnList);
		return "issuebook/returnform";
	}
}
