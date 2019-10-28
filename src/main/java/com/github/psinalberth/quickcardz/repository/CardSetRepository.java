package com.github.psinalberth.quickcardz.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.github.psinalberth.quickcardz.model.entity.CardSet;

@Repository
public interface CardSetRepository extends PagingAndSortingRepository<CardSet, Long> {
	
}
