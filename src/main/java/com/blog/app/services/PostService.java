package com.blog.app.services;

import java.util.List;

import com.blog.app.entities.Post;
import com.blog.app.payload.PostDto;
import com.blog.app.payload.PostResponse;

public interface PostService {
	
	//create post
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	//update Post
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//Delete post
	
	void deletePost(Integer postId);
	
	//Get All post
	
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy);
	
	//Get Post by Id
	
	PostDto getPostById(Integer postId);
	
	
	// Get post for Category
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	// Get All post for User
	
	List<PostDto> getPostsByUser(Integer userId);
	
	//Search Post
	
	List<PostDto> searchPosts(String keyword);
	
	

}
