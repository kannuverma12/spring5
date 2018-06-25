package com.s5.reactive;

import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Function;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.FluxSink.OverflowStrategy;
import reactor.core.publisher.UnicastProcessor;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;
import reactor.util.Loggers;
import reactor.util.function.Tuple2;

public class FluxAndMono {
	
	
			
	public static void main(String[] args) {
		Loggers.useConsoleLoggers();
		//startFlux();
		//fluxHotStream();
		//combineSteams();
		//connectableFlux();
		
		//sbackPressure();
		
		
		
		
		//mapData();
		
		//combineSteams();
		
		//concurrency();
		
		//unicastProcessor();
		
		//transformStream();
		
		//composeStream();
		
		//coldStream();
		
		//hotStream();
		
		//broadcastStream();
		
		//broadcastStreamWithAutoConnect();
		
		//groupByStreams();
		
		Double c = 120.00;
		DecimalFormat df2 = new DecimalFormat("0.00");
		System.out.println("c = "+df2.format(c));
		
		
		
		//parallelFlux();
		
		//elasticVsNewElastic();
		
		//deferFlux();
		
		combineFluxToTuple();
		
		
	}
	
	private static void combineFluxToTuple() {
		List<Integer> elems = new ArrayList<>();
	    Flux<Integer> f1 = Flux.just(10,20,30,40);
	    Flux<Integer> f2 = Flux.just(100,200,300,400);
	    
//	    Flux<Tuple2> zipped = f1.zipWith(f2,
//	    									(one, two) ->  Tuple.<Selector, Object>of(Selectors.U(key), key))
//	    										.subscribe();
	    										
	    
	        
		
	}

	private static void deferFlux() {
		Flux f1 = Flux.defer(() -> Flux.just("d1", "d2")).log();
		Flux f2 = Flux.just("d1", "d2");
		f1.subscribe(s -> System.out.print("Defer Subs 1 "+s));
		f1.subscribe(s -> System.out.print("Defer Subs 2 "+s));
		f2.subscribe(s -> System.out.println("Subs 1"));
		f2.subscribe(s -> System.out.println("Subs 2"));
		
	}

	private static void elasticVsNewElastic() {
		System.out.println("------ elastic ---------  ");
		Flux.range(1, 10)
		  .map(i -> i / 2)
		  .publishOn(Schedulers.elastic()).log()
		  .blockLast();
		
		System.out.println("------ new elastic ---------  ");
		Flux.range(1, 10)
		  .map(i -> i / 2).log()
		  .publishOn(Schedulers.newElastic("my")).log()
		  .blockLast();
		  
		
	}

	private static void parallelFlux() {
		System.out.println("*********Calling parallelFlux************");
		Flux.range(1, 10)
	    		.parallel(2)
	    		.runOn(Schedulers.parallel())
	    		//.subscribe(i -> System.out.println(Thread.currentThread().getName() + " -> " + i));
	    		.subscribe(System.out::println);
		
//		try {
//			Thread.sleep(5000);
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		System.out.println("-------------------------------------");
		
	}

	private static void groupByStreams() {
		System.out.println("*********Calling groupByStreams************");
		StepVerifier.create(
		        Flux.just(1, 3, 5, 2, 4, 6, 11, 12, 13)
		                .groupBy(i -> i % 2 == 0 ? "even" : "odd")
		                .concatMap(g -> g.defaultIfEmpty(-1) //if empty groups, show them
		                                .map(String::valueOf) //map to string
		                                .startWith(g.key())) //start with the group's key
		        )
		        .expectNext("odd", "1", "3", "5", "11", "13")
		        .expectNext("even", "2", "4", "6", "12")
		        .verifyComplete();
		
		System.out.println("-------------------------------------");
		
	}

