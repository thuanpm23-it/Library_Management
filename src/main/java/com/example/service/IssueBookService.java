package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.IssueBook;


public interface IssueBookService {


	void addIssueBook(IssueBook issueBook);

	List<IssueBook> getAllIssueBook();

	IssueBook getIssueBookById(Integer issueId);

	void returnBook(Integer issueId);

	List<IssueBook> getReturnList();


}