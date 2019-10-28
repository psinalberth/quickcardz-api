package com.github.psinalberth.quickcardz.security;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtTokenFilterConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
	
	final JwtTokenProvider jwtTokenProvider;
	
	public JwtTokenFilterConfigurer(final JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider; 
	}
	
	@Override
	public void configure(HttpSecurity builder) throws Exception {
		JwtTokenFilter filter = new JwtTokenFilter(jwtTokenProvider);
		builder.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}
}
