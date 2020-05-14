package com.portal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.portal.models.Book;
import com.portal.models.Branch;
import com.portal.services.BookService;
import com.portal.services.CategoryService;

@Controller
public class HomeController {
	
	@Autowired
	CategoryService categoryService;
	
	
	
	@GetMapping(value = {"index", "/"})
	public String categoryContent(ModelMap model) {		
		List<Object[]> listCategories = categoryService.contents();
		model.addAttribute("listCategories", listCategories);
		
		return "index";
	}
}
