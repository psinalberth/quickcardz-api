package com.github.psinalberth.quickcardz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.psinalberth.quickcardz.exception.ResourceNotFoundException;
import com.github.psinalberth.quickcardz.model.entity.Flashcard;
import com.github.psinalberth.quickcardz.model.mapper.FlashcardMapper;
import com.github.psinalberth.quickcardz.model.vo.FlashcardDto;
import com.github.psinalberth.quickcardz.repository.FlashcardRepository;

@Service
public class FlashcardService {
	
	private final FlashcardRepository repository;
	private final FlashcardMapper mapper;
	
	@Autowired
	public FlashcardService(FlashcardRepository repository, FlashcardMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
	
	public FlashcardDto findById(Long id) {		
		Flashcard flashcard = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for given id."));		
		return mapper.fromEntityToDto(flashcard);
	}
	
	private Flashcard findEntityById(Long id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for given id."));
	}
	
	public FlashcardDto save(FlashcardDto dto) {		
		Flashcard flashcard = mapper.fromDtoToEntity(dto);
		flashcard = repository.save(flashcard);
		return mapper.fromEntityToDto(flashcard);
	}
	
	public FlashcardDto update(FlashcardDto dto) {
		Flashcard flashcard = this.findEntityById(dto.getId());
		flashcard = mapper.update(flashcard, dto);
		flashcard = repository.save(flashcard);
		return mapper.fromEntityToDto(flashcard);
	}
	
	public List<FlashcardDto> findAll() {
		return mapper.map(repository.findAll());
	}

	public void delete(Long id) {
		FlashcardDto dto = this.findById(id);
		repository.deleteById(dto.getId());
	}
}
