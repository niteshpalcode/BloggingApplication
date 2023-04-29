package com.blogging.controller;

import java.util.List;

import javax.validation.Valid;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.dto.ApiResponse;
import com.blogging.dto.CategoryDto;
import com.blogging.exception.CategoryNotFoundException;
import com.blogging.service.CategoryService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	
	@Autowired
	private CategoryService categoryService;
	
	
	@PostMapping("/add")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		
		return new ResponseEntity<CategoryDto>(categoryService.createCategory(categoryDto),HttpStatus.CREATED);
	}
	
@PutMapping("/update/{categoryId}")
public ResponseEntity<CategoryDto> updateCategory
(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") Long id)
	throws CategoryNotFoundException{
		
		return new ResponseEntity<CategoryDto>(categoryService.updateCategory(categoryDto, id),HttpStatus.ACCEPTED);
	
	}


@GetMapping("/{categoryId}")
public ResponseEntity<CategoryDto> getCategoryById
( @PathVariable("categoryId") Long id) throws CategoryNotFoundException{
		
		return new ResponseEntity<CategoryDto>(categoryService.getCategoryById(id),HttpStatus.OK);
	
	}


@DeleteMapping("/{categoryId}")
public ResponseEntity<?> deleteCategoryById
( @PathVariable("categoryId") Long id) throws CategoryNotFoundException{
	categoryService.deleteCategory(id);
	return new ResponseEntity<Object>(new ApiResponse("Category Deleted Successfully", true),HttpStatus.OK);
	
	
	}


@GetMapping("/getAll")
public ResponseEntity<List<CategoryDto>> getAllCategory() throws CategoryNotFoundException{
		
		return ResponseEntity.ok(categoryService.getAllCategory());
	
	}
}
