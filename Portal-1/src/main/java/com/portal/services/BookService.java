package com.portal.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

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

	public List<Book> TopEightBookViews(String name) {
		return repo.TopEightBookViews(name);
	}

	public Page<Book> sortByRequest(String name, int page, String request) {
		Page<Book> listBooks = null;
		if (request.equalsIgnoreCase("price-descending")) {
			listBooks = findByLikeName(name.trim(), PageRequest.of(page - 1, 12, Sort.by("price").descending()));
		} else if (request.equalsIgnoreCase("price-ascending")) {
			listBooks = findByLikeName(name.trim(), PageRequest.of(page - 1, 12, Sort.by("price").ascending()));
		} else if (request.equalsIgnoreCase("views")) {
			listBooks = findByLikeName(name.trim(), PageRequest.of(page - 1, 12, Sort.by("views").descending()));
		} else {
			listBooks = findByLikeName(name.trim(), PageRequest.of(page - 1, 12));
		}
		return listBooks;
	}

	public void savePDF(MultipartFile imageFile) throws IOException {
		String folder = "E:\\Eclipse\\github-portal-1\\Portal-1\\src\\main\\webapp\\resources\\pdf\\";
		byte[] bytes = imageFile.getBytes();
		Path path = Paths.get(folder + imageFile.getOriginalFilename());
		Files.write(path, bytes);

	}

	/**
	 * Hàm dùng để lấy ngẫu nhiên 4 book liên quan đến branch(ngành)
	 * 
	 * @param branchId
	 * @param bookId
	 * @return
	 */

	public List<Book> recommendBooks(int branchId, int bookId) {
		List<Book> recommendBooks = repo.findByBranchOrderByIdAsc(new Branch(branchId));
		if(recommendBooks.size() > 10) {
			int currentId = algorithmForRandomBook(recommendBooks, bookId);
			recommendBooks.remove(currentId);
			Collections.shuffle(recommendBooks);
			int random = 4;
			List<Book> randomBooks = recommendBooks.subList(0, random);
			return randomBooks;
		}else {
			return null;
		}
	}

	/**
	 * Hàm dùng chung để hiển thị item trên trang chủ
	 * 
	 * @param <T>
	 */
	public <T> void modelItem(ModelMap model, List<T> list) {
		if (list.size() > 0) {
			model.addAttribute("begin1", 0);
			model.addAttribute("end1", 2);
			model.addAttribute("begin2", 3);
			model.addAttribute("end2", 5);
			model.addAttribute("begin3", 6);
			model.addAttribute("end3", 8);
		}
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

	public int algorithmForRandomBook(List<Book> list, int id) {
		int left = 0;
		int right = list.size() - 1;
		int mid = 0;
		while (left <= right) {
			mid = (right + left) / 2;
			if (list.get(mid).getId() < id) {
				left = mid + 1;
			} else if (list.get(mid).getId() > id) {
				right = mid - 1;
			} else {
				return mid;
			}
		}

		return 0;
	}
}
