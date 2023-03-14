package com.rugwed.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rugwed.blog.payloads.ApiResponce;
import com.rugwed.blog.payloads.CategoryDTO;
import com.rugwed.blog.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	// create

	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCat(@Valid @RequestBody CategoryDTO categoryDTO) {
		CategoryDTO createCat = this.categoryService.createCategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>(createCat, HttpStatus.CREATED);
	}

	// update

	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDTO> updateCat(@Valid @RequestBody CategoryDTO categoryDTO,
			@PathVariable Integer catId) {
		CategoryDTO updateCat = this.categoryService.updateCategory(categoryDTO, catId);
		return new ResponseEntity<CategoryDTO>(updateCat, HttpStatus.OK);
	}

	// delete

	@DeleteMapping("/{catId}")
	public ResponseEntity<ApiResponce> deleteCat(@PathVariable Integer catId) {
		this.categoryService.deleteCategory(catId);
		return new ResponseEntity<ApiResponce>(new ApiResponce("category is deleted successfully!", true),
				HttpStatus.OK);
	}

	// get

	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDTO> getCat(@PathVariable Integer catId) {
		CategoryDTO getCat = this.categoryService.getCategory(catId);
		return new ResponseEntity<CategoryDTO>(getCat, HttpStatus.OK);
	}

	// getAll

	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getAllCat() {
		List<CategoryDTO> getAllCat = this.categoryService.getAllCategory();
		return ResponseEntity.ok(getAllCat);
	}

}
