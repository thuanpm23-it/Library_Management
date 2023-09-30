package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Publisher;
import com.example.repository.PublisherRepository;

@Service
public class PublisherServiceImpl implements PublisherService {
	
	@Autowired
	private PublisherRepository publisherRepository;

	@Override
	public List<Publisher> getAllPublisher() {
		// TODO Auto-generated method stub
		return publisherRepository.findAll();
	}

	@Override
	public void addPublisher(Publisher publisher) {
		// TODO Auto-generated method stub
		publisherRepository.save(publisher);
	}

	@Override
	public Publisher editPublisher(Integer id) {
		// TODO Auto-generated method stub
		return publisherRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Publisher Id: " + id));
	}

//	@Override
//	public void updatePublisher(Publisher publisher) {
//		// TODO Auto-generated method stub
//		publisherRepository.save(publisher);
//	}

	@Override
	public void deletePublisher(Integer id) {
		// TODO Auto-generated method stub
		publisherRepository.deleteById(id);
	}

}
