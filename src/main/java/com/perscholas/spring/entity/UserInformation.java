package com.perscholas.spring.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users_information") 
public class UserInformation {
	
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @NotBlank
	private String name;
	
    @NotBlank
    @Email
    @Column(unique=true)
	private String email;
    
    @Transient
    @Column(insertable = false, updatable = false)
    private String phoneNumberLocal;
	
    public String getPhoneNumberLocal() {
		return phoneNumberLocal;
	}

	public void setPhoneNumberLocal(String phoneNumberLocal) {
		this.phoneNumberLocal = phoneNumberLocal;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	@NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message="Please enter a valid date of birth.")
	private LocalDate birthDate;
	
    @CreationTimestamp
	private LocalDateTime creationDate;
	
    @NotNull
	private boolean hidden = false;
	
	public UserInformation() {
		
	}

	public UserInformation(int id, String name, String email, LocalDate birthDate, LocalDateTime creationDate,
			boolean viewable) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.creationDate = creationDate;
		this.hidden = viewable;
	}
	
	public UserInformation(int id, String name, String email, LocalDate birthDate,
			boolean viewable) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.hidden = viewable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isViewable() {
		return hidden;
	}

	public void setViewable(boolean viewable) {
		this.hidden = viewable;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		UserInformation other = (UserInformation) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	
	
	
	

}
