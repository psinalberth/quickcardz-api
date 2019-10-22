package com.github.psinalberth.quickcardz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.psinalberth.quickcardz.exception.ResourceNotFoundException;
import com.github.psinalberth.quickcardz.model.entity.FlashcardSet;
import com.github.psinalberth.quickcardz.model.mapper.FlashcardSetMapper;
import com.github.psinalberth.quickcardz.model.mapper.MyContext;
import com.github.psinalberth.quickcardz.model.vo.FlashcardSetVO;
import com.github.psinalberth.quickcardz.repository.FlashcardSetRepository;

@Service
public class FlashcardSetService {
	
	private final FlashcardSetRepository repository;
	private final FlashcardSetMapper mapper;
	private final MyContext ctx;
	
	@Autowired
	public FlashcardSetService(FlashcardSetRepository repository, FlashcardSetMapper mapper, MyContext ctx) {
		this.repository = repository;
		this.mapper = mapper;
		this.ctx = ctx;
	}
	
	public FlashcardSetVO findById(Long id) {
		
		FlashcardSet set = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for given id."));		
		return mapper.flashcardSetToFlashcardSetVO(set);
	}
	
	public FlashcardSet save(FlashcardSetVO setVO) {
		
		FlashcardSet newSet = mapper.flashcardSetVOToFlashcardSet(setVO, setVO);
		return repository.save(newSet);
	}
	
	public FlashcardSet update(FlashcardSetVO setVO) {
		
//		FlashcardSet setToUpdate = mapper.fromVOtoEntity(setVO);
//		FlashcardSet savedSet = this.findById(setToUpdate.getId());
//		
//		savedSet.setTitle(setToUpdate.getTitle());
//		savedSet.setDescription(setToUpdate.getDescription());
//		savedSet.setFlashcards(setToUpdate.getFlashcards());
//		
//		return repository.save(savedSet);
		return null;
	}
	
	public List<FlashcardSet> findAll() {
		return repository.findAll();
	}

	public void delete(Long id) {
//		FlashcardSet setToDelete = this.findById(id);
//		repository.delete(setToDelete);
	}
}