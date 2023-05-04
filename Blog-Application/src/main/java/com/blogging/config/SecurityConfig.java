package com.blogging.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.blogging.security.CustomUserDetailService;
import com.blogging.security.JwtAunthicationFilter;
import com.blogging.security.JwtAuthenticationEntryPoint;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAunthicationFilter jwtAunthicationFilter;
	
	public static  final String[] PUBLIC_URLS = {
			"/blog/auth/login" ,
			"/v3/api-docs",
			"/v2/api-docs",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**"
			
			
			
	};
  
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf()
		.disable()
		.authorizeRequests()
		.antMatchers(PUBLIC_URLS).permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtAunthicationFilter, UsernamePasswordAuthenticationFilter.class);
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

}
