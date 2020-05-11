package com.portal.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.portal.models.Author;

public interface IAuthorRepository extends CrudRepository<Author, Integer> {

	@Query(nativeQuery = true, value ="SELECT Author.* FROM Author join Book_Author ON Author.id=Book_Author.author_id WHERE Book_Author.book_id =?1")
	public List<Author> findBook(int bookId);
}
