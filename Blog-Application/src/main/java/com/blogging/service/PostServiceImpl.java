package com.blogging.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogging.dto.PostDto;
import com.blogging.exception.CategoryNotFoundException;
import com.blogging.exception.PostNotFoundException;
import com.blogging.exception.UserNotFoundException;
import com.blogging.model.Category;
import com.blogging.model.Post;
import com.blogging.model.PostResponse;
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
		
		Post post = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException("post not found with this postId : " +postId));
		postRepository.delete(post);
	}

	@Override
	public PostDto findPostById(Long postId) throws PostNotFoundException {
		Post post = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException("post not found with this postId : " +postId));

		return modelMapper.map(post, PostDto.class);
		
	}

	@Override
	public List<PostDto> findAllPost() {
		List<Post> post = postRepository.findAll();
		
	List<PostDto> postDtos = post.stream()
			.map(posts -> modelMapper.map(posts, PostDto.class))
			.collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public PostResponse getPostByCategory(Long categoryId ,Integer pageNumber,Integer pageSize) throws CategoryNotFoundException{
		
		 Category category = categoryRepository.findById(categoryId)
				  .orElseThrow(()-> new CategoryNotFoundException("category with this id is not found : " + categoryId));
	
//		 List<Post> posts = postRepository.findByCategory(category);
		
		 Pageable p = PageRequest.of(pageNumber, pageSize);
			
	      Page<Post> pagePost = postRepository.findByCategory(category, p);
//			Page<Post> pagePost = postRepository.findAll(p);
			List<Post> post = pagePost.getContent();
			
			List<PostDto> postDtos = post.stream()
					.map(posts -> modelMapper.map(posts, PostDto.class))
					.collect(Collectors.toList());
				
			
			PostResponse postResponse = new PostResponse();
			postResponse.setContent(postDtos);
			postResponse.setPageNumber(pagePost.getNumber());
			postResponse.setPageSize(pagePost.getSize());
			postResponse.setTotalElement(pagePost.getTotalElements());
			postResponse.setTotalpages(pagePost.getTotalPages());
			postResponse.setLastPage(pagePost.isLast());
			
			return postResponse ;
		
		
		
	}

	@Override
	public PostResponse getPostByUser(Long userId,Integer pageNumber,Integer pageSize)  throws UserNotFoundException{
		User user = userRepository.findById(userId).orElseThrow(()-> 
	      new UserNotFoundException("user with this id is not found : "+ userId));
//		List<Post> po =	postRepository.findByUser(user);
		
      Pageable p = PageRequest.of(pageNumber, pageSize);
		
      Page<Post> pagePost = postRepository.findByUser(user, p);
//		Page<Post> pagePost = postRepository.findAll(p);
		List<Post> post = pagePost.getContent();
		
		List<PostDto> postDtos = post.stream()
				.map(posts -> modelMapper.map(posts, PostDto.class))
				.collect(Collectors.toList());
			
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalpages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse ;
		
		
		
		
		
	}


	@Override
	public PostResponse findAllPost(Integer pageNumber, Integer pageSize, String sortBy,String sortDir ) {
		
		Sort sort =null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort =	Sort.by(sortBy).ascending();
		}else {
			sort =	Sort.by(sortBy).descending();
		}
		
		
	Pageable p = PageRequest.of(pageNumber,  pageSize, sort);
		
		Page<Post> pagePost = postRepository.findAll(p);
		List<Post> post = pagePost.getContent();
		
		List<PostDto> postDtos = post.stream()
				.map(posts -> modelMapper.map(posts, PostDto.class))
				.collect(Collectors.toList());
			
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalpages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse ;
		
			
	}
	
	
	
	@Override
	public List<PostDto> searchPosts(String keyword) {
		
		
		List<Post> post = postRepository.findByTitleContaining(keyword);
		
		List<PostDto> postDtos  = post.stream()
				.map(posts->modelMapper.map(posts, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPostsByContents(String keyword) {
	
		
   List<Post> post = postRepository.findByContent("%"+ keyword+ "%");
		
		List<PostDto> postDtos  = post.stream()
				.map(posts->modelMapper.map(posts, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	
	}
	
}
