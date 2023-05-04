package com.blogging;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.blogging.config.AppConstants;
import com.blogging.model.Role;
import com.blogging.repository.RoleRepository;
import com.blogging.repository.UserRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

public class BlogApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
		System.out.println("Welcome to Blog Application...!");
	
		
	}
	
	
  @Bean
    public ModelMapper modelMapper() {
	return new ModelMapper();
	
}


@Override
public void run(String... args) throws Exception {
	

	
	Role role = new Role();
	role.setId(AppConstants.ADMIN_USER);
	role.setName("ROLE_ADMIN");
	
	Role role1 = new Role();
	role1.setId(AppConstants.NORMAL_USER);
	role1.setName("ROLE_NORMAL");
	
	List<Role> roles = List.of(role,role1);
	
	List<Role> result =  this.roleRepository.saveAll(roles);
	result.forEach(r->System.out.println(r.getName()));
	
	
	
}

}
