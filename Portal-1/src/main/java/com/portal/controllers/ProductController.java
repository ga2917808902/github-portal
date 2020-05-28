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

import com.portal.configs.PDFToImage;
import com.portal.models.Book;
import com.portal.models.Comment;
import com.portal.models.ImageBook;
import com.portal.services.BookService;
import com.portal.services.CategoryService;
import com.portal.services.CommentService;
import com.portal.services.ImageBookService;

@Controller
public class ProductController {

	@Autowired
	BookService bookService;

	@Autowired
	CommentService commentService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ImageBookService imageBookService;
	
	@Autowired
	PDFToImage pdfToImage;
	
	static LocalDateTime CREATED_AT;

	@GetMapping("{name}&{id}&{branchId}")
	public String ProductDetail(ModelMap model, @PathVariable("name") String name, @PathVariable("id") int id, @PathVariable("branchId") int branchId,
			HttpSession session) {
		Book book = bookService.findByName(name);
		int views = bookService.processViews(book);
		List<Comment> listComments = commentService.findByBook(new Book(id));
		List<Object[]> listCategories = categoryService.contents();
		List<Book> recommendBooks = bookService.recommendBooks(branchId, id);
		if(recommendBooks != null) {
			model.addAttribute("recommendBooks", recommendBooks);
		}
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("listComments", listComments);
		model.addAttribute("book", book);
		model.addAttribute("views", views);
		model.addAttribute("comment", new Comment());
		model.addAttribute("totalComment", listComments.size());
		session.setAttribute("id", id);
		session.setAttribute("name", name);
		session.setAttribute("branchId", branchId);

		return "product-detail";
	}

	@PostMapping("comment")
	public String comment(ModelMap model, @ModelAttribute("comment") Comment comment, HttpSession session)
			throws UnsupportedEncodingException {
		int idProduct = (Integer) session.getAttribute("id");
		int branchId = (Integer) session.getAttribute("branchId");
		String nameProduct = (String) session.getAttribute("name");
		LocalDateTime now = LocalDateTime.now();
		if (comment.getId() > 0) {
			comment.setCreatedAt(CREATED_AT);
			comment.setUpdatedAt(now);
		} else {
			comment.setCreatedAt(now);
			comment.setUpdatedAt(now);
		}
		commentService.saveOrUpdate(comment);
		String url = nameProduct + "&" + idProduct + "&" + branchId;

		return "redirect:/" + URLEncoder.encode(url, "UTF-8").replace("+", "%20");
	}

	@GetMapping("edit-comment/{id}")
	public @ResponseBody Optional<Comment> edit(@PathVariable("id") long id) {
		Optional<Comment> comment = commentService.findById(id);
		CREATED_AT = comment.get().getCreatedAt();
		return comment;
	}

	@GetMapping("delete-comment/{id}")
	public String deleteComment(@PathVariable("id") long id, HttpSession session) throws UnsupportedEncodingException {
		int idProduct = (Integer) session.getAttribute("id");
		int branchId = (Integer) session.getAttribute("branchId");
		String nameProduct = (String) session.getAttribute("name");
		commentService.delete(id);

		String url = nameProduct + "&" + idProduct + "&" + branchId;
		return "redirect:/" + URLEncoder.encode(url, "UTF-8").replace("+", "%20");
	}

	@GetMapping("updating")
	public String updating() {
		return "updating";
	}
	
	@GetMapping("review/{source}&{id}")
	public String review(ModelMap model, @PathVariable("id") int id, @PathVariable("source") String source) {
		List<Object[]> listCategories = categoryService.contents();
		List<ImageBook> listImageBooks = imageBookService.findByBookID(id);
		pdfToImage.process(listImageBooks, source, id);
		model.addAttribute("listImageBooks", listImageBooks);
		model.addAttribute("listCategories", listCategories);
		
		return "review";
	}

	@RequestMapping("back")
	public String back() {
		return "redirect:/";
	}
}
