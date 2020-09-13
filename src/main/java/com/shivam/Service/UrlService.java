package com.shivam.Service;

import java.util.List;

import com.shivam.Entity.Url;

public interface UrlService {
	
	public List<Url> findAll();
	
	public Url findById(int id);
	
	public void deleteById(int id);
	
	public  void save(Url url);
	
	public String shortenUrl(String originalUrl);
	
	public String getOneYearLaterDate();
	
	public String isUrlReachable(String url);
	
}
