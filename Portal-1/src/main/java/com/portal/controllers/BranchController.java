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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.portal.models.Branch;
import com.portal.models.Category;
import com.portal.services.BranchService;
import com.portal.services.CategoryService;

@Controller
@RequestMapping("branch")
public class BranchController {

	@Autowired
	BranchService service;
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping(value = {"index", "/"})
	public String index(ModelMap model) throws JsonProcessingException {
		List<Branch> listBranchs = service.findAll();
		ObjectMapper mapper = new ObjectMapper();
		//Config FAIL_ON_EMPTY_BEANS khi dùng để tranh lỗi empty bean
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		model.addAttribute("listBranchs", mapper.writeValueAsString(listBranchs));
		model.addAttribute("branch", new Branch());
		
		return "branch/index";
	}
	
	@PostMapping("save")
	public String saveOrUpdate(@ModelAttribute("branch") Branch branch) {
		service.saveOrUpdate(branch);
		
		return "redirect:/branch/";
	}
	
	@GetMapping("edit/{id}")
	public @ResponseBody Optional<Branch> edit(@PathVariable("id") int id) {
		return service.findById(id);
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") int id) {
		service.delete(id);
		
		return "redirect:/branch/";
	}
	
	@ModelAttribute("categories")
	public List<Category> listCategories(){
		return categoryService.findAll();
	}
}
