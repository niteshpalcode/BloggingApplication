package com.blogging.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.dto.ApiResponse;
import com.blogging.dto.UserDto;
import com.blogging.exception.UserNotFoundException;
import com.blogging.repository.RoleRepository;
import com.blogging.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	
	@Autowired
	private UserService userService;
	
	
	
	
	
	@PostMapping("/add")
	public ResponseEntity<UserDto> createNewUserHandler(@Valid @RequestBody UserDto userDto) {
		
		return new ResponseEntity<UserDto>(userService.createNewUser(userDto),HttpStatus.CREATED);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerNewUserHandler(@Valid @RequestBody UserDto userDto) {
		
		return new ResponseEntity<UserDto>(userService.registerNewUser(userDto),HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Long id) 
			throws UserNotFoundException{
		
		return new ResponseEntity<UserDto>(userService.updateUser(userDto, id),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long id) throws UserNotFoundException{
		
		return new ResponseEntity<UserDto>(userService.getUserbyId(id),HttpStatus.OK);
		
		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUserById(@PathVariable("userId") Long id) throws UserNotFoundException{
		userService.deleteUserById(id);
//		return new ResponseEntity(Map.of("message", "User Deleted Successfully"),HttpStatus.OK);
		return new ResponseEntity<Object>(new ApiResponse("User Deleted Successfully", true),HttpStatus.OK);
	}
	
	
	
	@GetMapping("/getAll")
	public ResponseEntity<List<UserDto>> getAllUser() throws UserNotFoundException{
		
		return  ResponseEntity.ok(userService.getAllUser());
		
		
	}
}
