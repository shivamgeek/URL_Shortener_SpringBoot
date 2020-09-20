package com.shivam.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shivam.DAO.UserRepository;
import com.shivam.Entity.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> list = userRepository.findAllUserByEmail(username);
		if(list == null || list.size() <= 0) {
			throw new UsernameNotFoundException("NO user found with the given email!!!");
		}
		User user = list.get(0);
		return new MyUserDetails(user);
	}

}
