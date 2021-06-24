package com.perscholas.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perscholas.spring.entity.User;
import com.perscholas.spring.entity.UserLogin;
import com.perscholas.spring.respository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	    
    
	    public void save(User user) {
	    	repository.save(user);
	    }
	     
	    public List<User> listAll() {
	        return (List<User>) repository.findAll();
	    }
	     	    
	    public User get(int id) {
	        Optional<User> user = repository.findById(id);
	        return user.isPresent() ? user.get() : null;
	    }
	    
	    @Transactional
	    public void delete(int id) {
	    	repository.deleteById(id);
	    }
	    
	    public User findByLogin(UserLogin login) {
	    	User user = repository.findByLogin(login);
	    	if(user != null) {
	    		//Hibernate.initialize(user.getFriends());
	    		//Hibernate.initialize(user.getSentTransactions());
	    		//Hibernate.initialize(user.getReceivedTransactions());
	    	}
	    	return user;
	    }
	    
	    
	    

}
