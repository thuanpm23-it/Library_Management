package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.IssueBook;

public interface IssueBookRepository extends JpaRepository<IssueBook, Integer> {

	List<IssueBook> findByReturnedFalse();
	
	List<IssueBook> findByReturnedTrue();
	
	Long countByReturnedFalse();

}
