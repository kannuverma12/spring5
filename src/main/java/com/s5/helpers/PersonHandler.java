package com.s5.helpers;


import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.s5.model.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonHandler {

	private final PersonRepository repository;

	public PersonHandler(PersonRepository repository) {
		this.repository = repository;
	}

	public Mono<ServerResponse> listPeople(ServerRequest request) { //1
		Flux<Person> people = repository.allPeople();
		return ServerResponse.ok().contentType(APPLICATION_JSON).body(people, Person.class);
	}

	public Mono<ServerResponse> createPerson(ServerRequest request) { //2
		Mono<Person> person = request.bodyToMono(Person.class);
		return ServerResponse.ok().build(repository.savePerson(person));
	}

	public Mono<ServerResponse> getPerson(ServerRequest request) { //3
		int personId = Integer.valueOf(request.pathVariable("id"));
		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
		Mono<Person> personMono = this.repository.getPerson(personId);
		
		//personMono.then(person -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(person)));
		
		//personMono.then(person -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(person)))
		
//		return personMono.then(person -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(person)))
//				.otherwiseIfEmpty(notFound);
		//return personMono.thenMany(person -> ServerResponse.ok().contentType(APPLICATION_JSON).body(fromObject(person)));
		return null;
	}
	
	
}