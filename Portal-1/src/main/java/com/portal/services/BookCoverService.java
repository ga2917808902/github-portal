package com.portal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.Repositories.IBookCoverRepository;
import com.portal.models.BookCover;

@Service
public class BookCoverService {

	@Autowired
	IBookCoverRepository repo;
	
	public List<BookCover> findAll(){
		return (List<BookCover>) repo.findAll();
	}
	
	public Optional<BookCover> findById(int id) {
		return repo.findById(id); 
	}
	
	public void saveOrUpdate(BookCover bookCover) {
		repo.save(bookCover);
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
}
