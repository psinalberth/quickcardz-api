package com.github.psinalberth.quickcardz.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.psinalberth.quickcardz.exception.ResourceNotFoundException;
import com.github.psinalberth.quickcardz.model.entity.CardSet;
import com.github.psinalberth.quickcardz.model.mapper.CardSetMapper;
import com.github.psinalberth.quickcardz.model.vo.CardSetDto;
import com.github.psinalberth.quickcardz.repository.CardSetRepository;

@Service
public class CardSetService {
	
	private final CardSetRepository repository;
	private final CardSetMapper mapper;
	
	@Autowired
	public CardSetService(CardSetRepository repository, CardSetMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	public CardSetDto findById(Long id) {		
		CardSet set = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for given id."));		
		return mapper.fromEntityToDto(set);
	}
	
	private CardSet findEntityById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for given id."));
	}
	
	public CardSetDto save(@Valid CardSetDto dto) {		
		CardSet set = mapper.fromDtoToEntity(dto);
		set = repository.save(set);
		return mapper.fromEntityToDto(set);
	}
	
	public CardSetDto update(CardSetDto dto) {
		CardSet set = this.findEntityById(dto.getId());
		set = mapper.update(set, dto);
		set = repository.save(set);
		return mapper.fromEntityToDto(set);
	}
	
	public List<CardSetDto> findAll() {
		return mapper.map(repository.findAll());
	}

	public void delete(Long id) {
		CardSetDto dto = this.findById(id);
		repository.deleteById(dto.getId());
	}
}