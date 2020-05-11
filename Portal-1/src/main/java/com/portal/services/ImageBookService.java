package com.portal.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.Repositories.IImageBookRepository;
import com.portal.models.Book;
import com.portal.models.ImageBook;

@Service
public class ImageBookService {

	@Autowired
	IImageBookRepository repo;
	
	public List<ImageBook> findByBook(Book book) {
		List<ImageBook> list = repo.findByBook(book);
		return list;
	}
}
