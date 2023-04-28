package com.blogging.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging.dto.CategoryDto;
import com.blogging.exception.CategoryNotFoundException;
import com.blogging.model.Category;
import com.blogging.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
	
	Category category =	modelMapper.map(categoryDto,Category.class );
	Category addCategory =	 categoryRepository.save(category );
	return modelMapper.map(addCategory, CategoryDto.class);
	
	
	
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Long categoryId) throws CategoryNotFoundException {
	
	Optional<Category> isPresent = categoryRepository.findById(categoryId);
		
	if(isPresent.isEmpty()) {
		throw new CategoryNotFoundException("Category Not Found With this CategoryId :-" + categoryId);
	}
	Category category = isPresent.get();
	
	category.setCategoryDescription(categoryDto.getCategoryDescription());
	category.setCategoryTitle(categoryDto.getCategoryTitle());
	
	Category UpdatedCategory = categoryRepository.save(category);
	
	return modelMapper.map(UpdatedCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Long categoryId) throws CategoryNotFoundException {
		Optional<Category> isPresent = categoryRepository.findById(categoryId);
		if(isPresent.isEmpty()) {
			throw new CategoryNotFoundException("Category Not Found With this CategoryId :-" + categoryId);
		}
		Category category = isPresent.get();
		categoryRepository.delete(category);
		
	}

	@Override
	public CategoryDto getCategoryById(Long categoryId) throws CategoryNotFoundException {
		Optional<Category> isPresent = categoryRepository.findById(categoryId);
		if(isPresent.isEmpty()) {
			throw new CategoryNotFoundException("Category Not Found With this CategoryId :-" + categoryId);
		}
		Category category = isPresent.get();
		
		return modelMapper.map(category, CategoryDto.class);
		
		
	}

	@Override
	public List<CategoryDto> getAllCategory() {
	
	List<Category> categories= 	categoryRepository.findAll();
		
    List<CategoryDto> categoryDtos =	categories.stream()
    		.map(category -> modelMapper.map(category, CategoryDto.class))
    		.collect(Collectors.toList());
   return categoryDtos;

}
	
	
	
	
	
}
