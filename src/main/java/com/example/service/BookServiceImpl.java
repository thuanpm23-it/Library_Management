package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Book;
import com.example.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> getAllBook() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		bookRepository.save(book);
	}

	@Override
	public Book getBookById(Integer bookId) {
		// TODO Auto-generated method stub
		return bookRepository.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Invalid Book Id: " + bookId));
	}

	@Override
	public void deleteBook(Integer bookId) {
		// TODO Auto-generated method stub
		bookRepository.deleteById(bookId);
	}

//	@Override
//	public List<Book> findBookByCategory(Integer categoryId) {
//		// TODO Auto-generated method stub
//		return bookRepository.findBookByCategory(categoryId);
//	}

	@Override
	public List<Book> findBookByCategoryAndPublisherAndAuthor(Integer categoryId, Integer publisherId,
			Integer authorId) {
		// TODO Auto-generated method stub
		return bookRepository.findBooksByCategoryAndPublisherAndAuthor(categoryId, publisherId, authorId);
	}
}
