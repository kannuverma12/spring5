package com.s5.reactive;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

public class CombinePublishers {
	
	
	
	public static void main(String... strings) {
		
		try {
			Mono<String> mono1 = Mono.just(" karan ");
			Mono<String> mono2 = Mono.just(" | verma ");
			Mono<String> mono3 = Mono.just(" | kv ");
			
			Flux<String> flux1 = Flux.just(" {1} ","{2} ","{3} ","{4} " );
			Flux<String> flux2 = Flux.just(" |A|"," |B| "," |C| ");
			
			// FLux emits item each 500ms
			Flux<String> intervalFlux1 = Flux.interval(Duration.ofMillis(1000))
											.zipWith(flux1, (i, string) -> string);
			
			// FLux emits item each 700ms		
			Flux<String> intervalFlux2 = Flux
									.interval(Duration.ofMillis(1000))
									.zipWith(flux2, (i, string) -> string);
			
			
			
			System.out.println("**************Flux Concat***************");
			Flux.concat(mono1, mono2, mono3).subscribe(System.out::print);
			System.out.println();
			Flux.concat(flux2, flux1).subscribe(System.out::print);
			System.out.println();
			Flux.concat(intervalFlux2, flux1).subscribe(System.out::print);
			
			//Thread.sleep(5000);
			
			System.out.println();
			Flux.concat(intervalFlux2, intervalFlux1).subscribe(System.out::print);
			//Thread.sleep(10000);
			System.out.println("----------------------------------------");
			
			
			System.out.println("**************Flux Concat with***************");
			mono1.concatWith(mono2).concatWith(mono3).subscribe(System.out::print);
			System.out.println();
			flux1.concatWith(flux2).subscribe(System.out::print);
			
			System.out.println();
			intervalFlux1.concatWith(flux2).subscribe(System.out::print);
			//Thread.sleep(5000);
			
			System.out.println();
			intervalFlux1.concatWith(intervalFlux2).subscribe(System.out::print);
			//Thread.sleep(10000);
			System.out.println();
			
			System.out.println("----------------------------------------");
			
			
			System.out.println("**************Flux zip***************");
			
			Flux.zip(flux1, flux2,
					(itemflux1, itemflux2) -> "[ "+itemflux1 + ":"+ itemflux2 + " ] " ).log()
			.subscribe(System.out::print);
			System.out.println();
			
			flux1.zipWith(flux2,
					(itemflux1, itemflux2)-> "- [ "+itemflux1 + ":"+ itemflux2 + " ]-")
			.subscribe(System.out::print);
			System.out.println();
			
			System.out.println("**************Synchronous sink***************");
			Flux.generate(() -> 1,
					(state, sink) -> {
						sink.next("3 x " + state + " = " + 3*state);
						if (state == 10) sink.complete(); 
					      return state + 1; 
					}).log().subscribe(System.out::print);
			
			
			System.out.println();
			
			System.out.println("**************Synchronous sink - Mutable***************");
			Flux.generate(
				    AtomicLong::new, 
				    (state, sink) -> {
				      long i = state.getAndIncrement(); 
				      sink.next("3 x " + i + " = " + 3*i);
				      if (i == 10) sink.complete();
				      return state; 
				    }).log().subscribe(System.out::println);
			
			System.out.println();
			
			System.out.println("**************Synchronous sink - with consumer***************");
			Flux.generate(	
				    AtomicLong::new,
				      (state, sink) -> { 
				      long i = state.getAndIncrement(); 
				      sink.next("3 x " + i + " = " + 3*i);
				      if (i == 10) sink.complete();
				      return state; 
				    }, (state) -> System.out.println("state: " + state)).log().subscribe(System.out::println); 
				
			
			
			ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
			    while(true) {
			        fluxSink.next(System.currentTimeMillis());
			    }
			})
			  .sample(Duration.ofSeconds(2))
			  .publish();
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
