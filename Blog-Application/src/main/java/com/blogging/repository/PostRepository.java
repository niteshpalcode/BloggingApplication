package com.blogging.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blogging.model.Category;
import com.blogging.model.Post;
import com.blogging.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	
//	@Query("SELECT p FROM Post p WHERE p.user = :user")
//    Page<Post> findByUser(@Param("user") User user, Pageable pageable);

	Page<Post> findByUser(User user, Pageable p);
	Page<Post> findByCategory(Category category , Pageable p);
	
	List<Post> findByTitleContaining(String title);
	
	
	@Query("select p from Post p where p.content like :key")
	List<Post> findByContent(@Param("key") String content);
	
}
