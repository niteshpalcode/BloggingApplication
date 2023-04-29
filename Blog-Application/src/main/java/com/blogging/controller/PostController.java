package com.blogging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.dto.PostDto;
import com.blogging.exception.CategoryNotFoundException;
import com.blogging.exception.UserNotFoundException;
import com.blogging.service.PostService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/blog")
public class PostController {

	@Autowired
	private PostService postService;
	
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createNewPost(@RequestBody PostDto postDto,
			@PathVariable("userId") Long userId,
			@PathVariable("categoryId") Long categoryId
			)throws UserNotFoundException ,CategoryNotFoundException
	{
		
		return new ResponseEntity<PostDto>(postService.createNewPost(postDto, userId, categoryId),HttpStatus.CREATED);
	}
}
