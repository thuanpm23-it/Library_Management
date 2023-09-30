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

import com.example.model.Publisher;
import com.example.repository.PublisherRepository;

@RestController
@RequestMapping(value = "/api/publisher")
public class PublisherRestController {
	@Autowired
	private PublisherRepository publisherRepository;

	@GetMapping("")
	public List<Publisher> getAllPublishers() {
		return publisherRepository.findAll();
	}

	@GetMapping("/{id}")
	public Publisher getPublisherById(@PathVariable Integer id) {
		return publisherRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Publisher Id: " + id));
	}

	@PostMapping("")
	public Publisher createPublisher(@RequestBody Publisher publisher) {
		return publisherRepository.save(publisher);
	}

	@PutMapping("/{id}")
	public Publisher updatePublisher(@PathVariable Integer id, @RequestBody Publisher updatedPublisher) {
		return publisherRepository.findById(id).map(publisher -> {
			publisher.setPublisherName(updatedPublisher.getPublisherName());
			publisher.setAddress(updatedPublisher.getAddress());
			publisher.setContactEmail(updatedPublisher.getContactEmail());
			publisher.setContactPhone(updatedPublisher.getContactPhone());
			return publisherRepository.save(publisher);
		}).orElseThrow(() -> new IllegalArgumentException("Invalid Publisher Id: " + id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePublisher(@PathVariable Integer id) {
		try {
			publisherRepository.deleteById(id);
			return ResponseEntity.ok("Delete Publisher Successfully!");
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher not found!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Publisher!");
		}
	}

}
