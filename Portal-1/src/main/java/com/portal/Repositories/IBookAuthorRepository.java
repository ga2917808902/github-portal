package com.portal.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.portal.models.BookAuthor;

public interface IBookAuthorRepository extends CrudRepository<BookAuthor, Long> {
	
	
}
