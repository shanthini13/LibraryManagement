package com.equifax.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.equifax.library.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
	
	public User findByMailId(String mailId);
	

}