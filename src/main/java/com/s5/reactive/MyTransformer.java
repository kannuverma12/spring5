package com.s5.reactive;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

public class MyTransformer<T, R> extends SubmissionPublisher<R> implements Flow.Processor<T, R> {

	private Function<T, R> function;
	private Flow.Subscription subscription;
	
	public MyTransformer(Function<T, R> function) {
        super();
        this.function = function;
    }
	
	@Override
	public void onComplete() {
		System.out.println("Transformer Completed");
	}

	@Override
	public void onError(Throwable e) {
		e.printStackTrace();
	}

	@Override
	public void onNext(T item) {
		System.out.println("Transformer Got : "+item);
		submit(function.apply(item));
		subscription.request(1);
		
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		this.subscription = subscription;
		subscription.request(1);
	}

	

}
