package com.s5.webclient;

import java.net.URI;
import java.nio.charset.Charset;
import java.time.ZonedDateTime;
import java.util.concurrent.SubmissionPublisher;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;

import com.s5.reactive.TestReactiveStreams;

import reactor.core.publisher.Mono;


public class MyWebClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebClient wc1 = WebClient.create();
		
		WebClient wc2 = WebClient.create("http://localhost:8080");
		
		// 1.	Create a web client
		WebClient wc3 = WebClient.builder()
								.baseUrl("http://localhost:8080")
								.defaultCookie("key", "val")
								.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
								.build();
		
		//	2.	Specify an Http method
		WebClient.UriSpec<WebClient.RequestBodySpec> request1 = wc3.method(HttpMethod.POST);
		WebClient.UriSpec<WebClient.RequestBodySpec> request2 = wc3.post();
		
		//	3. 	Provide a URL
		WebClient.RequestBodySpec uri1 = wc3.method(HttpMethod.POST).uri("/getDocs");
		WebClient.RequestBodySpec uri2 = wc3.post().uri(URI.create("/getDocs"));
		
		
		//	4. 	set a request body, content type, length, cookies or headers
		WebClient.RequestHeadersSpec<?> requestSpec1 = WebClient
														.create()
														.method(HttpMethod.POST).uri("/getDocs")
														.body(BodyInserters
																.fromPublisher(Mono.just("data"), 
																				String.class)
														);
				
		
		WebClient.RequestHeadersSpec<?> data = WebClient.create().method(HttpMethod.POST).uri("/getDocs")
                .body(BodyInserters.fromPublisher(Mono.just("data"), String.class));
		
		WebClient.RequestHeadersSpec<?> requestSpec2 = WebClient
				  .create("http://localhost:8080")
				  .post()
				  .uri(URI.create("/getDocs"))
				  .body(BodyInserters.fromObject("data"));
				  
		
		/*
		 * The BodyInserter is an interface responsible for populating a ReactiveHttpOutputMessage body 
		 * with a given output message and a context used during the insertion. A Publisher is a 
		 * reactive component that is in charge of providing a potentially unbounded number of sequenced elements.

			The second way is the body method, which is a shortcut for the original body(BodyInserter inserter) 
			method.

			To alleviate this process of filling a BodyInserter, there is a BodyInserters 
			class which a bunch of useful utility methods:
		 */
		
		BodyInserter<Publisher<String>, ReactiveHttpOutputMessage> inserter1 = BodyInserters
															.fromPublisher(Subscriber::onComplete, String.class);

//		BodyInserter<Publisher<String>, ReactiveHttpOutputMessage> inserter2 = BodyInserters
//				.fromPublisher(TestReactiveStreams.publisher, SubmissionPublisher.class);
//		
		LinkedMultiValueMap map = new LinkedMultiValueMap();
		 
		map.add("key1", "value1");
		map.add("key2", "value2");
		 
		BodyInserter<MultiValueMap, ClientHttpRequest> inserter2
		 = BodyInserters.fromMultipartData(map);
		
		BodyInserter<Object, ReactiveHttpOutputMessage> inserter3
		 = BodyInserters.fromObject(new Object());
		
		
		// 5.	Set headers etc
		
		WebClient.ResponseSpec response1 = uri1
				  .body(inserter3)
				    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				    .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
				    .acceptCharset(Charset.forName("UTF-8"))
				    .ifNoneMatch("*")
				    .ifModifiedSince(ZonedDateTime.now())
				  .retrieve();
		
		// 6. 	Get a response
		
		String response2 = ((RequestHeadersSpec<RequestBodySpec>) request1).exchange()
				  .block()
				  .bodyToMono(String.class)
				  .block();
				String response3 = ((RequestHeadersSpec<RequestBodySpec>) request2)
				  .retrieve()
				  .bodyToMono(String.class)
				  .block();
		
	}

}
