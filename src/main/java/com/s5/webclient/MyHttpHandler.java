package com.s5.webclient;

import org.apache.catalina.connector.Response;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

public class MyHttpHandler {
	
	public static void main(String... args) {
		
	}
	
	public static void checkHttpHandler() {
		HttpHandler httpHandler = RouterFunctions.toHttpHandler(routingFunction());
		
	}
	
	public static RouterFunction<? extends ServerResponse> routingFunction() {
		
	    return route(RequestPredicates.path("/"), req -> ServerResponse.ok().build());
		//return null;
	}
	
	public static <T extends ServerResponse> RouterFunction<T> route(
			  RequestPredicate predicate,
			  HandlerFunction<T> handlerFunction){
		return null;
	}
	
	public static void helloWorldRoute() {
//		RouterFunction<ServerResponse> helloWorldRoute =
//				RouterFunctions.route(RequestPredicates.path("/hello-world"),
//				request -> ServerResponse.ok().body(fromObject("Hello World")));
	}

}
