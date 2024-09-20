package com.example.lms.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.lms.serviceimpl.UserInfoUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	
	 @Autowired
   private JwtService jwtService;

   @Autowired
   private UserInfoUserDetailsService userDetailsService;

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
           throws ServletException, IOException {
       // Step 1: Extract Token and Username from Request Header
       String token = null;
       String username = null;
       String header = request.getHeader("Authorization");
       if (null != header && header.startsWith("Bearer ")) {
           token = header.substring(7);
           username = jwtService.extractUsername(token);
       }

       // Step 2: Check if Username is not null and Authentication has not been set
       if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
           // Step 3: Load UserDetails from Database
           UserDetails userDetails = userDetailsService.loadUserByUsername(username);

           // Step 4: Validate Token against UserDetails
           if (jwtService.validateToken(token, userDetails)) {
               // Step 5: Set up Spring Security Authentication
               UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
                       userDetails, null, userDetails.getAuthorities());
               userToken.setDetails(new WebAuthenticationDetails(request));
               SecurityContextHolder.getContext().setAuthentication(userToken);
           }
       }

       // Step 6: Continue with the Filter Chain
       filterChain.doFilter(request, response);
   }
}
