package com.shivam.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.shivam.DAO.UrlSeedRepository;
import com.shivam.Entity.UrlSeed;

public class UrlSeedServiceImpl implements UrlSeedService {

	@Autowired
	UrlSeedRepository urlSeedRepository;
	
	@Override
	public List<UrlSeed> findAll() {
		return urlSeedRepository.findAll();
	}

	@Override
	public UrlSeed findById(int id) {
		Optional<UrlSeed> result = urlSeedRepository.findById(id);
		if(result.isEmpty() == true) {
			return null;
		}
		return result.get();
	}

	@Override
	public void deleteById(int id) {
		urlSeedRepository.deleteById(id);
	}

	@Override
	public void save(UrlSeed seed) {
		urlSeedRepository.save(seed);
	}

}
