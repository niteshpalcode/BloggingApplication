package com.blogging.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogging.model.Category;
import com.blogging.model.Post;
import com.blogging.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
	
}
