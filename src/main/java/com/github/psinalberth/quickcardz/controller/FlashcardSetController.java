package com.github.psinalberth.quickcardz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.psinalberth.quickcardz.model.entity.FlashcardSet;
import com.github.psinalberth.quickcardz.model.vo.FlashcardSetVO;
import com.github.psinalberth.quickcardz.service.FlashcardSetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/sets")
@Api(tags = "Flashcard Sets Endpoint")
public class FlashcardSetController {
	
	private final FlashcardSetService service;	
	
	@Autowired
	public FlashcardSetController(FlashcardSetService service) {
		this.service = service;
	}
	
	@GetMapping
	@ApiOperation("Find all sets.")
	public List<FlashcardSet> findAll() {
		return service.findAll();
	}
	
	@GetMapping(path = "/{id}")
	@ApiOperation("Find a single flashcard set.")
	public FlashcardSetVO findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@PostMapping
	@ApiOperation(value="Create a new flashcard set.")
	public FlashcardSet save(@RequestBody FlashcardSetVO set) {
		return service.save(set);
	}
	
	@PutMapping
	@ApiOperation(value="Update an existing flashcard set.")
	public FlashcardSet update(@RequestBody FlashcardSetVO set) {
		return service.update(set);
	}
	
	@DeleteMapping(path = "/{id}")
	@ApiOperation(value = "Delete a flashcard set for given id.")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}