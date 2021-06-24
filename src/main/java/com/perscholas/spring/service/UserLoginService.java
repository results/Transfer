package com.perscholas.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perscholas.spring.entity.UserLogin;
import com.perscholas.spring.respository.UserLoginRepository;

@Service
public class UserLoginService {
	
	@Autowired
	private UserLoginRepository repository;
	    
    
	    public void save(UserLogin login) {
	    	repository.save(login);
	    }
	     
	    public List<UserLogin> listAll() {
	        return (List<UserLogin>) repository.findAll();
	    }
	     
	    public UserLogin get(int id) {
	        Optional<UserLogin> login = repository.findById(id);
	        return login.isPresent() ? login.get() : null;
	    }
	    
	    public UserLogin find(String phone) {
	        UserLogin login = repository.findByPhoneNumber(phone);
	        return login;
	    }
	    
	    public UserLogin findByPhoneAndPass(String phone,String password) {
	        UserLogin login = repository.findByPhoneNumberAndPassword(phone,password);
	        return login;
	    }
	    
	    @Transactional
	    public void delete(int id) {
	    	repository.deleteById(id);
	    }
	    
	    public ArrayList<UserLogin> search(String keyword) {
	        return repository.search(keyword);
	    }

	    
	    
}
