package com.shivam.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="user_table")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull(message="This is a mandatory field")
	@Size(min=3, max=10, message="First name should be between 3-10 characters")
	@Column(name="first_name")
	private String firstName;
	
	@NotNull(message="This is a mandatory field")
	@Size(min=3, max=10, message="Last name should be between 3-10 characters")
	@Column(name="last_name")
	private String lastName;
	
	@NotNull(message="This is a mandatory field")
	@Size(min=1, max=20, message="Enter valid email")
	@Column(name="email", unique=true)
	@Email
	private String email;
	
	@NotNull(message="This is a mandatory field")
	@Size(min=1, message="Password cannot be empty")
	@Column(name="passkey")
	private String password;
	
	@Column(name="user_role")
	private String userRole;
	
	@Column(name="user_enabled")
	private boolean userEnabled;

	@Column(name="num_urls")
	private int numUrls;
	
	@OneToMany(mappedBy="user")
	List<Url> urlList;
	
	public void addURL(Url url) {
		if(urlList == null) {
			urlList = new ArrayList<Url>();
		}
		urlList.add(url);
	}
	
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	public boolean isUserEnabled() {
		return userEnabled;
	}

	public void setUserEnabled(boolean userEnabled) {
		this.userEnabled = userEnabled;
	}

	public List<Url> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<Url> urlList) {
		this.urlList = urlList;
	}

	public User() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNumUrls() {
		return numUrls;
	}

	public void setNumUrls(int numUrls) {
		this.numUrls = numUrls;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", numUrls=" + numUrls + ", urlList=" + urlList + "]";
	}
	
	
}
