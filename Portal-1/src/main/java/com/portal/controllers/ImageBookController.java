package com.portal.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.portal.models.Book;
import com.portal.models.ImageBook;
import com.portal.services.ImageBookService;

@Controller
@RequestMapping("image-book")
public class ImageBookController {

	@Autowired
	ImageBookService service;

	@GetMapping("view/{id}")
	public String view(ModelMap model, @PathVariable("id") int id, HttpSession session) {
		Book book = new Book();
		book.setId(id);
		List<ImageBook> listImageBooks = service.findByBook(book);
		model.addAttribute("listImageBooks", listImageBooks);
		session.setAttribute("id", id);

		return "image-book/view";
	}

	@GetMapping("new")
	public String create(ModelMap model) {
		model.addAttribute("imageBook", new ImageBook());

		return "image-book/form";
	}
	/**
	 * 
	 * @param imageBook
	 * @param imageFile
	 * @param model
	 * @param id
	 * @return
	 * @throws IOException
	 */

	@PostMapping("save")
	public String saveOrUpdate(@ModelAttribute("imageBook") ImageBook imageBook,
			@RequestParam("imageFile") MultipartFile imageFile, HttpSession session) throws IOException {
		String image = imageFile.getOriginalFilename();
		int id = (int) session.getAttribute("id");
		imageBook.setName(image);
		service.saveImage(imageFile);
		Book book = new Book(id);
		imageBook.setBook(book);
		service.saveOrUpdate(imageBook);
		
		return "redirect:/image-book/view/" + id;
	}
}
