package com.portal.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.portal.models.Author;
import com.portal.models.Book;
import com.portal.models.BookAuthor;
import com.portal.models.BookCover;
import com.portal.models.Branch;
import com.portal.models.Language;
import com.portal.models.Publishing;
import com.portal.services.AuthorService;
import com.portal.services.BookAuthorService;
import com.portal.services.BookCoverService;
import com.portal.services.BookService;
import com.portal.services.BranchService;
import com.portal.services.LanguageService;
import com.portal.services.PublishingService;

@Controller
@RequestMapping("book")
public class BookController {

	@Autowired
	BookService service;

	@Autowired
	PublishingService pubService;

	@Autowired
	BranchService branchService;

	@Autowired
	BookCoverService bookCoverService;

	@Autowired
	LanguageService langService;

	@Autowired
	AuthorService authorService;

	@Autowired
	BookAuthorService bookAuthorService;

	static ObjectMapper mapper = new ObjectMapper();

	@GetMapping(value = { "index", "/" })
	public String index(ModelMap model, @RequestParam(defaultValue = "1") int page) {
		Page<Book> listBooks = service.findAll(PageRequest.of(page - 1, 10));
		int totalPages = listBooks.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("listBooks", listBooks);

		return "book/index";
	}

	@GetMapping("new")
	public String create(ModelMap model) throws JsonProcessingException {
		List<Author> listAuthors = authorService.findAll();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		model.addAttribute("listAuthors", mapper.writeValueAsString(listAuthors));
		model.addAttribute("book", new Book());

		return "book/create";
	}

	@PostMapping("save")
	public String save(@ModelAttribute("book") Book book, @RequestParam List<Integer> listIdAuthor) {
		LocalDateTime time = LocalDateTime.now();
		book.setCreatedAt(time);
		book.setUpdatedAt(time);
		// save book
		service.saveOrUpdate(book);

		// save book_author
		for (int authorId : listIdAuthor) {
			Book tempBook = new Book(book.getId());
			Author tempAuthor = new Author(authorId);
			BookAuthor bookAuthor = new BookAuthor(tempBook, tempAuthor);
			bookAuthorService.saveOrUpdate(bookAuthor);
		}
		return "redirect:/book/";
	}

	@PostMapping("update")
	public String update(@ModelAttribute("book") Book book) {
		LocalDateTime time = LocalDateTime.now();
		book.setUpdatedAt(time);
		service.saveOrUpdate(book);

		return "redirect:/book/";
	}

	@GetMapping("edit/{id}")
	public String edit(ModelMap model, @PathVariable("id") int id) throws JsonProcessingException {
		Optional<Book> book = service.findById(id);
		// Danh sách tác đã được thêm vào book_author
		List<Author> listAuthors = bookAuthorService.findByBook(id);
		// Danh sách tác giả chưa được thêm vào book_author
		List<Author> authors = authorService.listAuthors(listAuthors);
		model.addAttribute("listAuthors", mapper.writeValueAsString(listAuthors));
		model.addAttribute("authors", mapper.writeValueAsString(authors));
		model.addAttribute("id", id);
		model.addAttribute("book", book);

		return "book/edit";
	}

	@GetMapping("transfer-data/{id}")
	public String transferData(@PathVariable("id") int id, @RequestParam List<Integer> listId) {
		for (int authorId : listId) {
			Book tempBook = new Book(id);
			Author tempAuthor = new Author(authorId);
			BookAuthor bookAuthor = new BookAuthor(tempBook, tempAuthor);
			bookAuthorService.saveOrUpdate(bookAuthor);
		}

		return "redirect:/book/edit/" + id;
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		service.delete(id);

		return "redirect:/book/";
	}

	@PostMapping("remove-author")
	public ResponseEntity<Void> removeAuthor(@RequestParam("listIdAuthor") int idAuthor) {
		bookAuthorService.deleteByAuthor(idAuthor);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("search")
	public String searchBook(ModelMap model, @RequestParam("q") String name,
			@RequestParam(defaultValue = "1") int page) {
		Page<Book> listBooks = service.findLikeName(name, PageRequest.of(page - 1, 10));
		int totalPages = listBooks.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("listBooks", listBooks);

		return "book/index";
	}

	@ModelAttribute("publishings")
	public List<Publishing> getPublishing() {
		return pubService.findAll();
	}

	@ModelAttribute("branchs")
	public List<Branch> getBranch() {
		return branchService.findAll();
	}

	@ModelAttribute("bookCovers")
	public List<BookCover> getBookCover() {
		return bookCoverService.findAll();
	}

	@ModelAttribute("languages")
	public List<Language> getLanguage() {
		return langService.findAll();
	}
}
