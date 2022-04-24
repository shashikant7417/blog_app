package com.blog.app.services;

import java.util.List;

import com.blog.app.payload.CategoryDto;

public interface CategoryService {
	
	//create
	
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//update
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	//delete
	
	public void deleteCategory(Integer categoryId);
	
	//get
	
	public CategoryDto getCategory(Integer categoryId);
	
	//getall
	
	public List<CategoryDto> getCategories();

}
