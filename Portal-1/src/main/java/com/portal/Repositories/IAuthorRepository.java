package com.portal.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.portal.models.Author;

public interface IAuthorRepository extends CrudRepository<Author, Integer> {

}
