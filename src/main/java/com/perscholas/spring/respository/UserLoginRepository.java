package com.perscholas.spring.respository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.perscholas.spring.entity.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {

	UserLogin findByPhoneNumber(String phoneNumber);
	
	UserLogin findByPhoneNumberAndPassword(String phoneNumber,String password);
	
    @Query(value = "SELECT u FROM UserLogin u WHERE u.phoneNumber LIKE '%' || :keyword || '%'")
    public ArrayList<UserLogin> search(@Param("keyword") String keyword);



}
