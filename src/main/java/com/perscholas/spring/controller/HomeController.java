package com.perscholas.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.perscholas.spring.service.TransactionService;
import com.perscholas.spring.service.UserLoginService;

@Controller
public class HomeController {
	
	@Autowired UserLoginService loginService;
	@Autowired TransactionService transactionService;
		
	@RequestMapping("/")
	public String index(HttpSession session) {
		session.setAttribute("totalAccounts", loginService.listAll().size());
		session.setAttribute("totalTrans", transactionService.listAll().size());
		return "index";
	}


}
