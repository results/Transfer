package com.perscholas.spring.validation.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.perscholas.spring.service.UserInformationService;
import com.perscholas.spring.validation.EmailConstraint;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String>{
	
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
		
	@Override
	public void initialize(EmailConstraint em) {
	}
	
	@Autowired UserInformationService infoService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(value);
		return matcher.find() ? infoService.findByEmail(value) != null : false;
	}
}