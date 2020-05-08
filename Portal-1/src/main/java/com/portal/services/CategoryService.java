package com.portal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.Repositories.ICategoryRepository;
import com.portal.models.Category;

@Service
public class CategoryService {

	@Autowired
	ICategoryRepository repo;
	
	public List<Category> findAll(){
		return (List<Category>) repo.findAll();
	}
	
	public Optional<Category> findById(int id) {
		return repo.findById(id);
	}
	
	public void saveOrUpdate(Category category) {
		repo.save(category);
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
}
