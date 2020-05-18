package com.portal.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.portal.models.BookAuthor;

public interface IBookAuthorRepository extends JpaRepository<BookAuthor, Long> {
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value ="DELETE FROM book_author WHERE author_id=?1")
	void deleteByAuthor(int id);

}
