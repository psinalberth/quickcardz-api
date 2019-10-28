package com.github.psinalberth.quickcardz.security;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	
	private String secretKey = "secret";	
	private long expirationTimeInMillis = 3600000;
	
	@PostConstruct
	public void postConstruct() {
		secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	public String generateToken(String username, Collection<String> roles) {
		
		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + expirationTimeInMillis);
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(now)
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
		return new UsernamePasswordAuthenticationToken(getUsername(token), null, null);
	}

	public String resolveToken(HttpServletRequest request) {
		
		String bearerToken = request.getHeader("Authorization");
		
		if (bearerToken != null && bearerToken.startsWith("Bearer "))
			return bearerToken.replace("Bearer ", "");
		
		return null;
	}
}