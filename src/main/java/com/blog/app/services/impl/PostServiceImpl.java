package com.blog.app.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.app.entities.Category;
import com.blog.app.entities.Post;
import com.blog.app.entities.User;
import com.blog.app.exception.ResourceNotFoundException;
import com.blog.app.payload.PostDto;
import com.blog.app.payload.PostResponse;
import com.blog.app.repository.CategoryRepo;
import com.blog.app.repository.PostRepo;
import com.blog.app.repository.UserRepo;
import com.blog.app.services.PostService;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		Post post=	this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		Post newPost= this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
	    post.setContent(postDto.getContent());
	    post.setTitle(postDto.getTitle());
	    post.setImageName(postDto.getImageName());
		Post updatePost = this.postRepo.save(post);
	    return this.modelMapper.map(updatePost, PostDto.class);
		
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "Post Id", postId));
        this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy) {
		
		
		Pageable p=PageRequest.of(pageNumber, pageSize, org.springframework.data.domain.Sort.by(sortBy).descending());
		
		 Page<Post> pagePost = this.postRepo.findAll(p);
		 List<Post> posts = pagePost.getContent();
		 
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id", postId));
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		
		return postDto;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId ));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.searchByTitle("%"+keyword+"%");
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map( post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

}
