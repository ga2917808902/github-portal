package com.portal.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.portal.models.Category;

public interface ICategoryRepository extends CrudRepository<Category, Integer> {

}
