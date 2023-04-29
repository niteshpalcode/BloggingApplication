package com.blogging.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogging.dto.PostDto;
import com.blogging.exception.CategoryNotFoundException;
import com.blogging.exception.PostNotFoundException;
import com.blogging.exception.UserNotFoundException;

@Service
public interface PostService {

	
	public PostDto createNewPost(PostDto postDto ,Long userId,Long categoryId) throws UserNotFoundException ,CategoryNotFoundException;
	
	public PostDto updatePost(PostDto postDto ,Long postId) throws PostNotFoundException;
	
	public void deletePost(Long postId)throws PostNotFoundException;
	
	public PostDto findPostById(Long postId)throws PostNotFoundException;
	
	public List<PostDto> findAllPost();
	
	public List<PostDto> getPostByCategory(Long categoryId);
	
	public List<PostDto> getPostByUser(Long userId);
	
	public List<PostDto> searchPosts(String keyword);
	
}
