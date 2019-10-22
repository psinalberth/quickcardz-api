package com.github.psinalberth.quickcardz.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "flashcardset")
public class CardSet extends AbstractEntity {
	
	private static final long serialVersionUID = -5608187932811655899L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flashcardset_id")
	private Long id;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = true)
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cardSet", fetch = FetchType.EAGER)
	private List<Flashcard> flashcards;
}