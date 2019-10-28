package com.github.psinalberth.quickcardz.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenFilter extends OncePerRequestFilter {
	
	final JwtTokenProvider jwtTokenProvider;
	
	public JwtTokenFilter(final JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		String token = jwtTokenProvider.resolveToken(request);
		
		if (token != null && jwtTokenProvider.isTokenValid(token)) {			
			Authentication authentication = jwtTokenProvider.authentication(token);
			
			if (authentication != null) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} else {
			 SecurityContextHolder.clearContext();
		}
		
		filterChain.doFilter(request, response);
	}
}