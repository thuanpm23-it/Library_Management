package com.example.service;

import java.util.List;

import com.example.model.Author;

public interface AuthorService {

	List<Author> getAllAuthor();

	void addAuthor(Author author);

	Author editAuthor(Integer id);

//	void updateAuthor(Author author);

	void deleteAuthor(Integer id);

}
