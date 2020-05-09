package com.portal.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.models.Author;
import com.portal.services.AuthorService;

@Controller
@RequestMapping("author")
public class AuthorController {

	@Autowired
	AuthorService service;
	
	@GetMapping(value = {"index", "/"})
	public String index(ModelMap model) throws JsonProcessingException {
		List<Author> listAuthors = service.findAll();
		ObjectMapper mapper = new ObjectMapper();
		model.addAttribute("listAuthors", mapper.writeValueAsString(listAuthors));
		model.addAttribute("author", new Author());
		
		return "author/index";
	}
	
	@PostMapping("save")
	public String saveOrUpdate(@ModelAttribute("author") Author author) {
		service.saveOrUpdate(author);
		
		return "redirect:/author/";
	}
	
	@GetMapping("edit/{id}")
	public @ResponseBody Optional<Author> edit(@PathVariable("id") int id) {
		return service.findById(id);
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		service.delete(id);
		
		return "redirect:/author/";
	}
}
