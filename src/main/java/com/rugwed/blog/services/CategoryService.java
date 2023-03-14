package com.rugwed.blog.services;

import java.util.List;

import com.rugwed.blog.payloads.CategoryDTO;

public interface CategoryService {

	 CategoryDTO createCategory(CategoryDTO categoryDTO);
	 
	 CategoryDTO updateCategory(CategoryDTO categoryDTO,Integer CategoryId);
	 
	 void deleteCategory(Integer CategoryId);
	 
	 CategoryDTO getCategory(Integer CategoryId);
	 
	 List<CategoryDTO> getAllCategory();
	 
}
