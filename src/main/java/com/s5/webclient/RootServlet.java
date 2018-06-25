package com.s5.webclient;

import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServletHttpHandlerAdapter;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;

public class RootServlet extends ServletHttpHandlerAdapter {
	 
//    public RootServlet() {
//        this(WebHttpHandlerBuilder
//          .webHandler(toHttpHandler(routingFunction()))
//          .filter(new IndexRewriteFilter())
//          .build());
//    }
 
    private static Object routingFunction() {
		// TODO Auto-generated method stub
		return null;
	}

	RootServlet(HttpHandler httpHandler) {
        super(httpHandler);
    }
 
    //...
 
}