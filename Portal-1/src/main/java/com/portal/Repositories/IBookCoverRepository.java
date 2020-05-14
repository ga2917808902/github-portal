package com.portal.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.portal.models.BookCover;

public interface IBookCoverRepository extends CrudRepository<BookCover, Integer> {

	
}
