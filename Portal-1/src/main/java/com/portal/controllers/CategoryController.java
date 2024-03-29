package com.portal.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portal.ConfigPage;
import com.portal.models.Category;
import com.portal.services.CategoryService;

@Controller
@RequestMapping("category")
public class CategoryController {

	@Autowired
	CategoryService service;
	
	@Autowired
	ConfigPage<Category> configPage;

	/**
	 * 
	 * @param model
	 * @param page mặc định là 1
	 * @return trả về tất cả category
	 */
	@GetMapping(value = { "index", "/" })
	public String index(ModelMap model, @RequestParam(defaultValue = "1") int page) {
		Page<Category> listCategories = service.findAll(PageRequest.of(page - 1, 10));
		Map<String, Integer> pages = configPage.pagination(listCategories);
		model.addAttribute("begin", pages.get("begin"));
		model.addAttribute("end", pages.get("end"));
		model.addAttribute("last", pages.get("last"));
		model.addAttribute("current", pages.get("current"));
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("category", new Category());

		return "category/index";
	}

	@PostMapping("save")
	public String saveOrUpdate(@ModelAttribute("category") Category category) {
		service.saveOrUpdate(category);

		return "redirect:/category/";
	}

	@GetMapping("edit/{id}")
	public @ResponseBody Optional<Category> edit(@PathVariable("id") int id) {
		return service.findById(id);
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		service.delete(id);

		return "redirect:/category/";
	}
}
