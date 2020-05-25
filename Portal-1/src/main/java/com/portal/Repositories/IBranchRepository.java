package com.portal.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.portal.models.Branch;
import com.portal.models.Category;

public interface IBranchRepository extends CrudRepository<Branch, Integer>{

	@Query(nativeQuery = true, 
			value = "SELECT branch.* "
					+ "FROM branch join category on category.id=branch.category_id "
					+ "WHERE category.name = ?1 "
					+ "GROUP BY branch.name, branch.id, branch.category_id")
	List<Branch> findByCategoryName(String name);
	
	@Query(nativeQuery = true, 
			value = "SELECT COUNT(*) "
					+ "FROM branch join book on branch.id=book.branch_id "
					+ "WHERE branch.id=?1 ")
	int countBook(int id);
	
	@Query(nativeQuery = true, 
			value = "SELECT branch.* "
					+ "FROM branch join book on book.branch_id=branch.id "
					+ "WHERE book.name like %?1% "
					+ "GROUP BY branch.id, branch.name, branch.category_id")
	List<Branch> recommendBranchs(String name);
}
