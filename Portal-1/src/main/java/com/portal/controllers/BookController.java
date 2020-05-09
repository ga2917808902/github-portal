package com.portal.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.models.Book;
import com.portal.models.BookCover;
import com.portal.models.Branch;
import com.portal.models.Language;
import com.portal.models.Publishing;
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
	
	@GetMapping(value = {"index", "/"})
	public String index(ModelMap model) throws JsonProcessingException {
		List<Book> listBooks = service.findAll();
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("listBooks", mapper.writeValueAsString(listBooks));
		model.addAttribute("book", new Book());
		
		return "book/index";
	}
	
	@PostMapping("save")
	public String saveOrUpdate(@ModelAttribute("book") Book book) {
		LocalDateTime time = LocalDateTime.now();
		if(book.getId() != 0) {
			book.setUpdatedAt(time);
		}else {
			book.setCreatedAt(time);
			book.setUpdatedAt(time);
		}
		service.saveOrUpdate(book);
		
		return "redirect:/book/";
	}
	
	@GetMapping("edit/{id}")
	public @ResponseBody Optional<Book> edit(@PathVariable("id") int id) {
		return service.findById(id);
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		service.delete(id);
		
		return "redirect:/book/";
	}
	
	@ModelAttribute("publishings")
	public List<Publishing> getPublishing(){
		return pubService.findAll();
	}
	
	@ModelAttribute("branchs")
	public List<Branch> getBranch(){
		return branchService.findAll();
	}
	
	@ModelAttribute("bookCovers")
	public List<BookCover> getBookCover(){
		return bookCoverService.findAll();
	}
	
	@ModelAttribute("languages")
	public List<Language> getLanguage(){
		return langService.findAll();
	}
}
