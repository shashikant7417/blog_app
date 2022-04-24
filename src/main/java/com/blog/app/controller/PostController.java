package com.blog.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.payload.ApiResponse;
import com.blog.app.payload.PostDto;
import com.blog.app.payload.PostResponse;
import com.blog.app.services.PostService;

@RestController
@RequestMapping("api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
    // Create User
   	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto ,
			@PathVariable Integer userId, @PathVariable Integer categoryId){
		
		PostDto createdPost= this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost,HttpStatus.CREATED);
		
	}
	
	// Get All post of Users with userID
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUserId(@PathVariable Integer userId){
		
		List<PostDto> posts = this.postService.getPostsByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
	
	// Get All post of Category with categoryId
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategoryId(@PathVariable Integer categoryId){
		
		List<PostDto> posts = this.postService.getPostsByCategory(categoryId);
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
	//Get all post
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value="pageSize", defaultValue = "15",required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue="postId",required = false)String sortBy){
		
		  PostResponse postResponse = this.postService.getAllPosts(pageNumber,pageSize,sortBy);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
		
	}
	
	//Get Post by Id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		
		PostDto postDto = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
		
	}
	
	//Delete Post
	
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId) {
		
		this.postService.deletePost(postId);
		return new ApiResponse("Post is Successfully deleted", true);
		
	}
	
	//Update Post
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId){
		
		PostDto updatedPost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
		
	}
	
	//Search Post
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable("keyword") String keyword){
		List<PostDto> result = this.postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>> (result,HttpStatus.OK);
	}
	
	


}
