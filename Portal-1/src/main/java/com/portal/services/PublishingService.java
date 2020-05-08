package com.portal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.Repositories.IPublishingRepository;
import com.portal.models.Publishing;

@Service
public class PublishingService {

	@Autowired
	IPublishingRepository repo;
	
	public List<Publishing> findAll(){
		return (List<Publishing>) repo.findAll();
	}
	
	public Optional<Publishing> findById(int id) {
		return repo.findById(id);
	}
	
	public void saveOrUpdate(Publishing publishing) {
		repo.save(publishing);
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
}
