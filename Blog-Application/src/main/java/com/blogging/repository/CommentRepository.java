package com.blogging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogging.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	
}
