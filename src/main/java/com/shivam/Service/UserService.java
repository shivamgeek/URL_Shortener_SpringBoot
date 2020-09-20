package com.shivam.Service;

import java.util.List;

import com.shivam.Entity.User;

public interface UserService {
	
	public List<User> findAll();
	
	public User findById(int id);
	
	public void deleteById(int id);
	
	public  void save(User seed);
	
	public User loginUser(String email, String password);
	
	public User findUserByEmail(String email);
	
}
