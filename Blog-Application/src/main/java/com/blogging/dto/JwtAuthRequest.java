package com.blogging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class JwtAuthRequest {

	private String username;
	private String password;
}
