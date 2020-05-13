package com.portal.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.portal.models.Book;

public interface IBookRepository extends CrudRepository<Book, Integer>{

	@Query(nativeQuery = true, 
			value = "SELECT book.* "
					+ "FROM branch join book on branch.id=book.branch_id "
					+ "WHERE branch.category_id=?1 ")
	List<Book> findBook(int id);
}
