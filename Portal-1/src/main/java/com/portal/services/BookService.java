package com.portal.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.portal.Repositories.IBookRepository;
import com.portal.models.Book;
import com.portal.models.Branch;

@Service
public class BookService {

	static int tempViews;
	static int views;
	public static Map<Integer, Book> mapBooks = new HashMap<>();

	@Autowired
	IBookRepository repo;

	public Page<Book> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	public List<Book> findAll() {
		return (List<Book>) repo.findAll();
	}

	public Optional<Book> findById(int id) {
		return repo.findById(id);
	}

	public void saveOrUpdate(Book book) {
		repo.save(book);
	}

	public void delete(int id) {
		repo.deleteById(id);
	}

	public Page<Book> findByBranch(Branch branch, Pageable pageable) {
		return repo.findByBranch(branch, pageable);
	}

	public Page<Book> findByBook(int id, Pageable pageable) {
		return repo.findBook(id, pageable);
	}

	public List<Book> recentlyBook() {
		return repo.recentlyBook();
	}

	public Page<Book> findByLikeName(String name, Pageable pageable) {
		return repo.findByLikeName(name, pageable);
	}

	public Book findByName(String name) {
		return repo.findByName(name);
	}

	public List<Book> topViews() {
		return repo.topViews();
	}
	
	public List<Book> TopEightBookViews(String name){
		return repo.TopEightBookViews(name);
	}

	public int processViews(Book book) {
		if (mapBooks.containsKey(book.getId())) {
			Book tempBook = mapBooks.get(book.getId());
			tempViews = tempBook.getViews();
			views = tempViews + 1;
			tempBook.setViews(views);
			mapBooks.put(book.getId(), tempBook);
		} else {
			tempViews = book.getViews();
			views = tempViews + 1;
			book.setViews(views);
			mapBooks.put(book.getId(), book);
		}
		
		return views;
	}
}
