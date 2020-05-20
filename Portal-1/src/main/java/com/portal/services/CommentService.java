package com.portal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.Repositories.ICommentRepository;
import com.portal.models.Book;
import com.portal.models.Comment;

@Service
public class CommentService {

	@Autowired
	ICommentRepository repo;
	
	public void saveOrUpdate(Comment comment) {
		repo.save(comment);
	}
	
	public List<Comment> findByBook(Book book){
		return repo.findByBook(book);
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}
	
	public Optional<Comment> findById(long id) {
		return repo.findById(id);
	}
}
