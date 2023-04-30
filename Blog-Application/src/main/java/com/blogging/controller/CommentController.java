package com.blogging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.dto.ApiResponse;
import com.blogging.dto.CommentDto;
import com.blogging.exception.CommentNotFoundException;
import com.blogging.exception.PostNotFoundException;
import com.blogging.exception.UserNotFoundException;
import com.blogging.service.CommentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/blog")
public class CommentController {

	
	@Autowired
	private CommentService commentService;
	
	
	
	@PostMapping("/{postId}/user/{userId}/comment")
	public ResponseEntity<CommentDto> createNewComment
	(@RequestBody CommentDto commentDto ,@PathVariable("postId") Long postId, @PathVariable("userId") Long userId) 
			throws PostNotFoundException,UserNotFoundException{
		
		return new ResponseEntity<CommentDto>(commentService.createComment(commentDto, postId,userId),HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId)
	throws CommentNotFoundException{
		commentService.deleteComment(commentId);
		
		return new ResponseEntity<>(new ApiResponse("post Deleted successfully",false), HttpStatus.OK);
	}
}
