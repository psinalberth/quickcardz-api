package com.github.psinalberth.quickcardz.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "flashcard")
public class Flashcard extends AbstractEntity {
	
	private static final long serialVersionUID = 3044140886212389472L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "flashcard_id")
	private Long id;
	
	@Column(nullable = false)
	private String term;
	
	@Column(nullable = false)
	private String definition;
	
	@Column(nullable = true)
	private String imageUri;
	
	@Column(nullable = true)
	private String audioUri;
	
	@ManyToOne
	@JoinColumn(name = "flashcardset_id")
	private FlashcardSet flashcardSet;
}