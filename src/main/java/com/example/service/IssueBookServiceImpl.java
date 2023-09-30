package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.IssueBook;
import com.example.repository.IssueBookRepository;

@Service
public class IssueBookServiceImpl implements IssueBookService {
	@Autowired
	private IssueBookRepository issueBookRepository;

	@Override
	public void addIssueBook(IssueBook issueBook) {
		// TODO Auto-generated method stub
		issueBookRepository.save(issueBook);
	}

	@Override
	public List<IssueBook> getAllIssueBook() {
		// TODO Auto-generated method stub
		return issueBookRepository.findByReturnedFalse();
	}

	@Override
	public IssueBook getIssueBookById(Integer issueId) {
		// TODO Auto-generated method stub
		return issueBookRepository.findById(issueId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid IssueBook Id: " + issueId));
	}

	@Override
	public void returnBook(Integer issueId) {
		// TODO Auto-generated m
		IssueBook issueBook = issueBookRepository.findById(issueId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid BookTransaction Id: " + issueId));

		issueBookRepository.delete(issueBook);

	}

	@Override
	public List<IssueBook> getReturnList() {
		// TODO Auto-generated method stub
		return issueBookRepository.findByReturnedTrue();
	}

}
