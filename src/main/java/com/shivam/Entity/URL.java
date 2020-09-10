package com.shivam.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="url_table")
public class URL {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@NotNull(message="This is a mandatory field")
	@Size(min=1, message="Enter atleast 1 character")
	@Column(name="full_url")
	private String fullUrl;
	
	@NotNull(message="This is a mandatory field")
	@Column(name="short_url")
	private String shortUrl;
	
	@NotNull(message="This is a mandatory field")
	@Column(name="expiration_date")
	private String expirationDate;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@JsonBackReference //Reference with BackReference won't be returned in JSON response, so breaking the loop
	private User user;

	public URL() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullUrl() {
		return fullUrl;
	}

	public void setFullUrl(String fullUrl) {
		this.fullUrl = fullUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "URL [id=" + id + ", fullUrl=" + fullUrl + ", shortUrl=" + shortUrl + ", expirationDate="
				+ expirationDate + "]";
	}
	
	
}
