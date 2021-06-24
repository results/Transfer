package com.perscholas.spring.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.perscholas.spring.entity.User;
import com.perscholas.spring.entity.UserInformation;
import com.perscholas.spring.entity.UserLogin;
import com.perscholas.spring.service.TransactionService;
import com.perscholas.spring.service.UserInformationService;
import com.perscholas.spring.service.UserLoginService;
import com.perscholas.spring.service.UserService;

@Controller
@RequestMapping("/account")
public class RegistrationController {

	@Autowired UserService userService;
	@Autowired TransactionService tranService;
	@Autowired UserLoginService loginService;
	@Autowired UserInformationService informationService;
	
	@RequestMapping(value = "/register")
	public String redirectRegister(HttpSession session, ModelMap model) {
		User user = (User) session.getAttribute("user");
		if(user != null) {
			return "redirect:/account/signingin";
		} else {
			user = new User();
		}
		user.setLogin(new UserLogin());
		user.setUserInformation(new UserInformation());
		model.addAttribute("user",user);
		model.addAttribute("login",user.getLogin());
		model.addAttribute("userInformation",user.getUserInformation());
		//model.addAttribute("message", "");
		//model.put("user", user);
		//model.put("login", user.getLogin());
		//model.put("userInformation", user.getUserInformation());
		return "register";
	}
	
	@RequestMapping(value = "/submitRegister", method = RequestMethod.POST)
	public String submitRegister(HttpSession session,ModelMap model, @ModelAttribute("user") @Valid User user, BindingResult userErrors,@ModelAttribute("login") UserLogin login,BindingResult loginErrors, @ModelAttribute("userInformation") UserInformation info, BindingResult infoErrors) {
		if(userErrors.hasErrors() || loginErrors.hasErrors() || infoErrors.hasErrors()) {
			return "register";
		} else {
			if(user.isLoggedIn() || (User) session.getAttribute("user") != null) {
				if(((User) session.getAttribute("user")).isLoggedIn()) {
					System.out.println("user logged in");
					return "redirect:/account/signingin";
				}
			}
			if(loginService.find(user.getLogin().getPhoneNumber()) != null) {
				//model.put("pNumber", user.getLogin().getPhoneNumber());
				session.setAttribute("message", "An account with this phone number already exists. Please sign in.");
				return "redirect:/account/signin";
			}
			if(informationService.findByEmail(user.getUserInformation().getEmail()) != null) {
				model.addAttribute("message", "An account with this email already exists. Please sign in.");
				//System.out.println("email error");
				infoErrors.addError(new ObjectError("email", "An account already exists with this email."));
				return "register";
			}
			//user.setUserInformation(info);
			//user.setLogin(login);
			userService.save(user);
			System.out.println("reached");
			model.addAttribute("message", "Account succesfully created. Please Sign in.");
			return "redirect:/account/signin";
		}
	}
	

}
