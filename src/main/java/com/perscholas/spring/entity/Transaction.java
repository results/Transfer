package com.perscholas.spring.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.NumberFormat;



@Entity
@Table(name = "transactions")
public class Transaction {

	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(cascade= {CascadeType.MERGE,CascadeType.REMOVE})
	@NotNull
	private User sentFrom;

	@OneToOne(cascade= {CascadeType.MERGE,CascadeType.REMOVE})
	@NotNull
	private User receivedBy;

	@NotNull
	@NumberFormat(pattern="^[0-9.]+$)")
	@Digits(integer = 8, fraction = 2,message="Amount must have be in dollar and cent format")
	private double amount = 1.00;
	
	@NotNull
	private boolean viewable;
	
	private String description;

    @CreationTimestamp
	private LocalDateTime timestamp;
		
	public Transaction() {
		
	}
		
	public Transaction(@NotNull int id, @NotNull User sentFrom, @NotNull User receivedBy,
			@NotNull @Digits(integer = 8, fraction = 2, message = "Amount must have be in dollar and cent format") double amount,
			@NotNull boolean viewable, String description, @NotNull LocalDateTime timestamp) {
		this.id = id;
		this.sentFrom = sentFrom;
		this.receivedBy = receivedBy;
		this.amount = amount;
		this.viewable = viewable;
		this.description = description;
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getSentFrom() {
		return sentFrom;
	}

	public void setSentFrom(User sentFrom) {
		this.sentFrom = sentFrom;
	}

	public User getReceivedBy() {
		return receivedBy;
	}

	public void setReceivedBy(User receivedBy) {
		this.receivedBy = receivedBy;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public boolean isViewable() {
		return viewable;
	}

	public void setViewable(boolean viewable) {
		this.viewable = viewable;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((receivedBy == null) ? 0 : receivedBy.hashCode());
		result = prime * result + ((sentFrom == null) ? 0 : sentFrom.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
		Transaction other = (Transaction) obj;
		if (id != other.id)
			return false;
		if (receivedBy == null) {
			if (other.receivedBy != null)
				return false;
		} else if (!receivedBy.equals(other.receivedBy))
			return false;
		if (sentFrom == null) {
			if (other.sentFrom != null)
				return false;
		} else if (!sentFrom.equals(other.sentFrom))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}
	
	
	


}
