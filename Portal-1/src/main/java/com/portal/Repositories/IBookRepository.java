package com.portal.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.portal.models.Book;

public interface IBookRepository extends CrudRepository<Book, Integer>{

}
