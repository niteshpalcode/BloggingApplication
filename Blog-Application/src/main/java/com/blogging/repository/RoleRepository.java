package com.blogging.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {

	
	Role findByName(String name);
}
