package com.portal.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.portal.models.Book;
import com.portal.models.ImageBook;

public interface IImageBookRepository extends CrudRepository<ImageBook, Integer>{
	
	@Query(nativeQuery = true, value ="SELECT * FROM image_book WHERE image_book.book_id=?1 order by main desc")
	public List<ImageBook> findByBook(int id);
	
	@Query(nativeQuery = true, value ="SELECT * FROM image_book WHERE image_book.book_id=?1")
	public List<ImageBook> findByBookID(int id);
}
