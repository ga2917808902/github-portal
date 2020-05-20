package com.portal.controllers;

import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	static LocalDateTime CREATED_AT;
	
	@GetMapping("{name}&{id}")
	public String ProductDetail(ModelMap model, @PathVariable("name") String name, @PathVariable("id") int id, HttpSession session) {
		Book book = bookService.findByName(name);
		book.setViews(book.getViews() + 1);
		bookService.saveOrUpdate(book);
		List<Comment> listComments = commentService.findByBook(new Book(id));
		model.addAttribute("listComments", listComments);
		model.addAttribute("book", book);
		model.addAttribute("comment", new Comment());
		session.setAttribute("id", id);
		session.setAttribute("name", name);

		return "product-detail";
	}
	
	@PostMapping("comment")
	public String comment(ModelMap model, @ModelAttribute("comment") Comment comment, HttpSession session) throws UnsupportedEncodingException  {
		int idProduct = (Integer)session.getAttribute("id");
		String nameProduct = (String)session.getAttribute("name");
		LocalDateTime now = LocalDateTime.now();
		if (comment.getId() > 0) {
			comment.setCreatedAt(CREATED_AT);
			comment.setUpdatedAt(now);
		}else {
			comment.setCreatedAt(now);
			comment.setUpdatedAt(now);
		}
		commentService.saveOrUpdate(comment);
		String url = nameProduct + "&" + idProduct;
		
		return "redirect:/" + URLEncoder.encode(url,"UTF-8").replace("+","%20");
	}
	
	@GetMapping("edit-comment/{id}")
	public @ResponseBody Optional<Comment> edit(@PathVariable("id") long id) {
		Optional<Comment> comment  = commentService.findById(id);
		CREATED_AT = comment.get().getCreatedAt();
		return comment;
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
