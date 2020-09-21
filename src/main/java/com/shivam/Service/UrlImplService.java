package com.shivam.Service;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
	
	static int base64Arr[] = new int[255];
	
	static {
		int k = 0;
		for(int i='a';i<='z';i++) {
			base64Arr[i] = k++;
		}
		for(int i='A';i<='A';i++) {
			base64Arr[i] = k++;
		}
		for(int i='0';i<='9';i++) {
			base64Arr[i] = k++;
		}
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
		 //Get seed value
		UrlSeed seed  = urlSeedRepository.findById(1).get();
		
		//String generatedUrl = createShortUrlFromSeed(seed.getSeedValue(), originalUrl); //generate a short-url from seed and long url
		//String newSeed = generateNextSeed(seed.getSeedValue()); // Get next seed value
		
		String shortUrlToken = seed.getSeedValue();
		String newSeed = getNextBase64Token(seed.getSeedValue()); // Get next seed value
		seed.setSeedValue(newSeed); 
		urlSeedRepository.save(seed); //save new seed value
		
		return shortUrlToken;
	}
	
	public String isUrlReachable(String url) {
		try {
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		int responseCode = connection.getResponseCode();
		if (responseCode != 200) {
		   	return "Status NOT_OK for this URL";
		}
		}catch(MalformedURLException e) {
			return "BAD URL! Try adding http/https before";
		}catch(Exception e) {
			return "URL not found !";
		}
		
		return null;
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
	
	public String getNextBase64Token(String s) {
		
		String base64Values = "aAbB0cCdD1eEfFgG2hHiI3jJkK4lLmM5+nNoO6pPqQrR7sStTuU8vVwW9xXyYzZ/";
		char arr[] = s.toCharArray();
		int i=s.length()-1;
		
		for(;i>=0;i--) {
			char x = arr[i];
			if(x == base64Values.charAt(base64Values.length()-1)) {
				continue;
			}else {
				int j = 0;
				for(;j<base64Values.length();j++) {
					if(x == base64Values.charAt(j)) {
						break;
					}
				}
				arr[i] = base64Values.charAt(j+1);
				break;
			}
		}
		
		i++;
		
		for(;i<arr.length;i++) {
			arr[i] = base64Values.charAt(0);
		}
		
		return new String(arr);
	}
	
}
