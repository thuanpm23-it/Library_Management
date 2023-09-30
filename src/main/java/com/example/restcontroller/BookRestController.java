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

import com.example.model.Book;
import com.example.repository.BookRepository;

@RestController
@RequestMapping(value = "/api/book")
public class BookRestController {
	@Autowired
	private BookRepository bookRepository;

	@GetMapping("")
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@GetMapping("/{id}")
	public Book getBookById(@PathVariable Integer id) {
		return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Book Id: " + id));
	}

	@PostMapping("")
	public Book createBook(@RequestBody Book book) {
		return bookRepository.save(book);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
		try {
			bookRepository.deleteById(id);
			return ResponseEntity.ok("Delete Book Successfully!");
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Book!");
		}
	}

	@PutMapping("/{id}")
	public Book updateBook(@PathVariable Integer id, @RequestBody Book updatedBook) {
		return bookRepository.findById(id).map(book -> {
			book.setTag(updatedBook.getTag());
			book.setTitle(updatedBook.getTitle());
			book.setImage(updatedBook.getImage());
			book.setAuthor(updatedBook.getAuthor());
			book.setPublisher(updatedBook.getPublisher());
			book.setCategory(updatedBook.getCategory());
			book.setPublicationYear(updatedBook.getPublicationYear());
			book.setISBN(updatedBook.getISBN());
			book.setDescription(updatedBook.getDescription());
			return bookRepository.save(book);
		}).orElseThrow(() -> new IllegalArgumentException("Invalid Book Id: " + id));
	}

}
