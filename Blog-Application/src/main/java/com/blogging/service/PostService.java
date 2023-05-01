package com.blogging.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogging.dto.PostDto;
import com.blogging.exception.CategoryNotFoundException;
import com.blogging.exception.PostNotFoundException;
import com.blogging.exception.UserNotFoundException;
import com.blogging.model.PostResponse;

@Service
public interface PostService {

	
	public PostDto createNewPost(PostDto postDto ,Long userId,Long categoryId) throws UserNotFoundException ,CategoryNotFoundException;
	
	public PostDto updatePost(PostDto postDto ,Long postId) throws PostNotFoundException;
	
	public void deletePost(Long postId)throws PostNotFoundException;
	
	public PostDto findPostById(Long postId)throws PostNotFoundException;
	
	public List<PostDto> findAllPost();
	
	public PostResponse findAllPost(Integer pageNumber,Integer pageSize ,String sortBy ,String sortDir);
	
	public PostResponse  getPostByCategory(Long categoryId,Integer pageNumber,Integer pageSize) throws CategoryNotFoundException;
	
	public PostResponse  getPostByUser(Long userId, Integer pageNumber,Integer pageSize) throws UserNotFoundException;
	
	public List<PostDto> searchPosts(String keyword);
	
	public List<PostDto> searchPostsByContents(String keyword);
	
}
