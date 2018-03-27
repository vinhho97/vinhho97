package com.hoangvinh.springboot.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.hoangvinh.springboot.admin.service.PostService;

@Controller
public class MainController extends AbstractController {

	@Autowired
	PostService postService;

	@GetMapping("/")
	public String homePage(Model model) {
		return super.getCategoryByID(model, "client/web/index");
	}

	@GetMapping("/login")
	public String loginPage(Model model, @RequestParam(name = "e", required = false) String error) {
		if (error != null) {
			model.addAttribute("e", error);
		}
		return super.getCategoryByID(model, "login");
	}

	@GetMapping("/category/{id}")
	public String showPostByCategoryID(Model model, @PathVariable(name = "id") Long id) {
		model.addAttribute("listPost", postService.getAllPostByCategoryID(id));
		return super.getCategoryByID(model, "client/web/category");
	}
}