	private static void broadcastStreamWithAutoConnect() {
		System.out.println("*********Calling broadcastStream to multiple subscribers with autoconnect************");
		Flux<Integer> source = Flux.range(1, 3)
                .doOnSubscribe(s -> System.out.println("subscribed to source"));

		Flux<Integer> autoCo = source.publish().autoConnect(2);
		
		autoCo.subscribe(System.out::println, e -> {}, () -> {});
		System.out.println("subscribed first");
		try {
			Thread.sleep(1000);
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("subscribing second");
		autoCo.subscribe(System.out::println, e -> {}, () -> {});
		
		System.out.println("-------------------------------------");
		
	}

	private static void broadcastStream() {
		System.out.println("*********Calling broadcastStream to multiple subscribers************");
		Flux<Integer> source = Flux.range(1, 3)
                						.doOnSubscribe(s -> System.out.println("subscribed to source"));

		ConnectableFlux<Integer> co = source.publish();
		
		co.subscribe(System.out::println, e -> {}, () -> {});
		co.subscribe(System.out::println, e -> {}, () -> {});
		
		System.out.println("done subscribing");
		try {
			Thread.sleep(1000);
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("will now connect");
		
		co.connect();
		System.out.println("-------------------------------------");
	}

	private static void hotStream() {
		System.out.println("*********Calling hotStream************");
		UnicastProcessor<String> hotSource = UnicastProcessor.create();

		Flux<String> hotFlux = hotSource.publish()
		                                .autoConnect()
		                                .map(String::toUpperCase);


		hotFlux.subscribe(d -> System.out.println("Subscriber 1 to Hot Source: "+d));

		hotSource.onNext("ram");
		hotSource.onNext("sam");

		hotFlux.subscribe(d -> System.out.println("Subscriber 2 to Hot Source: "+d));

		hotSource.onNext("dam");
		hotSource.onNext("lam");
		hotSource.onComplete();
		System.out.println("-------------------------------------");
	}

	private static void coldStream() {
		System.out.println("*********Calling coldStream************");
		Flux<String> source = Flux.fromIterable(Arrays.asList("ram", "sam", "dam", "lam"))
                .doOnNext(System.out::println)
                .filter(s -> s.startsWith("l"))
                .map(String::toUpperCase);

		source.subscribe(d -> System.out.println("Subscriber 1: "+d));
		source.subscribe(d -> System.out.println("Subscriber 2: "+d));
		System.out.println("-------------------------------------");
		
	}

	private static void composeStream() {
		System.out.println("*********Calling composeStream************");
		Function<Flux<String>, Flux<String>> alterMap = f -> {
																	return f.filter(color -> !color.equals("ram"))
																			.map(String::toUpperCase);
																};

		Flux<String> compose = Flux.fromIterable(Arrays.asList("ram", "sam", "kam", "dam"))
										.doOnNext(System.out::println)
										.compose(alterMap);

		compose.subscribe(d -> System.out.println("Subscriber to Composed AlterMap :"+d));
		System.out.println("-------------------------------------");
		
	}

	private static void transformStream() {
		System.out.println("*********Calling transformStream************");
		Function<Flux<String>, Flux<String>> alterMap = f -> f.filter(color -> !color.equals("ram"))
				      											.map(String::toUpperCase);
		
		Flux.fromIterable(Arrays.asList("ram", "sam", "kam", "dam"))
		        .doOnNext(System.out::println)
		        .transform(alterMap)
		        .subscribe(d -> System.out.println("Subscriber to Transformed AlterMap: "+d));
		System.out.println("-------------------------------------");
	}

	private static void unicastProcessor() {
		System.out.println("*********Calling unicastProcessor************");
		SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
		UnicastProcessor<Integer> processor = UnicastProcessor.create();
		FluxSink<Integer> sink = processor.sink(OverflowStrategy.BUFFER);
		//publisher.subscribe(processor);
		sink.next(10);
		System.out.println("-------------------------------------");
	}

	private static void concurrency() {
		System.out.println("*********Calling Concurrency************");
		List<Integer> elements = new ArrayList<>();
		Flux.range(1, 100)
		  //.log()
		  .map(i -> i / 2)
		  .publishOn(Schedulers.parallel()).log()
		  //.subscribeOn(Schedulers.parallel())
		  //.subscribe(elements::add)
		  .blockLast();
		
		System.out.println("-------------------------------------");
		
	}

	public static void mapData() {
		System.out.println("*********Calling MapData************");
		List<Integer> elements = new ArrayList<>();
		Flux.just(1, 2, 3, 4)
		  .log()
		  .map(i -> i * 2)
		  .subscribe(elements::add);
		//printElements(elements);
		System.out.println("-------------------------------------");
	}

	public static void backPressure() {
		System.out.println("*********Calling BackPressure************");
		List<Integer> elements = new ArrayList<>();
		
		Flux.just(1, 2, 3, 4, 5)
		  .log()
		  .subscribe(new Subscriber<Integer>() {
		    private Subscription s;
		    int onNextAmount;
		 
		    @Override
		    public void onSubscribe(Subscription s) {
		        this.s = s;
		        System.out.println("Inside OnSubscribe");
		        s.request(2);
		    }
		 
		    @Override
		    public void onNext(Integer integer) {
		    	 	System.out.println("Inside OnNext");
		        elements.add(integer);
		        onNextAmount++;
		        if (onNextAmount % 2 == 0) {
		            s.request(2);
		        }
		    }
		 
		    @Override
		    public void onError(Throwable t) {
		    		System.out.println("Inside OnComplete");
		    }
		 
		    @Override
		    public void onComplete() {
		    	 	System.out.println("Inside OnComplete");
		    }
		});
		
		printElements(elements);
	}

	public static void connectableFlux() {
		ConnectableFlux<Object> cf = Flux.create(fluxsink -> {
			while(true) {
				fluxsink.next(System.currentTimeMillis());
			}
		}).publish();
		cf.subscribe(System.out::println);
	}
	
	public static void fluxHotStream() {
		List<Integer> elements = new ArrayList<>();
		Flux.just(1, 2, 3, 4)
		  .log()
		  .subscribe(new Subscriber<Integer>() {
		    @Override
		    public void onSubscribe(Subscription s) {
		      s.request(Long.MAX_VALUE);
		    }
		 
		    @Override
		    public void onNext(Integer integer) {
		      elements.add(integer);
		    }
		 
		    @Override
		    public void onError(Throwable t) {}
		 
		    @Override
		    public void onComplete() {}
		});
	}
	
	public static void combineSteams() {

		
//		List<Integer> elems = new ArrayList<>();
//		Flux.just(10,20,30,40)
//			.log()
//			.map(x -> x * 2)
//			.zipWith(Flux.range(0, Integer.MAX_VALUE), 
//					(two, one) -> String.format("First  : %d, Second : %d \n", one, two))
//			.subscribe(elems::add);
		
		
		System.out.println("Inside Combine Streams");
		List<Integer> elems = new ArrayList<>();
	    Flux.just(10,20,30,40)
	        .log()
	        .map(x -> x * 2)
	        .zipWith(Flux.range(0, Integer.MAX_VALUE),
	            (two, one) -> String.format("First  : %d, Second : %d \n", one, two))
	        .subscribe(new Consumer<String>() {
	          @Override
	          public void accept(String s) {

	          }
	        });
	    System.out.println("-------------------------------------");
	    
	}
	
	public static void startFlux() {
		List<Integer> elements = new ArrayList<>();
		Flux.just(1, 2, 3, 4)
		  .log()
		  .subscribe(elements::add);
		printElements(elements);
		 
		assertThat(elements).containsExactly(1, 2, 3, 4);
	}
	
	public static void printElements(List<Integer> elements) {
		System.out.println("Printing Elements ----");
		for(Integer i : elements) {
			System.out.println("     "+i);
		}
	}

}
