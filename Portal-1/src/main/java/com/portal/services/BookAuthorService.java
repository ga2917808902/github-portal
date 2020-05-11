package com.portal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.Repositories.IAuthorRepository;
import com.portal.Repositories.IBookAuthorRepository;
import com.portal.models.Author;
import com.portal.models.BookAuthor;

@Service
public class BookAuthorService {

	@Autowired
	IBookAuthorRepository repo;
	
	@Autowired
	IAuthorRepository authorRepo;
	
	public List<BookAuthor> findAll(){
		return (List<BookAuthor>) repo.findAll();
	}
	
	public List<Author> findByBook(int id){
		return authorRepo.findBook(id);
	}
	
	public void saveOrUpdate(BookAuthor bookAuthor) {
		repo.save(bookAuthor);
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
}
