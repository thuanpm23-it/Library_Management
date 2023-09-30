package com.example.service;

import java.util.List;

import com.example.model.Book;

public interface BookService {

	

	List<Book> getAllBook();

	void addBook(Book book);

	Book getBookById(Integer bookId);

	void deleteBook(Integer bookId);

//	List<Book> findBookByCategory(Integer categoryId);

	List<Book> findBookByCategoryAndPublisherAndAuthor(Integer categoryId, Integer publisherId, Integer authorId);
}
