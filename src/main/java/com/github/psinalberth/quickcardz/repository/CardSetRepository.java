package com.github.psinalberth.quickcardz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.psinalberth.quickcardz.model.entity.CardSet;

@Repository
public interface CardSetRepository extends JpaRepository<CardSet, Long> {
	
}
