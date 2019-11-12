package com.github.psinalberth.quickcardz.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;

@Data
@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {
	
	private static final long serialVersionUID = 2434099971718334226L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "authority_id")
	private Long id;
	
	@Column(name = "authority", unique = true)
	private String authority;
}
