package com.portal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portal.models.Book;
import com.portal.models.ImageBook;
import com.portal.services.ImageBookService;

@Controller
@RequestMapping("image-book")
public class ImageBookController {
	
	@Autowired
	ImageBookService service;

	@RequestMapping("view/{id}")
	public String view(ModelMap model, @PathVariable("id") int id) {
		Book book = new Book();
		book.setId(id);
		List<ImageBook> listImageBooks = service.findByBook(book);
		model.addAttribute("listImageBooks", listImageBooks);
		
		return "image-book/view";
	}
}
