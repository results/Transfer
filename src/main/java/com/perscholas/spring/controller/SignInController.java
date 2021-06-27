package com.perscholas.spring.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.perscholas.spring.Constants;
import com.perscholas.spring.entity.TransactionWebObject;
import com.perscholas.spring.entity.User;
import com.perscholas.spring.entity.UserInformation;
import com.perscholas.spring.entity.UserLogin;
import com.perscholas.spring.exceptions.UserException;
import com.perscholas.spring.service.UserInformationService;
import com.perscholas.spring.service.UserLoginService;
import com.perscholas.spring.service.UserService;

@Controller
@RequestMapping("/account")
public class SignInController {
	
	@Autowired UserService userService;
	@Autowired UserLoginService loginService;
	@Autowired UserInformationService informationService;
	

	@RequestMapping("/signin")
	public String signIn(HttpSession session,ModelMap model) {
		User user = (User) session.getAttribute("user");
		if(user != null) {
			System.out.println("user logged in");
			return "redirect:"+Constants.SIGNING_IN_PATH;
		} else {
			user = new User();
		}
		user.setLogin(new UserLogin());
		user.setUserInformation(new UserInformation());
		model.addAttribute("user",user);
		model.addAttribute("login",user.getLogin());
		model.addAttribute("userInformation",user.getUserInformation());
		return Constants.SIGNIN_JSP;
	}
	
	@RequestMapping("/signOut")
	public String signOut(@ModelAttribute("user") User user, HttpSession session,ModelMap model) {
		model.addAttribute("message", "");
		user = (User) session.getAttribute("user");
		//if(user != null) {
			model.clear();
			session.invalidate();//clear everything out
			model.addAttribute("loginMessage", "You have been signed out.");
			if(user != null) {
				user.setLoggedIn(false);
			}
		//}	
		return "redirect:"+Constants.SIGNIN_PATH;
	}
	
	@RequestMapping(value = "/submitSignin", method = RequestMethod.POST)
	public String submitSignIn(HttpSession session,ModelMap model,@ModelAttribute("user") User user, @ModelAttribute("login") UserLogin login,BindingResult loginErrors) {
		if((User) session.getAttribute("user") != null) {
			if(((User) session.getAttribute("user")).isLoggedIn()) {
				return "redirect:"+Constants.SIGNING_IN_PATH;
			}
		}
		if(loginErrors.hasErrors()) {
			return Constants.SIGNIN_JSP;
		}
		UserLogin dbLogin = loginService.findByPhoneAndPass(user.getLogin().getPhoneNumber(), user.getLogin().getPassword());
		user = userService.findByLogin(dbLogin);
		if(dbLogin == null || user == null) {
			model.addAttribute("loginMessage", "Invalid sign in details");
			return Constants.SIGNIN_JSP;
		}
		user.setLoggedIn(true);
		addUserDetails(session,user, model);
		model.addAttribute("loginMessage", "");
		model.addAttribute("message", "");
		session.setAttribute("signMes", "Welcome back, "+user.getUserInformation().getName());
	    return "redirect:"+Constants.SIGNING_IN_PATH;
	}
	
	@RequestMapping("/signingin")
	public String signingIn(@ModelAttribute("user") User user, HttpSession session,ModelMap model) {
		user = (User) session.getAttribute("user");
		if(user == null || !user.isLoggedIn()) {
			System.out.println("Redirecting to login.");
			return "redirect:"+Constants.SIGNIN_PATH;
		}
		user.setLoggedIn(true);
		addUserDetails(session, user, model);
		session.setAttribute("message", "");//clear so it doesn't carry over
		if(session.getAttribute("redirect") == null) {
			model.put("redirect", "userhome");
			System.out.println("Directing home");
		}
		if(session.getAttribute("signMes") == null) {
			session.setAttribute("signMes", "Loading Requested Information");
		}
		return Constants.SIGNING_IN_JSP;
	}
	
	public void addUserDetails(HttpSession session,User user, ModelMap map) {
		if(user == null || user.getUserInformation() == null) {
			try {
				throw new UserException("Tried adding null user to session.");
			} catch (UserException e) {
				e.printStackTrace();
				return;
			}
		}
		session.setAttribute("user", user);
		session.setAttribute("name", user.getUserInformation().getName());
		session.setAttribute("logged",user.isLoggedIn());
		session.setAttribute("balance",String.format("%.2f", user.getCurrentBalance()));
		ArrayList<UserInformation> friendsList = new ArrayList<>();
		user.getFriends().stream().forEach(e -> {
			e.getUserInformation().setPhoneNumberLocal(e.getLogin().getPhoneNumber());
			friendsList.add(e.getUserInformation());
		});
		session.setAttribute("friendsList", friendsList);
		ArrayList<TransactionWebObject> trans = new ArrayList<>();
		user.getAllTransactions().stream().forEach(e -> {
			TransactionWebObject webObject = new TransactionWebObject(e.getId(), e.getReceivedBy().getLogin().getPhoneNumber(),
			e.getSentFrom().getLogin().getPhoneNumber(), "$"+String.format("%.2f", e.getAmount()), e.getDescription(), e.getTimestamp().toLocalDate());
			trans.add(webObject);
		});
		session.setAttribute("transactionsList",trans);
	}
	
	
}
