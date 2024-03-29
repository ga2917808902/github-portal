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


import com.portal.models.Publishing;
import com.portal.services.PublishingService;

@Controller
@RequestMapping("publishing")
public class PublishingController {

	@Autowired
	PublishingService service;
	
	@GetMapping(value = { "index", "/" })
	public String index(ModelMap model) {
		try {
			List<Publishing> listPublishings = service.findAll();
			model.addAttribute("publishing", new Publishing());
			model.addAttribute("listPublishings", listPublishings);
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return "publishing/index";
	}
	
	@PostMapping("save")
	public String saveOrUpdate(@ModelAttribute("publishing")Publishing publishing) {
		service.saveOrUpdate(publishing);
		return "redirect:/publishing/";
	}
	
	@GetMapping("edit/{id}")
	public @ResponseBody Optional<Publishing> edit(ModelMap model, @PathVariable("id") int id) {

		return service.findById(id);
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		service.delete(id);
		
		return "redirect:/publishing/";
	}
}
