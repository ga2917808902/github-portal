package com.portal.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.portal.models.Book;
import com.portal.services.BookService;
import com.portal.services.CategoryService;

@Controller
public class HomeController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	BookService bookService;

	@GetMapping(value = { "index", "/" })
	public String categoryContent(ModelMap model) {
		List<Object[]> listCategories = categoryService.contents();
		List<Book> recentlyBooks = bookService.recentlyBook();
		List<Book> topViews = bookService.topViews();
		model.addAttribute("topViews", topViews);
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("recentlyBooks", recentlyBooks);

		return "index";
	}

	@GetMapping("search")
	public String searchBook(ModelMap model, @RequestParam("q") String name,
			@RequestParam(defaultValue = "1") int page) {
		List<Object[]> listCategories = categoryService.contents();
		Page<Book> listBooks = bookService.findByLikeName(name.trim(), PageRequest.of(page - 1, 10));
		int totalPages = listBooks.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("listBooks", listBooks);

		return "search";
	}
}
