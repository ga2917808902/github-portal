package com.portal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.models.Book;
import com.portal.models.Category;
import com.portal.services.BranchService;

@Controller
@RequestMapping("contents")
public class ContentsController {
	
	@Autowired
	BranchService branchService;

	@GetMapping("{id}")
	public String index(ModelMap model, @PathVariable("id") int id) {
		Category category = new Category(id);
		List<Object[]> listBranchs = branchService.contents(category);
		List<Book> listBooks = branchService.findByBook(id);
		model.addAttribute("listBranchs", listBranchs);
		model.addAttribute("listBooks", listBooks);
		model.addAttribute("id", id);
		return "contents/index";
	}
	
}
