package com.perscholas.spring.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="users") 
public class User {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
    private double currentBalance = 0.00;
	
    @OneToOne(cascade= {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.REFRESH})
    @NotNull
    @Valid
	private UserLogin login;
	
    @OneToOne(cascade= {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.REFRESH})
    @NotNull
    @Valid
	private UserInformation userInformation;
	
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Transaction> sentTransactions;
    
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Transaction> receivedTransactions;
    
    @Transient
    public int getTotalTransactions() {
    	return sentTransactions.size()+receivedTransactions.size();
    }
    
    @Transient
    public List<Transaction> getAllTransactions() {
    	List<Transaction> list = new ArrayList<Transaction>();
    	list.addAll(receivedTransactions);
    	list.addAll(sentTransactions);
    	return list;
    }
    
    public User(@NotNull int id, double currentBalance, @NotNull @Valid UserLogin login,
			@NotNull @Valid UserInformation userInformation, List<Transaction> sentTransactions,
			List<Transaction> receivedTransactions, List<User> friends, boolean loggedIn, String redirectURL) {
		this.id = id;
		this.currentBalance = currentBalance;
		this.login = login;
		this.userInformation = userInformation;
		this.sentTransactions = sentTransactions;
		this.receivedTransactions = receivedTransactions;
		this.friends = friends;
		this.loggedIn = loggedIn;
		this.redirectURL = redirectURL;
	}

	public List<Transaction> getSentTransactions() {
		return sentTransactions;
	}

	public void setSentTransactions(List<Transaction> sentTransactions) {
		this.sentTransactions = sentTransactions;
	}

	public List<Transaction> getReceivedTransactions() {
		return receivedTransactions;
	}

	public void setReceivedTransactions(List<Transaction> receivedTransactions) {
		this.receivedTransactions = receivedTransactions;
	}

	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH})
	private List<User> friends;
    
    @Transient
    @Column(insertable = false, updatable = false)
    private boolean loggedIn;
    
    @Transient
    @Column(insertable = false, updatable = false)
    private String redirectURL;
    
    public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getRedirectURL() {
		return redirectURL;
	}

	public void setRedirectURL(String redirectURL) {
		this.redirectURL = redirectURL;
	}

	public User() {

	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserLogin getLogin() {
		return login;
	}

	public void setLogin(UserLogin login) {
		this.login = login;
	}

	public UserInformation getUserInformation() {
		return userInformation;
	}

	public void setUserInformation(UserInformation userInformation) {
		this.userInformation = userInformation;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((userInformation == null) ? 0 : userInformation.hashCode());
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
		User other = (User) obj;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (userInformation == null) {
			if (other.userInformation != null)
				return false;
		} else if (!userInformation.equals(other.userInformation))
			return false;
		return true;
	}

	
	
	
}
