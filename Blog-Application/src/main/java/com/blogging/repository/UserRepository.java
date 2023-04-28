package com.blogging.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogging.model.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
