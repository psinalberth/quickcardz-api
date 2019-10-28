package com.github.psinalberth.quickcardz.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.psinalberth.quickcardz.model.vo.CardSetDto;
import com.github.psinalberth.quickcardz.service.CardSetService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/sets")
@Api(tags = "Flashcard Sets Endpoint")
public class CardSetController {
	
	private final CardSetService service;	
	
	public CardSetController(final CardSetService service) {
		this.service = service;
	}
	
	@GetMapping
	@ApiOperation("Find sets (using pagination).")
	public ResponseEntity<Page<CardSetDto>> findAllCards(
			@RequestParam(value = "page", defaultValue = "0" ) Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "20" ) Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "title" ) String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC" ) String direction) {
		return ResponseEntity.ok(service.findAll(page, linesPerPage, orderBy, direction));
	}
	
	@GetMapping(path = "/{id}")
	@ApiOperation("Find a single flashcard set.")
	public CardSetDto findById(@PathVariable("id") Long id) {
		return service.findById(id);
	}
	
	@PostMapping
	@ApiOperation(value="Create a new flashcard set.")
	public CardSetDto save(@Valid @RequestBody CardSetDto set) {
		return service.save(set);
	}
	
	@PutMapping
	@ApiOperation(value="Update an existing flashcard set.")
	public CardSetDto update(@Valid @RequestBody CardSetDto set) {
		return service.update(set);
	}
	
	@DeleteMapping(path = "/{id}")
	@ApiOperation(value = "Delete a flashcard set for given id.")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}