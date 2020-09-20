package com.shivam.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		String getUserQuery = "select email,passkey,user_enabled from user_table where email=?";
		String getRoleQuery = "select email,user_role from user_table where email=?";
		
		/*
		 * AuthenticationManager -> AuthenticationProvider ->authenticate() -> Take help from UserDetailsService to get the provided user details
		 * and then do authentication based on the got information.
		 */
		
		
		
		auth.userDetailsService(userDetailsService);
		//	.jdbcAuthentication().dataSource(dataSource)
		//	.usersByUsernameQuery(getUserQuery)
		//	.authoritiesByUsernameQuery(getRoleQuery)
		//	.rolePrefix("ROLE_");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/userLogin").hasRole("SIGNED_IN_USER")
		.antMatchers("/users/**").hasRole("SIGNED_IN_USER")
		.antMatchers("/").permitAll()
		.and().formLogin()
		.and().logout().logoutSuccessUrl("/homepage");
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	
}
