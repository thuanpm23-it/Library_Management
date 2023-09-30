package com.example.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Author;
import com.example.repository.AuthorRepository;

@RestController
@RequestMapping(value = "/api/author")
public class AuthorRestController {

	@Autowired
	private AuthorRepository authorRepository;

	@GetMapping("")
	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}

	@GetMapping("/{id}")
	public Author getAuthorById(@PathVariable Integer id) {
		return authorRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Author Id: " + id));
	}

	@PostMapping("")
	public Author createAuthor(@RequestBody Author author) {
		return authorRepository.save(author);
	}

	@PutMapping("/{id}")
	public Author updateAuthor(@PathVariable Integer id, @RequestBody Author updatedAuthor) {
		return authorRepository.findById(id).map(author -> {
			author.setAuthorName(updatedAuthor.getAuthorName());
			author.setBirthDate(updatedAuthor.getBirthDate());
			return authorRepository.save(author);
		}).orElseThrow(() -> new IllegalArgumentException("Invalid Author Id: " + id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAuthor(@PathVariable Integer id) {
		try {
	        authorRepository.deleteById(id);
	        return ResponseEntity.ok("Delete Author Successfully!");
	    } catch (EmptyResultDataAccessException e) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author not found!");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Author!");
	    }
	}
}