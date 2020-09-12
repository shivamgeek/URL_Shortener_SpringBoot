package com.shivam.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.DAO.UrlRepository;
import com.shivam.DAO.UrlSeedRepository;
import com.shivam.Entity.Url;
import com.shivam.Entity.UrlSeed;

@Service
public class UrlImplService implements UrlService {
	
	@Autowired
	UrlRepository urlRepository;
	
	@Autowired
	UrlSeedRepository urlSeedRepository;

	@Override
	public List<Url> findAll() {
		return urlRepository.findAll();
	}

	@Override
	public Url findById(int id) {
		Optional<Url> result = urlRepository.findById(id);
		if(result.isPresent() == false) {
			return null;
		}
		return result.get();
	}

	@Override
	public void deleteById(int id) {
		urlRepository.deleteById(id);
	}

	@Override
	public void save(Url url) {
		urlRepository.save(url);
	}
	

	@Override
	public String getOneYearLaterDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		cal.add(Calendar.YEAR, 1);
		Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
		return timestamp.toString();
	}
	
	@Override
	public String shortenUrl(String originalUrl) {
		UrlSeed seed  = urlSeedRepository.findById(1).get(); //Get seed value
		String generatedUrl = createShortUrlFromSeed(seed.getSeedValue(), originalUrl); //generate a short-url from seed and long url
		
		String newSeed = generateNextSeed(seed.getSeedValue()); // Get next seed value
		seed.setSeedValue(newSeed); 
		urlSeedRepository.save(seed); //save new seed value
		
		return generatedUrl;
	}
	
	public String generateNextSeed(String str) {
		char arr[] = str.toCharArray();
		int i=arr.length-1;
		for(;i>=0;i--) {
			char x = arr[i];
			if(x == 'Z') {
				continue;
			}else {
				arr[i] = (char) ((char)arr[i]+1);
				break;
			}
		}
		i++;
		for(;i<arr.length;i++) {
			arr[i] = 'A';
		}
		return new String(arr);
	}
	
	//Take last 3 digits of hashcode of original URL and the 3-digit hashseed and interleave them to form a unique string
	public String createShortUrlFromSeed(String seed, String url) {
		StringBuilder sb = new StringBuilder();
		int hash = Math.abs(url.hashCode());
		if(hash<1000) { hash=hash*1000; }
		hash = hash%1000;
		for(int i=0;i<3;i++) {
			sb = sb.append(hash%10);
			hash=hash/10;
			sb = sb.append(seed.charAt(i));
		}
		return sb.toString();
	}
	
}
