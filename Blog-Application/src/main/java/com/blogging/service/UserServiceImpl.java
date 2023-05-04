package com.blogging.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogging.config.AppConstants;
import com.blogging.dto.UserDto;
import com.blogging.exception.UserNotFoundException;
import com.blogging.model.Role;
import com.blogging.model.User;
import com.blogging.repository.RoleRepository;
import com.blogging.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserDto createNewUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
        User savedUser = this.userRepository.save(user);
        return this.userToDto(savedUser);
		
		
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long id) throws UserNotFoundException {
		
	Optional<User>  isPresent = userRepository.findById(id);
	
	if( isPresent.isEmpty()) {
		throw new UserNotFoundException("user not found with this id : -" + id);
	}
		User user = isPresent.get();
		
		user.setName(userDto.getName());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		
	User updateUser =	userRepository.save(user);
     return	this.userToDto(updateUser);
		
		
	}

	@Override
	public UserDto getUserbyId(Long id) throws UserNotFoundException{
		Optional<User>  isPresent = userRepository.findById(id);
		
		if( isPresent.isEmpty()) {
			throw new UserNotFoundException("user not found with this id : -" + id);
		}
		User user = isPresent.get();
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {
		
	List<User> users =	userRepository.findAll();
	
List<UserDto> userDtos = 	users.stream().map(user -> this.userToDto(user))
	.collect(Collectors.toList());
	
	
		return userDtos;
	}

	@Override
	public void deleteUserById(Long id) throws UserNotFoundException {
		
   Optional<User>  isPresent = userRepository.findById(id);
		
   if( isPresent.isEmpty()) {
		throw new UserNotFoundException("user not found with this id : -" + id);
	}
		User user = isPresent.get();
		 userRepository.delete(user);
		 
		
	}
	
	
	private User dtoToUser(UserDto userDto) {
		
//		User user = new User();
//		user.setId(user.getId());
//		user.setName(userDto.getName());
//		user.setAbout(userDto.getAbout());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
		
		User user = this.modelMapper.map(userDto, User.class);
		
		return user;
		
		
		
	}
	
	
private UserDto userToDto(User user) {
		
//		UserDto userDto = new UserDto();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setAbout(user.getAbout());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
		
   UserDto userDto = modelMapper.map(user, UserDto.class);
		return userDto;
		
		
		
	}

@Override
public UserDto registerNewUser(UserDto userDto) {
	
	 User user = this.dtoToUser(userDto);
	user.setPassword(passwordEncoder.encode(user.getPassword()));
	
	Role role = roleRepository.findById(AppConstants.NORMAL_USER).get();
	
	user.getRoles().add(role);
     
     User savedUser = this.userRepository.save(user);
     return this.userToDto(savedUser);


}
}
