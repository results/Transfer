package com.perscholas.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perscholas.spring.entity.UserInformation;
import com.perscholas.spring.respository.UserInformationRepository;

@Service
public class UserInformationService {
	
	@Autowired
	private UserInformationRepository repository;
	    
    
	    public void save(UserInformation user) {
	    	repository.save(user);
	    }
	     
	    public List<UserInformation> listAll() {
	        return (List<UserInformation>) repository.findAll();
	    }
	     
	    public UserInformation get(int id) {
	        return repository.findById(id).get();
	    }
	    
	    public UserInformation findByEmail(String email) {
	    	return repository.findByEmail(email);
	    }
	    
	    @Transactional
	    public void delete(int id) {
	    	repository.deleteById(id);
	    }
	    
	    public List<UserInformation> search(String keyword) {
	        return repository.search(keyword);
	    }
	    

}
