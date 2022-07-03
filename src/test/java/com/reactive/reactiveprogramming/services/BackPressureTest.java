package com.reactive.reactiveprogramming.services;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

class BackPressureTest {

	@Test
	void testBackPressure() {
		
		
		Flux<Integer> numbers = Flux.range(1,100).log();
		
		numbers.subscribe(new BaseSubscriber<Integer>() {
			
			@Override
			protected void hookOnSubscribe(Subscription subscription){
				subscription.request(3);
			}
			@Override
			protected void hookOnNext(Integer value){
				System.out.println("value:"+value);
				if(value==3)
					hookOnCancel();
			}

			@Override
			protected void hookOnComplete() {
				System.out.println("completed");
			}
			
			@Override
			protected void hookOnCancel() {
				System.out.println("cancelled");
			}
			
			
		});
			
	}
	
	@Test
	void testBackPressureDrop() {
		
		
		Flux<Integer> numbers = Flux.range(1,100).log();
		
		numbers.onBackpressureDrop(integer->{
			System.out.println("dropped:"+integer);
		})
		.subscribe(new BaseSubscriber<Integer>() {
			
			@Override
			protected void hookOnSubscribe(Subscription subscription){
				subscription.request(3);
			}
			@Override
			protected void hookOnNext(Integer value){
				System.out.println("value:"+value);
				if(value==3)
					hookOnCancel();
			}

			@Override
			protected void hookOnComplete() {
				super.hookOnComplete();
				System.out.println("completed");
			}
			
			@Override
			protected void hookOnCancel() {
				super.hookOnCancel();
			}
			
			
		});
			
	}
	
	@Test
	void testBackPressureBuffer() {
		
		
		Flux<Integer> numbers = Flux.range(1,100).log();
		
		numbers.onBackpressureBuffer(10, i->System.out.println("Buffered value:"+i))
		.subscribe(new BaseSubscriber<Integer>() {
			
			@Override
			protected void hookOnSubscribe(Subscription subscription){
				subscription.request(3);
			}
			@Override
			protected void hookOnNext(Integer value){
				System.out.println("value:"+value);
				if(value==3)
					hookOnCancel();
			}

			@Override
			protected void hookOnComplete() {
				super.hookOnComplete();
				System.out.println("completed");
			}
			
			@Override
			protected void hookOnCancel() {
				super.hookOnCancel();
			}
			
			
		});
			
	}
	
	@Test
	void testBackPressureError() {
		
		
		Flux<Integer> numbers = Flux.range(1,100).log();
		
		numbers.onBackpressureError()
		.subscribe(new BaseSubscriber<Integer>() {
			
			@Override
			protected void hookOnSubscribe(Subscription subscription){
				subscription.request(3);
			}
			@Override
			protected void hookOnNext(Integer value){
				System.out.println("value:"+value);
				if(value==3)
					hookOnCancel();
			}

			@Override
			protected void hookOnComplete() {
				super.hookOnComplete();
				System.out.println("completed");
			}
			
			@Override
			protected void hookOnCancel() {
				super.hookOnCancel();
			}
			
			
		});
			
	}


}
