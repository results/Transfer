package com.perscholas.spring.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.perscholas.spring.entity.User;
import com.perscholas.spring.entity.UserInformation;
import com.perscholas.spring.entity.UserLogin;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByLogin(UserLogin loginID);
	
	User findByUserInformation(UserInformation information);

}
