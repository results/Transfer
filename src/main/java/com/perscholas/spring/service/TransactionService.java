package com.perscholas.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perscholas.spring.entity.Transaction;
import com.perscholas.spring.respository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository repository;
	
    public void save(Transaction tran) {
    	repository.save(tran);
    }
     
    public List<Transaction> listAll() {
        return (List<Transaction>) repository.findAll();
    }
     
    public Transaction get(int id) {
        Optional<Transaction> tran = repository.findById(id);
        return tran.isPresent() ? tran.get() : null;
    }
    
  
    @Transactional
    public void delete(int id) {
    	repository.deleteById(id);
    }
	
}
