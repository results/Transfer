package com.perscholas.spring.service;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.perscholas.spring.entity.User;
import com.perscholas.spring.entity.UserInformation;
import com.perscholas.spring.entity.UserLogin;

@SpringBootTest

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {
	
	static long testId;

	@Autowired
	private UserService service;
	
	@Test
	@Order(1)
	void testCreate() {
		User user = new User();
		UserLogin login = new UserLogin();
		login.setPassword("aasdasdasds");
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		login.setPhoneNumber(""+number+"");
		UserInformation info = new UserInformation();
		info.setName("dan");
		info.setEmail(number+"@dan.com");
		info.setBirthDate(LocalDate.now().minusDays(5));
		info.setHidden(false);
		user.setLogin(login);
		user.setUserInformation(info);
		service.save(user);
		testId = user.getId();
		assertNotNull(user.getId());//JPA saved the entity, so not null when we retrieve it
	}
	
	@Test
	@Order(2)
	void testRead() {
		int userID = 1;
		User user = service.get(userID);	
		assertNotNull(user);//if we can get it, its readable
	}
	
	@Test
	@Order(3)
	void testUpdate() {
		int userID = 1;
		double prev = 0;
		User user = service.get(userID);
		prev = user.getCurrentBalance();
		user.setCurrentBalance(user.getCurrentBalance()+10000.00);
		service.save(user);
		user = service.get(userID);
		assertNotEquals(prev,user.getCurrentBalance());//new balance is saved, so doesn't match previous
	}
	
	@Test
	@Order(4)
	void testDelete() {
		int size = service.listAll().size();
		int id;
		User user = service.listAll().get(size-1);
		id = user.getId();
		service.delete(id);
		assertNull(service.get(id));//null because it was deleted
	}
	
	
	
	
	
	
	
	
	

}
