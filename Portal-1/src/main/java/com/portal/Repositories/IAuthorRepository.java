package com.portal.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.portal.models.Author;


public interface IAuthorRepository extends JpaRepository<Author, Integer> {

	/**
	 * Dùng nativeSQL(SQL) chứ không phải HQL(Hibernate)
	 * Query dùng để lấy tất cả thông tin của tác giả
	 * @param bookId
	 * @return list author
	 */
	@Query(nativeQuery = true, value ="SELECT Author.* FROM Author join Book_Author ON Author.id=Book_Author.author_id WHERE Book_Author.book_id =?1")
	List<Author> findBook(int bookId);
}
