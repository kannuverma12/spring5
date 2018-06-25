package com.s5.helpers;

import org.reactivestreams.Publisher;

import com.s5.model.Person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PersonRepository {

	public Mono<Void> save(Publisher<Person> personStream) {
		// TODO Auto-generated method stub
		return null;
	}

	public Flux<Person> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Mono<Person> findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Flux<Person> allPeople() {
		// TODO Auto-generated method stub
		return null;
	}

	public Publisher<Void> savePerson(Mono<Person> person) {
		// TODO Auto-generated method stub
		return null;
	}

	public Mono<Person> getPerson(int personId) {
		// TODO Auto-generated method stub
		return null;
	}

}
