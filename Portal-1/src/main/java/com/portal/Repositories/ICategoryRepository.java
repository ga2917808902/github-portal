package com.portal.Repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.portal.models.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer> {

	@Query(nativeQuery = true, 
			value = "SELECT COUNT(*) "
					+ "FROM category join branch on category.id=branch.category_id join book on branch.id=book.branch_id "
					+ "WHERE category.id=?1 ")
	int countBook(int id);
}
