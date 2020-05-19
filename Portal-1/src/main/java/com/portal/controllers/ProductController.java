package com.portal.controllers;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.models.Book;
import com.portal.models.Comment;
import com.portal.services.BookService;
import com.portal.services.CommentService;

@Controller
public class ProductController {

	@Autowired
	BookService bookService;
	
	@Autowired
	CommentService commentService;
	
	@GetMapping("{name}&{id}")
	public String ProductDetail(ModelMap model, @PathVariable("name") String name, @PathVariable("id") int id, HttpSession session) {
		Book book = bookService.findByName(name);
		List<Comment> listComments = commentService.findByBook(new Book(id));
		model.addAttribute("listComments", listComments);
		model.addAttribute("book", book);
		model.addAttribute("comment", new Comment());
		session.setAttribute("id", book.getId());
		session.setAttribute("name", name);
		return "product-detail";
	}
	
	@PostMapping("comment")
	public void comment(ModelMap model, @ModelAttribute("comment") Comment comment, HttpSession session) {
		int idProduct = (Integer)session.getAttribute("id");
		String nameProduct = (String)session.getAttribute("name");
		LocalDateTime now = LocalDateTime.now();
		if (comment.getId() > 0) {
			comment.setUpdatedAt(now);
		}else {
			comment.setCreatedAt(now);
			comment.setUpdatedAt(now);
		}
		commentService.saveOrUpdate(comment);
		this.ProductDetail(model, nameProduct, idProduct, session);
//		return "redirect:/" + nameProduct + "&" + idProduct;
	}
	
	@GetMapping("delete-comment/{id}")
	public String deleteComment(@PathVariable("id") long id) {
		commentService.delete(id);
		
		return "redirect:/";
	}
	
	@RequestMapping("updating")
	public String updating() {
		return "updating";
	}
	
	@RequestMapping("back")
	public String back() {
		return "redirect:/";
	}
}
