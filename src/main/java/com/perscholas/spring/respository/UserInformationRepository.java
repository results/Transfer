package com.perscholas.spring.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.perscholas.spring.entity.UserInformation;

public interface UserInformationRepository extends CrudRepository<UserInformation, Integer> {
	
    @Query(value = "SELECT u.userInformation FROM User u WHERE u.userInformation.name LIKE '%' || :keyword || '%'"
            + " OR u.userInformation.email LIKE '%' || :keyword || '%'"
            + " OR u.login.phoneNumber LIKE '%' || :keyword || '%'")
    public List<UserInformation> search(@Param("keyword") String keyword);
    
    UserInformation findByEmail(String email);


}
