package com.blogging;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
		System.out.println("Welcome to Blog Application...!");
	
	}
	
	
  @Bean
    public ModelMapper modelMapper() {
	return new ModelMapper();
	
}
}
