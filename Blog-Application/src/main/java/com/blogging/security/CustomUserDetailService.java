package com.blogging.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogging.model.User;
import com.blogging.repository.UserRepository;
@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	

       User user =	this.userRepository.findByEmail(username)
	.orElseThrow(()-> new UsernameNotFoundException("user is not found with this username" +username));
	
		
		return user;
		
		
	
		
		
		
	}

	
}
