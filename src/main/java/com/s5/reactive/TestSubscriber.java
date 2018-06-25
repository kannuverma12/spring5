package com.s5.reactive;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class TestSubscriber<T> implements Subscriber<T> {
	
	private Subscription subscription;
	
	public List<T> consumed = new LinkedList<>();

	@Override
	public void onComplete() {
		System.out.println("Subsciber Completed");
	}

	@Override
	public void onError(Throwable arg0) {
		arg0.printStackTrace();
	}

	@Override
	public void onNext(T item) {
		System.out.println("In Subscriber Got : "+item);
		subscription.request(1);
		
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);
		
	}

}
