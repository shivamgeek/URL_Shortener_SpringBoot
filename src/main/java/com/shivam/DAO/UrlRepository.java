package com.shivam.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shivam.Entity.Url;

public interface UrlRepository extends JpaRepository<Url, Integer>{

}
