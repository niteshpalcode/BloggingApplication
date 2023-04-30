package com.blogging.service;

import org.springframework.stereotype.Service;

import com.blogging.dto.CommentDto;
import com.blogging.exception.CommentNotFoundException;
import com.blogging.exception.PostNotFoundException;
import com.blogging.exception.UserNotFoundException;

@Service
public interface CommentService {

	
	    public   CommentDto  createComment(CommentDto commentDto ,Long postId,Long userId) throws PostNotFoundException,UserNotFoundException;
         public void deleteComment(Long commentId) throws CommentNotFoundException;
         
          
}