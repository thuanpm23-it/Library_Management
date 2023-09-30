package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Author;
import com.example.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public List<Author> getAllAuthor() {
		// TODO Auto-generated method stub
		return authorRepository.findAll();
	}

	@Override
	public void addAuthor(Author author) {
		// TODO Auto-generated method stub
		authorRepository.save(author);
		
	}

	@Override
	public Author editAuthor(Integer id) {
		// TODO Auto-generated method stub
		return authorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Author Id: " + id));
	}

//	@Override
//	public void updateAuthor(Author author) {
//		// TODO Auto-generated method stub
//		authorRepository.save(author);
//		
//	}

	@Override
	public void deleteAuthor(Integer id) {
		// TODO Auto-generated method stub
		authorRepository.deleteById(id);
		
	}

}
