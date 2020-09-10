package com.shivam.Service;

import java.util.List;

import com.shivam.Entity.URL;

public interface UrlService {
	
	public List<URL> findAll();
	
	public URL findById(int id);
	
	public void deleteById(int id);
	
	public  void save(URL url);
	
	public String generateNextSeed(String str);
	
	public String generateShortUrl(String seed, String url);
	
}
