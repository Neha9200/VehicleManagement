package com.perfios.vehicleManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.perfios.vehicleManagement.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	//@Query("SELECT u FROM user u WHERE u.email = ?1")
    public User findByEmail(String email);
	
	//User findByEmailAndPassword(String email, String password);

}
