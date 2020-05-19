package com.portal.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.portal.models.Book;
import com.portal.models.Branch;

public interface IBookRepository extends JpaRepository<Book, Integer>{

	/**
	 * Dùng pageable với nativeQuery thì phải thêm câu lệnh countQuery
	 * @param id
	 * @param pageable phân trang
	 * @return
	 */
	@Query(nativeQuery = true, 
			value = "SELECT book.*  "
					+ "FROM branch join book on branch.id=book.branch_id "
					+ "WHERE branch.category_id=?1 ",
					countQuery = "SELECT book.* FROM branch join book on branch.id=book.branch_id WHERE branch.category_id=?1")
	Page<Book> findBook(int id, Pageable pageable);
	
	Page<Book> findByBranch(Branch branch, Pageable pageable);
	
	@Query(nativeQuery = true,value="SELECT * FROM BOOK WHERE name like %?1%", countQuery = "SELECT * FROM BOOK WHERE name like %?1%")
	Page<Book> findLikeName(String name, Pageable pageable);
	
	@Query(nativeQuery = true, value = "SELECT top(10) * FROM book ORDER BY created_at desc")
	List<Book> recentlyBook();
}
