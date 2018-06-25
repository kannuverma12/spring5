package com.s5.controller;

import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.s5.helpers.PersonRepository;
import com.s5.model.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController
public class PersonController {

	private final PersonRepository repository;

	public PersonController(PersonRepository repository) {
		this.repository = repository;
	}

	@PostMapping("/person")
	Mono<Void> create(@RequestBody Publisher<Person> personStream) {
		return this.repository.save(personStream).then();
	}

	@GetMapping("/person")
	Flux<Person> list() {
		return this.repository.findAll();
	}

	@GetMapping("/person/{id}")
	Mono<Person> findById(@PathVariable String id) {
		return this.repository.findOne(id);
	}
}