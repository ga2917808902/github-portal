package com.portal.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.portal.models.Book;
import com.portal.models.Branch;
import com.portal.models.Category;

public interface IBranchRepository extends CrudRepository<Branch, Integer>{

	List<Branch> findByCategory(Category category);
	
	@Query(nativeQuery = true, 
			value = "SELECT COUNT(*) "
					+ "FROM branch join book on branch.id=book.branch_id "
					+ "WHERE branch.id=?1 ")
	int countBook(int id);
	
	
}
