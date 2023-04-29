package com.blogging.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging.dto.PostDto;
import com.blogging.exception.CategoryNotFoundException;
import com.blogging.exception.PostNotFoundException;
import com.blogging.exception.UserNotFoundException;
import com.blogging.model.Category;
import com.blogging.model.Post;
import com.blogging.model.User;
import com.blogging.repository.CategoryRepository;
import com.blogging.repository.PostRepository;
import com.blogging.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDto createNewPost(PostDto postDto, Long userId, Long categoryId)
			throws UserNotFoundException, CategoryNotFoundException {

  User user = userRepository.findById(userId).orElseThrow(()-> 
      new UserNotFoundException("user with this id is not found : "+ userId));

  Category category = categoryRepository.findById(categoryId)
		  .orElseThrow(()-> new CategoryNotFoundException("category with this id is not found : " + categoryId));

  Post post = modelMapper.map(postDto, Post.class);
  post.setAddDate(new Date());
  post.setImageName("default.png");
  post.setCategory(category);
  post.setUser(user);
  
  
Post updatedPost =  postRepository.save(post);
return modelMapper.map(updatedPost, PostDto.class);
  
		
	
	}

	@Override
	public PostDto updatePost(PostDto postDto, Long postId) throws PostNotFoundException {
		
		Post post = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException("post not found with this postId : " +postId));
				
		 post.setTitle(postDto.getTitle());
		 post.setAddDate(postDto.getAddDate());
		 post.setContent(postDto.getContent());
		 post.setImageName(postDto.getImageName());
		Post updatePost = postRepository.save(post);
		
		return modelMapper.map(updatePost, PostDto.class);
		
		
	}

	@Override
	public void deletePost(Long postId) throws PostNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PostDto findPostById(Long postId) throws PostNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> findAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getPostByCategory(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getPostByUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
