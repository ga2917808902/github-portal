package com.portal.controllers;

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

import com.portal.models.BookCover;
import com.portal.services.BookCoverService;

@Controller
@RequestMapping("book-cover")
public class BookCoverController {

	@Autowired
	BookCoverService service;
	
	@GetMapping(value = {"index", "/"})
	public String index(ModelMap model) {
		List<BookCover> listBookCovers = service.findAll();
		model.addAttribute("listBookCovers", listBookCovers);
		model.addAttribute("book-cover", new BookCover());
		
		return "book-cover/index";
	}
	
	@PostMapping("save")
	public String saveOrUpdate(@ModelAttribute("book-cover") BookCover bookCover) {
		service.saveOrUpdate(bookCover);
		
		return "redirect:/book-cover/";
	}
	
	@GetMapping("edit/{id}")
	public @ResponseBody Optional<BookCover> edit(@PathVariable("id") int id) {
		return service.findById(id);
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		service.delete(id);
		
		return "redirect:/book-cover/";
	}
}
