package com.shivam.Service;

import java.util.List;

import com.shivam.Entity.UrlSeed;

public interface UrlSeedService {
	
	public List<UrlSeed> findAll();
	
	public UrlSeed findById(int id);
	
	public void deleteById(int id);
	
	public  void save(UrlSeed seed);
	
}
