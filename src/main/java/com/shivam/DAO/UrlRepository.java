package com.shivam.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivam.Entity.URL;

public interface UrlRepository extends JpaRepository<URL, Integer>{

}
