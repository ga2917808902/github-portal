package com.portal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.Repositories.IAuthorRepository;
import com.portal.models.Author;

@Service
public class AuthorService {

	@Autowired
	IAuthorRepository repo;
	
	public List<Author> findAll(){
		return (List<Author>) repo.findAll();
	}
	
	public Optional<Author> findById(int id) {
		return repo.findById(id); 
	}
	
	public void saveOrUpdate(Author author) {
		repo.save(author);
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
	
	public List<Author> listAuthors(List<Author> tempList){
		List<Author> list = this.findAll();
		
		return list;
	}
}
