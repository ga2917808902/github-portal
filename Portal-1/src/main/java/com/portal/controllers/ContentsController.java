package com.portal.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.portal.ConfigPage;
import com.portal.models.Book;
import com.portal.models.Branch;
import com.portal.models.Category;
import com.portal.services.BookService;
import com.portal.services.BranchService;

@Controller
@RequestMapping("contents")
public class ContentsController {

	@Autowired
	BranchService branchService;

	@Autowired
	BookService bookService;

	@Autowired
	ConfigPage<Book> configPage;

	/**
	 * Hàm dùng để xử lý thư mục của loại và tất show tất cả book của loại đó (bao
	 * gồm ngành của loại đó)
	 * 
	 * @param model
	 * @param page    mặc định là 1
	 * @param id      của loại
	 * @param session id, set id để những phần liên quan get về sử dụng
	 * @return trả về danh sách ngành của thư mục và tất cả sách của thư mục đó
	 */
	@GetMapping("{id}")
	public String index(ModelMap model, @RequestParam(defaultValue = "1") int page, @PathVariable("id") int id,
			HttpSession session) {
		List<Object[]> listBranchs = branchService.contents(new Category(id));
		Page<Book> listBooks = bookService.findByBook(id, PageRequest.of(page - 1, 20));
		Map<String, Integer> pages = configPage.pagination(listBooks);
		model.addAttribute("begin", pages.get("begin"));
		model.addAttribute("end", pages.get("end"));
		model.addAttribute("last", pages.get("last"));
		model.addAttribute("current", pages.get("current"));
		model.addAttribute("listBranchs", listBranchs);
		model.addAttribute("listBooks", listBooks);
		session.setAttribute("idCategory", id);

		return "contents/index";
	}

	/**
	 * Hàm dùng để search theo id và tên của ngành
	 * 
	 * @param model
	 * @param page     mặc định là 1
	 * @param id       của ngành
	 * @param name     của ngành
	 * @param session, get id
	 * @return trả về danh sách của ngành và tất cả sách của ngành đó
	 */
	@GetMapping("{item[0]}/{item[1]}")
	public String branchOfBook(ModelMap model, @RequestParam(defaultValue = "1") int page,
			@PathVariable("item[0]") int id, @PathVariable("item[1]") String name, HttpSession session) {
		int idCategory = (Integer) session.getAttribute("idCategory");
		List<Object[]> listBranchs = branchService.contents(new Category(idCategory));
		Branch branch = new Branch(id, name);
		Page<Book> listBooks = bookService.findByBranch(branch, PageRequest.of(page - 1, 20));
		Map<String, Integer> pages = configPage.pagination(listBooks);
		model.addAttribute("begin", pages.get("begin"));
		model.addAttribute("end", pages.get("end"));
		model.addAttribute("last", pages.get("last"));
		model.addAttribute("current", pages.get("current"));
		model.addAttribute("listBranchs", listBranchs);
		model.addAttribute("listBooks", listBooks);
		model.addAttribute("id", id);
		model.addAttribute("name", name);

		return "contents/branchOfBook";
	}

}
