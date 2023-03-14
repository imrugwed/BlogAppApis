package com.rugwed.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rugwed.blog.entity.Category;
import com.rugwed.blog.exceptions.ResourceNotFoundException;
import com.rugwed.blog.payloads.CategoryDTO;
import com.rugwed.blog.repository.CategoryRepo;
import com.rugwed.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public CategoryDTO createCategory(CategoryDTO CategoryDTO) {
		Category cat = this.modelMapper.map(CategoryDTO, Category.class);
		Category addCat = this.categoryRepo.save(cat);
		return this.modelMapper.map(addCat, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer CategoryId) {
		Category cat = this.categoryRepo.findById(CategoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", CategoryId));
		cat.setCategoryTitle(categoryDTO.getCategoryTitle());
		cat.setCategoryDescription(categoryDTO.getCategoryDescription());

		Category updateCat = this.categoryRepo.save(cat);
		return this.modelMapper.map(updateCat, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Integer CategoryId) {
		Category cat = this.categoryRepo.findById(CategoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", CategoryId));
		this.categoryRepo.delete(cat);
	}

	@Override
	public CategoryDTO getCategory(Integer CategoryId) {
		Category cat = this.categoryRepo.findById(CategoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", CategoryId));

		return this.modelMapper.map(cat, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<Category> Categories = this.categoryRepo.findAll();
		List<CategoryDTO> catDtos = Categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDTO.class))
				.collect(Collectors.toList());

		return catDtos;
	}

}
