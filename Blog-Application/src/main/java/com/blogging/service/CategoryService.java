package com.blogging.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.blogging.dto.CategoryDto;
import com.blogging.exception.CategoryNotFoundException;


@Service
public interface CategoryService {

	
	public CategoryDto createCategory(CategoryDto categoryDto);
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) throws CategoryNotFoundException;
	public void deleteCategory(Long categoryId)throws CategoryNotFoundException;
	public CategoryDto getCategoryById(Long categoryId) throws CategoryNotFoundException;
	public List<CategoryDto> getAllCategory();
	
}
