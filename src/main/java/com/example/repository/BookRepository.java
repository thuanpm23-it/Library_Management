package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
//	@Query("SELECT u FROM Book u WHERE u.category.categoryId = ?1")
//	List<Book> findBookByCategory(Integer categoryId);
	
	@Query("SELECT b FROM Book b WHERE (:categoryId IS NULL OR b.category.categoryId = :categoryId)"
            + " AND (:publisherId IS NULL OR b.publisher.publisherId = :publisherId)"
            + " AND (:authorId IS NULL OR b.author.authorId = :authorId)")
    List<Book> findBooksByCategoryAndPublisherAndAuthor(Integer categoryId, Integer publisherId, Integer authorId);
}
