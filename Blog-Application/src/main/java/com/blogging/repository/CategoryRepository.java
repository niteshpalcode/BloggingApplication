package com.blogging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogging.model.Category;

@Repository
public interface CategoryRepository  extends JpaRepository<Category, Long>  {

}
