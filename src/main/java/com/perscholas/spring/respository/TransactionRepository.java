package com.perscholas.spring.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.perscholas.spring.entity.Transaction;

public interface TransactionRepository  extends CrudRepository<Transaction, Integer> {
	
    @Query(value = "SELECT t FROM Transaction t WHERE t.description LIKE '%' || :keyword || '%'"
    		+ " OR t.sentFrom.userInformation.name LIKE '%' || :keyword || '%'"
    		+ " OR t.receivedBy.userInformation.name LIKE '%' || :keyword || '%'"
    		+ " OR t.sentFrom.login.phoneNumber LIKE '%' || :keyword || '%'"
    		+ " OR t.receivedBy.login.phoneNumber LIKE '%' || :keyword || '%'")
    public List<Transaction> search(@Param("keyword") String keyword);
    

}
