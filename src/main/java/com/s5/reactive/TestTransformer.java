package com.s5.reactive;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;

import reactor.core.publisher.FluxSink;
import reactor.core.publisher.UnicastProcessor;
import reactor.core.publisher.FluxSink.OverflowStrategy;;

public class TestTransformer {

	public static void main(String... args) {
		SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
		MyTransformer<String, Integer> transformProcessor = new MyTransformer<>(Integer::parseInt);
		
		
		TestSubscriber<Integer> subscriber = new TestSubscriber<>();
	    List<String> items = List.of("1", "2", "3");
	    
	    List<Integer> expectedResult = List.of(1, 2, 3);
	    
	    publisher.subscribe(transformProcessor);
	    transformProcessor.subscribe(subscriber);
	    items.forEach(publisher::submit);
	    publisher.close();
	    
	}
}
