package com.blogging.model;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Role {

	@Id
	private Long id;
	private String name;
	
	
	@ManyToMany(mappedBy = "roles")
    private List < User > users;
}
