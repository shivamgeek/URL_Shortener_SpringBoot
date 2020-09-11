package com.shivam.DAO;

import java.util.List;

import com.shivam.Entity.User;

public interface UserRepositoryCustom {
	
	List<User> loginUser(String email, String password);
	
}
