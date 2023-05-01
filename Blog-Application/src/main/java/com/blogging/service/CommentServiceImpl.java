package com.blogging.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging.dto.CommentDto;
import com.blogging.exception.CommentNotFoundException;
import com.blogging.exception.PostNotFoundException;
import com.blogging.exception.UserNotFoundException;
import com.blogging.model.Comment;
import com.blogging.model.Post;
import com.blogging.model.PostResponse;
import com.blogging.model.User;
import com.blogging.repository.CommentRepository;
import com.blogging.repository.PostRepository;
import com.blogging.repository.UserRepository;

@Service
public class CommentServiceImpl  implements CommentService{

	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public CommentDto createComment(CommentDto commentDto, Long postId,Long userId) throws PostNotFoundException,UserNotFoundException {
		
		Post post = postRepository.findById(postId).
				orElseThrow(()-> new PostNotFoundException("post with this postid is not found" +postId));
		
		User user = userRepository.findById(userId).
				orElseThrow(()-> new UserNotFoundException("user with this userid is not found" +userId));
		
          Comment comment =modelMapper.map(commentDto,Comment.class );
           comment.setPost(post);
           comment.setUser(user);
           Comment saveComment = commentRepository.save(comment);
            return modelMapper.map(saveComment, CommentDto.class);
	  
		
	}

	@Override
	public void deleteComment(Long commentId) throws CommentNotFoundException{
		
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(()-> new CommentNotFoundException("comment  is not found with this comment id " + commentId));
		
		commentRepository.delete(comment);
		
	}
	
	
	
};
