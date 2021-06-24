package com.perscholas.spring.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.perscholas.spring.entity.User;
import com.perscholas.spring.entity.UserInformation;
import com.perscholas.spring.service.UserInformationService;
import com.perscholas.spring.service.UserLoginService;
import com.perscholas.spring.service.UserService;

@Controller
@RequestMapping("/account")
public class SearchController {
	
	@Autowired UserService userService;
	@Autowired UserLoginService loginService;
	@Autowired UserInformationService informationService;

	@RequestMapping("/search")
	public String search(@RequestParam String keyword,@ModelAttribute("user") User user, HttpSession session,ModelMap model) {
		user = (User) session.getAttribute("user");
		if(user == null || !user.isLoggedIn()) {
			System.out.println("Redirecting to login.");
			return "redirect:/account/signin";
		}
		ArrayList<UserInformation> list = new ArrayList<>();
		loginService.search(keyword).stream().forEach(e -> {
			User users = userService.findByLogin(e);
			users.getUserInformation().setPhoneNumberLocal(e.getPhoneNumber());
			list.add(users.getUserInformation());
		});
		model.put("results", list);
		return "searchusers";
	}
}
