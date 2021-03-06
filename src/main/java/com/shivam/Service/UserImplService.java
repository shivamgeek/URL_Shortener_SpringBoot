package com.shivam.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.DAO.UserRepository;
import com.shivam.Entity.User;

@Service
public class UserImplService implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(int id) {
		Optional<User> result = userRepository.findById(id);
		if(result.isPresent() == false) {
			return null;
		}
		return result.get();
	}

	@Override
	public void deleteById(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);
	}
	
	public User loginUser(String email, String password) {
		List<User> list = userRepository.loginUser(email, password);
		if(list.size() == 0 || list==null) {
			return null;
		}
		return list.get(0);
	}

	public User findUserByEmail(String email) {
		User user = userRepository.findUserByEmail(email);
		return user;	
	}

}
