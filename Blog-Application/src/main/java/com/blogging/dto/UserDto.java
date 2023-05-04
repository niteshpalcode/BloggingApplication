package com.blogging.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.blogging.model.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Long id;
    
    @NotEmpty
    @Size(message="Usernane must be of 6 character !!")
	private String name;
    
    @Email(message="Email address is not valid !!")
	private String email;
    
    @NotEmpty
    @Size(min =3,max =12, message="Password must be of 3 char and max 12 char")
	private String password;
    
    @NotEmpty
	private String about;
    
    
    private Set<RoleDto>roles = new HashSet<>();
}
