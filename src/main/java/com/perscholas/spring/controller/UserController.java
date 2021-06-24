package com.perscholas.spring.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.perscholas.spring.Constants;
import com.perscholas.spring.Utilities;
import com.perscholas.spring.entity.User;
import com.perscholas.spring.entity.UserInformation;
import com.perscholas.spring.service.UserInformationService;
import com.perscholas.spring.service.UserLoginService;
import com.perscholas.spring.service.UserService;

@Controller
@RequestMapping("/account")
public class UserController {

	@Autowired UserService userService;
	@Autowired UserLoginService loginService;
	@Autowired UserInformationService informationService;
	
	

	@RequestMapping("/userhome")
	public String userHome(@ModelAttribute("user") User user, HttpSession session,ModelMap model) {
		user = (User) session.getAttribute("user");
		if(user == null || !user.isLoggedIn()) {
			return "redirect:"+Constants.SIGNIN_PATH;

		}
		model.addAttribute("friendCount",user.getFriends().size());
		model.addAttribute("transCount",user.getTotalTransactions());
		session.removeAttribute("redirect");
		session.removeAttribute("signMes");
		//addUserDetails(session, user, model);
		return Constants.USERHOME_JSP;
	}
	
	@RequestMapping("/viewUser/{userPhone}")
	public String viewUser(@PathVariable String userPhone, @ModelAttribute("user") User user, HttpSession session, ModelMap model) {
		User profile = userService.findByLogin(loginService.find(userPhone));
		if(profile == null || profile.getUserInformation() == null || !profile.getUserInformation().isViewable()) {
			return "notfound";
		}
		profile.getUserInformation().setPhoneNumberLocal(userPhone);
		user = (User) session.getAttribute("user");
		if(user != null && user.isLoggedIn()) {
				if(user.equals(profile) || user.getLogin().getPhoneNumber().equals(userPhone)) {
					System.out.println("Same page");
					return "redirect:"+Constants.USERHOME_PATH;
				}
				session.removeAttribute("redirect");
				session.setAttribute("profile", profile.getUserInformation());
				model.put("profileIsFriend", user.getFriends().contains(profile));
				model.put("profileName", profile.getUserInformation().getName());
				model.put("profileActiveNow", profile.isLoggedIn());
				model.put("profilePhone", userPhone);
				model.put("profilePhoneFMT", Utilities.formatPhoneNumber(userPhone));
				model.put("profileTransCount", profile.getTotalTransactions());
				model.put("profileFriendsCount", profile.getFriends().size());
				model.put("profileCreated", profile.getUserInformation().getCreationDate().toLocalDate());
				return Constants.VIEW_USER_JSP;
		} else {
			session.setAttribute("redirect", "viewUser/"+userPhone);
			return "redirect:"+Constants.SIGNIN_PATH;
		}
	}
	
	@RequestMapping("/aorFriend/{userPhone}")
	public String addOrRemoveFriend(@PathVariable String userPhone, @ModelAttribute("user") User user, HttpSession session, ModelMap model) {
		User profile = userService.findByLogin(loginService.find(userPhone));
		user = (User) session.getAttribute("user");
		if(user != null && user.isLoggedIn()) {
			if(user.equals(profile) || user.getLogin().getPhoneNumber().equals(userPhone)) {
				return "redirect:"+Constants.USERHOME_PATH;
			}
			if(user.getFriends().contains(profile)) {
				user.getFriends().remove(profile);
			} else {
				user.getFriends().add(profile);

			}
			userService.save(user);
			ArrayList<UserInformation> friendsList = new ArrayList<>();
			user.getFriends().stream().forEach(e -> {
				e.getUserInformation().setPhoneNumberLocal(e.getLogin().getPhoneNumber());
				friendsList.add(e.getUserInformation());
			});
			model.addAttribute("friendCount",user.getFriends().size());
			session.setAttribute("friendsList", friendsList);
		}
		return "redirect:"+Constants.VIEW_USER_PATH+"/"+userPhone;
	}
	
	@RequestMapping("/recovery")
	public String redirectRecovery() {
		return "recovery";
	}
	
}
