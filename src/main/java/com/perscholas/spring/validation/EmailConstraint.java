package com.perscholas.spring.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.perscholas.spring.validation.impl.EmailValidator;

@Documented
@Constraint(validatedBy=EmailValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface EmailConstraint {
	
	String message() default "Invalid email.";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}


