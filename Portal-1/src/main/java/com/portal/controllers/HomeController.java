package com.portal.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.portal.ConfigPage;
import com.portal.models.Book;
import com.portal.models.Branch;
import com.portal.models.Category;
import com.portal.services.BookService;
import com.portal.services.BranchService;
import com.portal.services.CategoryService;

@Controller
public class HomeController {

	@Autowired
	CategoryService categoryService;

	@Autowired
	BookService bookService;
	
	@Autowired
	BranchService branchService;
	
	@Autowired
	ConfigPage<Book> configPage;

	@GetMapping(value = { "index", "/" })
	public String categoryContent(ModelMap model) {
		List<Object[]> listCategories = categoryService.contents();
		List<Category> topFourRankViews = categoryService.topFourRankViews();
		List<Book> recentlyBooks = bookService.recentlyBook();
		List<Book> topViews = bookService.topViews();
		bookService.modelItem(model, recentlyBooks);
		model.addAttribute("topViews", topViews);
		model.addAttribute("topFourRankViews", topFourRankViews);
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("recentlyBooks", recentlyBooks);
		
		return "index";
	}

	@GetMapping("rank-views-categor?")
	public String rankViewsCategory(ModelMap model, @RequestParam("q") String query) {
		List<Book> topViews = bookService.TopEightBookViews(query);
		List<Object[]> listCategories = categoryService.contents();
		List<Category> topFourRankViews = categoryService.topFourRankViews();
		List<Book> recentlyBooks = bookService.recentlyBook();
		model.addAttribute("topViews", topViews);
		model.addAttribute("topFourRankViews", topFourRankViews);
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("recentlyBooks", recentlyBooks);

		return "index";
	}

	@GetMapping("search")
	public String searchBook(ModelMap model, @RequestParam("q") String name,
			@RequestParam(defaultValue = "1") int page, @RequestParam(name = "sort", defaultValue = "default") String request) {
		List<Object[]> listCategories = categoryService.contents();
		List<Branch> listBranchs = branchService.recommendBranch(name);
		long start = System.currentTimeMillis();
		Page<Book> listBooks = bookService.sortByRequest(name, page, request);
		long end = System.currentTimeMillis();
		Map<String, Integer> pages = configPage.pagination(listBooks);
		model.addAttribute("time", end - start);
		model.addAttribute("begin", pages.get("begin"));
		model.addAttribute("end", pages.get("end"));
		model.addAttribute("last", pages.get("last"));
		model.addAttribute("current", pages.get("current"));
		model.addAttribute("sort", "sort="+request);
		model.addAttribute("cUrl", "search?q=" + name);
		model.addAttribute("totalItems", listBooks.getTotalElements());
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("listBranchs", listBranchs);
		model.addAttribute("listBooks", listBooks);
		
		return "search";
	}
}
