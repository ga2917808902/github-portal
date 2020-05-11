package com.portal.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.portal.models.Book;
import com.portal.models.ImageBook;

public interface IImageBookRepository extends CrudRepository<ImageBook, Integer>{
	public List<ImageBook> findByBook(Book book);
}
