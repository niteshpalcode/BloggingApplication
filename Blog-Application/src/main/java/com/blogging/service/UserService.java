package com.blogging.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogging.dto.UserDto;
import com.blogging.exception.UserNotFoundException;


@Service
public interface UserService {

	public UserDto createNewUser(UserDto userDto);
	public UserDto registerNewUser(UserDto userDto);
	public UserDto updateUser(UserDto userDto ,Long id)  throws UserNotFoundException;
	public UserDto getUserbyId(Long id) throws UserNotFoundException;
	public List<UserDto> getAllUser();
	public void deleteUserById(Long id) throws UserNotFoundException;
	
}
