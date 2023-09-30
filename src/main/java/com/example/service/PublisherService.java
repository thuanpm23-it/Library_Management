package com.example.service;

import java.util.List;

import com.example.model.Publisher;

public interface PublisherService {

	List<Publisher> getAllPublisher();

	void addPublisher(Publisher publisher);

	Publisher editPublisher(Integer id);

//	void updatePublisher(Publisher publisher);

	void deletePublisher(Integer id);

}
