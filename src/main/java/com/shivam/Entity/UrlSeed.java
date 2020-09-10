package com.shivam.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="url_seed")
public class UrlSeed {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="seed_value")
	private String seedValue;

	public UrlSeed() {
		super();
	}

	public String getSeedValue() {
		return seedValue;
	}

	public void setSeedValue(String seedValue) {
		this.seedValue = seedValue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UrlSeed [id=" + id + ", seedValue=" + seedValue + "]";
	}

	
}
