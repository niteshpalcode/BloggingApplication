package com.blogging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.dto.JwtAuthRequest;
import com.blogging.dto.JwtAuthResponse;
import com.blogging.security.JwtTokenHelper;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/blog/auth")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request){
		
		this.authenticate(request.getUsername(),request.getPassword());
		
	UserDetails userDetails =	this.userDetailsService.loadUserByUsername(request.getUsername());
		
	String token =	this.jwtTokenHelper.generateToken(userDetails);
		
	JwtAuthResponse response = new JwtAuthResponse();
	response.setToken(token);
	return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}


	private void authenticate(String username, String password) {
	
		
	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		this.authenticationManager.authenticate(authenticationToken);
	}
	
	
}
