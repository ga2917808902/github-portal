package com.portal.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.models.Book;
import com.portal.models.Branch;
import com.portal.models.Category;
import com.portal.services.BookService;
import com.portal.services.BranchService;

@Controller
@RequestMapping("contents")
public class ContentsController {
	
	@Autowired
	BranchService branchService;

	@Autowired
	BookService bookService;
	
	@GetMapping("{id}")
	public String index(ModelMap model, @PathVariable("id") int id, HttpSession session) {
		Category category = new Category(id);
		List<Object[]> listBranchs = branchService.contents(category);
		List<Book> listBooks = branchService.findByBook(id);
		model.addAttribute("listBranchs", listBranchs);
		model.addAttribute("listBooks", listBooks);
		session.setAttribute("idCategory", id);
		
		return "contents/index";
	}
	
	@GetMapping("{item[0]}/{item[1]}")
	public String branchOfBook(ModelMap model, @PathVariable("item[0]") int id, @PathVariable("item[1]") String name, HttpSession session) {
		int idCategory = (Integer) session.getAttribute("idCategory");
		this.index(model, idCategory, session);
		Branch branch = new Branch(id, name);
		List<Book> listBooks = bookService.findByBranch(branch);
		model.addAttribute("listBooks", listBooks);
		
		return "contents/index";
	}
	
}
