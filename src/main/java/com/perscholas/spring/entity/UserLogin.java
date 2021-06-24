package com.perscholas.spring.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="users_login") 
public class UserLogin {
	
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @NotBlank()
    @Pattern(regexp="^[0-9]+$")
    @Column(unique=true)
    @Length(min=10,max=10,message="Please enter a valid 10 digit phone number.")
	private String phoneNumber;
		
    @NotBlank
    @Length(min=8,max=30,message="Password must be at least 8 characters.")
	private String password;
	
	private int loginAttempts;
	
    @UpdateTimestamp
	private LocalDateTime lastLogin;
	
	private boolean disabled = false;
	
	
	public UserLogin() {
		// TODO Auto-generated constructor stub
	}

	public UserLogin(int id, String phoneNumber, String password, int loginAttempts, LocalDateTime lastLogin,
			boolean active) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.loginAttempts = loginAttempts;
		this.lastLogin = lastLogin;
		this.disabled = active;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public boolean isActive() {
		return disabled;
	}

	public void setActive(boolean active) {
		this.disabled = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLogin other = (UserLogin) obj;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		return true;
	}


}
