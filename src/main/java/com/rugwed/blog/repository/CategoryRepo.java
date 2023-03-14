package com.rugwed.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rugwed.blog.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{
	
	
}
