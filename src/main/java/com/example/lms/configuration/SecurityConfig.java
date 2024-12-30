package com.example.lms.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.lms.jwt.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthFilter jwtFilter;
	
	@Autowired
	private AuthenticationProvider authProvider;
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		 http.csrf()
         .disable()
         .authorizeHttpRequests()
         .requestMatchers("/auth/**","/actuator/**")
         .permitAll()
         .and()
         .authorizeHttpRequests().requestMatchers("/book/add","/student/**","/book/**","/transaction/**","/author/**")
         .authenticated()
         .and()
         .sessionManagement()
         .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
         .and()
         .authenticationProvider(authProvider)
         .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

 return http.build();
    }


}
