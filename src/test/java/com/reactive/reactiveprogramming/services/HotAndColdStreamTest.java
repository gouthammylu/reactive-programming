package com.reactive.reactiveprogramming.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

class HotAndColdStreamTest {

	@Test
	void coldStreamTest() {
		
		Flux<Integer> range = Flux.range(1, 10);
		range.subscribe(i->System.out.println("sub1:"+i));
		range.subscribe(i->System.out.println("sub2:"+i));
	}
	
	@Test
	void hotStreamTest() throws InterruptedException {
		
		Flux<Integer> numbers = Flux.range(1, 10).delayElements(Duration.ofMillis(1000));
		
		ConnectableFlux<Integer> publisher = numbers.publish();
		publisher.connect();
		publisher.subscribe(integer->System.out.println("sub1:"+integer));
		Thread.sleep(4000);
		publisher.subscribe(integer->System.out.println("sub2:"+integer));
		Thread.sleep(10000);
		
	}

}
