package com.shivam.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="user_table")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull(message="This is a mandatory field")
	@Size(min=1, message="Enter atleast 1 character")
	@Column(name="first_name")
	private String firstName;
	
	@NotNull(message="This is a mandatory field")
	@Size(min=1, message="Enter atleast 1 character")
	@Column(name="last_name")
	private String lastName;
	
	@NotNull(message="This is a mandatory field")
	@Size(min=1, message="Enter atleast 1 character")
	@Column(name="email")
	private String email;
	
	@NotNull(message="This is a mandatory field")
	@Size(min=1, message="Enter atleast 1 character")
	@Column(name="passkey")
	private String password;
	
	@Column(name="num_urls")
	private int numUrls;
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonManagedReference
	List<URL> urlList;
	
	public void addURL(URL url) {
		if(urlList == null) {
			urlList = new ArrayList<URL>();
		}
		urlList.add(url);
	}
	
	public List<URL> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<URL> urlList) {
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
