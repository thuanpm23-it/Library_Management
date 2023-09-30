package com.example.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "issuebook")
public class IssueBook {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer issueId;

	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate issueDate;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate dueDate;
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate returnDate;

	private boolean returned;

}