package com.shivam.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shivam.DAO.UrlRepository;
import com.shivam.Entity.URL;

@Service
public class UrlImplService implements UrlService {
	
	@Autowired
	UrlRepository urlRepository;

	@Override
	public List<URL> findAll() {
		return urlRepository.findAll();
	}

	@Override
	public URL findById(int id) {
		Optional<URL> result = urlRepository.findById(id);
		if(result.isEmpty() == true) {
			return null;
		}
		return result.get();
	}

	@Override
	public void deleteById(int id) {
		urlRepository.deleteById(id);
	}

	@Override
	public void save(URL url) {
		urlRepository.save(url);
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
	public String generateShortUrl(String seed, String url) {
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
