package com.portal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.portal.services.CategoryService;

@Controller
public class HomeController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping(value = {"index", "/"})
	public String categoryContent(ModelMap model, @RequestParam(defaultValue = "1") int page) {
		List<Object[]> listCategories = categoryService.contents();
		model.addAttribute("listCategories", listCategories);
		
		return "index";
	}
}
