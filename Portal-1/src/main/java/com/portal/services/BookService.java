package com.portal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.portal.Repositories.IBookRepository;
import com.portal.models.Book;
import com.portal.models.Branch;

@Service
public class BookService {

	@Autowired
	IBookRepository repo;
	
	public Page<Book> findAll(Pageable pageable){
		return repo.findAll(pageable);
	}
	
	public List<Book> findAll(){
		return (List<Book>) repo.findAll();
	}
	
	public Optional<Book> findById(int id) {
		return repo.findById(id);
	}
	
	public void saveOrUpdate(Book book) {
		repo.save(book);
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
	
	public Page<Book> findByBranch(Branch branch, Pageable pageable){
		return repo.findByBranch(branch, pageable);
	}
	
	public Page<Book> findByBook(int id, Pageable pageable){
		return repo.findBook(id, pageable);
	}
	
}
