package com.github.psinalberth.quickcardz.security;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.github.psinalberth.quickcardz.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenProvider {
	
	private String secretKey = "secret";	
	private long expirationTimeInMillis = 3600000;
	
	private final UserService userService;
	
	public JwtTokenProvider(final UserService userService) {
		this.userService = userService;
	}
	
	@PostConstruct
	public void postConstruct() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String generateToken(String username, Collection<String> roles) {
		
		Date creationDate = new Date();
		Date expirationDate = new Date(creationDate.getTime() + expirationTimeInMillis);
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(creationDate)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, secretKey)
				.compact();
	}
	
	public String getUsername(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean isTokenValid(String token) {
		
		Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
		
		if (claims.getBody().getExpiration().before(new Date()))
			return false;
		
		return true;
	}
	
	public Authentication authentication(String token) {
		UserDetails userDetails = this.userService.loadUserByUsername(getUsername(token));
		return new UsernamePasswordAuthenticationToken(userDetails,  null, userDetails.getAuthorities());
	}

	public String resolveToken(HttpServletRequest request) {
		
		String bearerToken = request.getHeader("Authorization");
		
		if (bearerToken != null && bearerToken.startsWith("Bearer "))
			return bearerToken.replace("Bearer ", "");
		
		return null;
	}
}