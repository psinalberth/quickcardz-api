package com.github.psinalberth.quickcardz.model.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {
	
	private static final long serialVersionUID = 3723586456010018776L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name="username", unique = true)
	private String username;
	
	@Column(name = "password")
	private String password;	
	
	@Column(name = "account_non_expired")
	private boolean accountNonExpired = true;
	
	@Column(name = "account_non_locked")
	private boolean accountNonLocked = true;
	
	@Column(name = "credentials_non_expired")
	private boolean credentialsNonExpired = true;
	
	@Column(name = "enabled")
	private boolean enabled = true;
	
	@ManyToMany
	@JoinTable(name = "user_authority", 
		joinColumns = { @JoinColumn (name = "user_id") },
		inverseJoinColumns = { @JoinColumn (name = "authority_id")})
	private List<Authority> authorities;
	
	@Transient
	public List<String> getRoles() {
		
		if (this.getAuthorities() == null)
			return new ArrayList<>();
		
		return this.getAuthorities()
			.stream()
			.map(Authority::getAuthority)
			.collect(Collectors.toList());
	}
}