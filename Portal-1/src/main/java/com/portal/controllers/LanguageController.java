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

import com.portal.models.Language;
import com.portal.services.LanguageService;

@Controller
@RequestMapping("lang")
public class LanguageController {

	@Autowired
	LanguageService service;

	@GetMapping(value = { "index", "/" })
	public String index(ModelMap model) {
		try {
			List<Language> listLanguages = service.findAll();
			model.addAttribute("lang", new Language());
			model.addAttribute("listLanguages", listLanguages);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return "lang/index";
	}
	
	@PostMapping("save")
	public String saveOrUpdate(@ModelAttribute("lang")Language lang) {
		service.saveOrUpdate(lang);
		return "redirect:/lang/";
	}
	
	@GetMapping("edit/{id}")
	public @ResponseBody Optional<Language> edit(ModelMap model, @PathVariable("id") int id) {

		return service.findById(id);
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		service.delete(id);
		
		return "redirect:/lang/";
	}
}
