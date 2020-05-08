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

import com.portal.models.Branch;
import com.portal.services.BranchService;

@Controller
@RequestMapping("branch")
public class BranchController {

	@Autowired
	BranchService service;
	
	@GetMapping(value = {"index", "/"})
	public String index(ModelMap model) {
		List<Branch> listBranchs = service.findAll();
		model.addAttribute("listBranchs", listBranchs);
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
}
