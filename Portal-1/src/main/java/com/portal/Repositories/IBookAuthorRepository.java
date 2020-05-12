package com.portal.Repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.portal.models.BookAuthor;

public interface IBookAuthorRepository extends CrudRepository<BookAuthor, Long> {
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value ="DELETE FROM book_author WHERE author_id=?1")
	void deleteByAuthor(int id);
}
