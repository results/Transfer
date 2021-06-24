package com.perscholas.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.perscholas.spring.Constants;
import com.perscholas.spring.Utilities;
import com.perscholas.spring.entity.Transaction;
import com.perscholas.spring.entity.User;
import com.perscholas.spring.exceptions.BalanceException;
import com.perscholas.spring.service.TransactionService;
import com.perscholas.spring.service.UserInformationService;
import com.perscholas.spring.service.UserLoginService;
import com.perscholas.spring.service.UserService;


@Controller
@RequestMapping("/transfers")
public class TransactionController {

	@Autowired TransactionService service;
	@Autowired UserService userService;
	@Autowired UserLoginService loginService;
	@Autowired UserInformationService informationService;
	
	@RequestMapping("/createTransfer/{profilePhone}")
	public String createTransfer(@PathVariable String profilePhone, @ModelAttribute("transaction") Transaction trans, @ModelAttribute("user") User user, HttpSession session, ModelMap model) {
		user = (User) session.getAttribute("user");
		if(user == null || !user.isLoggedIn()) {
			session.setAttribute("redirect", "/transfers/createTransfer/"+profilePhone);
			return "redirect:"+Constants.SIGNIN_PATH;
		}
		session.setAttribute("toPhone", profilePhone);
		Transaction transaction = new Transaction();
		transaction.setReceivedBy(userService.findByLogin(loginService.find(profilePhone)));
		transaction.setSentFrom(user);
		if(transaction.getReceivedBy() == null || transaction.getSentFrom() == null 
				|| transaction.getSentFrom() == transaction.getReceivedBy()) {
			return Constants.ERROR_JSP;		
		}
		session.setAttribute("transaction", transaction);
		return Constants.TRANSFER_JSP;
	}
	
	@RequestMapping(value = "/submitTransfer", method = RequestMethod.POST)
	public String submitTransaction(@ModelAttribute("transaction") Transaction trans, @ModelAttribute("user") User user, BindingResult errors, HttpSession session, ModelMap model) {
		user = (User) session.getAttribute("user");
		Transaction completedTrans = (Transaction) session.getAttribute("transaction");
		String toPhone = (String) session.getAttribute("toPhone");
		if(user == null || !user.isLoggedIn()) {
			session.setAttribute("redirect", "/transfers/createTransfer/"+toPhone);
			return "redirect:"+Constants.SIGNIN_PATH;
		}
		User received = userService.findByLogin(loginService.find(toPhone));
		if(trans == null || completedTrans == null || received == null) {
			return "redirect:"+Constants.USERHOME_PATH;
		}
		if(user.getCurrentBalance() < trans.getAmount()) {
			errors.addError(new ObjectError("amount", "Not enough funds."));
			session.setAttribute("message", "Transfer more than balance.");
			try {
				throw new BalanceException("Tried to send more money then they had");
			} catch (BalanceException e) {
				return Constants.TRANSFER_JSP;
			}
		}
		if(errors.hasErrors()) {
			return Constants.TRANSFER_JSP;
		}
		session.removeAttribute("redirect");
		session.removeAttribute("transaction");
		session.removeAttribute("toPhone");
		user.setCurrentBalance(user.getCurrentBalance()-trans.getAmount());
		System.out.println(received.getCurrentBalance());
		received.setCurrentBalance(received.getCurrentBalance()+trans.getAmount());
		System.out.println(received.getCurrentBalance());
		completedTrans.setAmount(trans.getAmount());
		completedTrans.setDescription(trans.getDescription());
		completedTrans.setViewable(trans.isViewable());
		completedTrans.setReceivedBy(received);
		user.getSentTransactions().add(completedTrans);
		received.getReceivedTransactions().add(completedTrans);
		service.save(completedTrans);
		userService.save(completedTrans.getSentFrom());
		userService.save(received);
		return "redirect:"+Constants.COMPLETED_TRANS_PATH+"/"+completedTrans.getId();	
	}
	
	@RequestMapping("/completed/{transID}")
	public String completed(@PathVariable int transID, @ModelAttribute("transaction") Transaction trans, @ModelAttribute("user") User user, HttpSession session, ModelMap model) {
		Transaction transaction = service.get(transID);
		if(transaction == null || !transaction.isViewable()) {
			return Constants.NOT_FOUND_JSP;
		}
		model.addAttribute("transactionAmount",String.format("%.02f", transaction.getAmount()));
		model.addAttribute("transactionComment",transaction.getDescription());
		model.addAttribute("transactionFrom",Utilities.formatPhoneNumber(transaction.getSentFrom().getLogin().getPhoneNumber()));
		model.addAttribute("transactionTo",Utilities.formatPhoneNumber(transaction.getReceivedBy().getLogin().getPhoneNumber()));
		model.addAttribute("transactionDate",transaction.getTimestamp().toLocalDate());
		return Constants.COMPLETED_TRANS_JSP;
	}
	
	@RequestMapping("/delete/{transID}")
	public String delete(@PathVariable int transID, @ModelAttribute("transaction") Transaction trans, @ModelAttribute("user") User user, HttpSession session, ModelMap model) {
		Transaction transaction = service.get(transID);
		if(transaction == null) {
			return Constants.NOT_FOUND_JSP;
		}
		user = (User) session.getAttribute("user");
		if(user == null || !user.isLoggedIn()) {
			return "redirect:"+Constants.SIGNIN_PATH;
		}
		if(user.getReceivedTransactions().contains(transaction)) {
			user.getReceivedTransactions().remove(transaction);
		} else if(user.getSentTransactions().contains(transaction)) {
			user.getSentTransactions().remove(transaction);
		}
		return "redirect:"+Constants.SIGNING_IN_PATH;
	}
	
	@RequestMapping("/add")
	public String addMoney(@ModelAttribute("user") User user, HttpSession session, ModelMap model) {
		user = (User) session.getAttribute("user");
		if(user == null || !user.isLoggedIn()) {
			return "redirect:"+Constants.SIGNIN_PATH;
		}
		user.setCurrentBalance(user.getCurrentBalance()+10000);
		session.setAttribute("balance", user.getCurrentBalance());
		userService.save(user);
		return "redirect:"+Constants.USERHOME_PATH;
	}
	
}
