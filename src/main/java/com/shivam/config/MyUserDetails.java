package com.shivam.config;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.shivam.Entity.User;

public class MyUserDetails implements UserDetails{

	private static final long serialVersionUID = 1L;

	private String email, password, userRole;
	boolean userEnabled;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_"+userRole));
	}
	
	public MyUserDetails(User user) {
		email = user.getEmail();
		password = user.getPassword();
		userEnabled = user.isUserEnabled();
		userRole = user.getUserRole();
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return userEnabled;
	}

}
