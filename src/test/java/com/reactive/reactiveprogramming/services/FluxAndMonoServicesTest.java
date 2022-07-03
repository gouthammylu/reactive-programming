package com.reactive.reactiveprogramming.services;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class FluxAndMonoServicesTest {
	
	FluxAndMonoServices fluxAndMonoServices=new FluxAndMonoServices();

	@Test
	void testFruitsFlux() {
		
		Flux<String> fruitsFlux = fluxAndMonoServices.fruitsFlux();
		StepVerifier.create(fruitsFlux)
					.expectNext("mango","banana","apple")
					.verifyComplete();
		
	}

	@Test
	void testFruitsMono() {
		
		Mono<String> fruitsMono=fluxAndMonoServices.fruitsMono();
		
		StepVerifier.create(fruitsMono) 
					.expectNext("kiwi")
					.verifyComplete();
	
	}
	
	@Test
	void testFruitsFluxFilterMap() {
		
		Flux<String> fruitsfluxAndMonoServices=fluxAndMonoServices.fruitsFluxFilterMap(5);
		
		StepVerifier.create(fruitsfluxAndMonoServices)
					.expectNext("BANANA")
					.verifyComplete();
	
	}
	
	@Test
	void testfruitsFluxFatMapAsync() {
		
		Flux<String> fruitsFluxFatMapAsync = fluxAndMonoServices.fruitsFluxFatMapAsync();
		
		StepVerifier.create(fruitsFluxFatMapAsync)
					.expectNextCount(16)
					.verifyComplete();
		
	}
	
	@Test
	void testFruitsMonoFatMapMany() {
		
		Flux<String> fruitsMonoFatMapMany = fluxAndMonoServices.fruitsMonoFatMapMany();
		
		StepVerifier.create(fruitsMonoFatMapMany)
					.expectNextCount(5)
					.verifyComplete();
		
	}
	
	@Test
	void testFruitsFluxTransform() {
		
		Flux<String> fruitsFluxTransform = fluxAndMonoServices.fruitsFluxTransform(10);
		
		StepVerifier.create(fruitsFluxTransform)
					.expectNext("Default")
					.verifyComplete();
		
	}
	
	@Test
	void testFruitsFluxTransformSwitchIfEmpty() {
		
		
		Flux<String> fruitsFluxTransformSwitchIfEmpty = fluxAndMonoServices.fruitsFluxTransformSwitchIfEmpty(10);
		
		StepVerifier.create(fruitsFluxTransformSwitchIfEmpty)
					.expectNext("pomogranate")
					.verifyComplete();
		
	}
	
	@Test
	void testFruitsFluxConcat() {
		
		Flux<String> fruitsFluxConcat = fluxAndMonoServices.fruitsFluxConcat();
		
		StepVerifier.create(fruitsFluxConcat)
					.expectNext("mango","orange","tomato","lemon")
					.verifyComplete();
		
	}
	
	@Test
	void testFruitsMonoConcat() {
		
		Flux<String> fruitsMonoConcat = fluxAndMonoServices.fruitsMonoConcat();
		
		StepVerifier.create(fruitsMonoConcat)
					.expectNext("mango","tomato")
					.verifyComplete();
		
	}
	
	@Test
	void testFruitsFluxMerge() {
		
		Flux<String> fruitsFluxMerge = fluxAndMonoServices.fruitsFluxMerge();
		
		StepVerifier.create(fruitsFluxMerge)
					.expectNext("mango","tomato","orange","lemon")
					.verifyComplete();
		
	}
	
	@Test
	void testFruitsFluxMergeWithSequential() {
		
		Flux<String> fruitsFluxMergeSeq = fluxAndMonoServices.fruitsFluxMergeWithSequential();
		
		StepVerifier.create(fruitsFluxMergeSeq)
					.expectNext("mango","orange","tomato","lemon")
					.verifyComplete();
		
	}
	
	@Test
	void testFruitsFluxZip() {
		
		Flux<String> fruitsFluxZip = fluxAndMonoServices.fruitsFluxZip();
		
		StepVerifier.create(fruitsFluxZip)
					.expectNext("mangotomato","orangelemon")
					.verifyComplete();
		
	}
	
	@Test
	void testFruitsFluxTuple() {
		
		Flux<String> fruitsFluxZipTuple = fluxAndMonoServices.fruitsFluxTuple();
		
		StepVerifier.create(fruitsFluxZipTuple)
		.expectNext("mangotomatopotato","orangelemonbeans")
		.verifyComplete();
		
	}
	
	@Test
	void testFruitsFluxDoOn() {
		
		Flux<String> fruitsFluxDoOn = fluxAndMonoServices.fruitsFluxDoOn();
		
		StepVerifier.create(fruitsFluxDoOn)
		.expectNext("mango","orange")
		.verifyComplete();
		
	}
	
	@Test
	void testFruitsFluxOnErrorReturn() {
		
		Flux<String> fruitsFluxOnErrorReturn = fluxAndMonoServices.fruitsFluxOnErrorReturn();
		
		StepVerifier.create(fruitsFluxOnErrorReturn)
					.expectNext("mango","orange","apple")
					.verifyComplete();
		
	}
	
	@Test
	void testTruitsFluxOnErrorContinue(){
		
		Flux<String> fruitsFluxOnErrorContinue = fluxAndMonoServices.fruitsFluxOnErrorContinue();
		
		StepVerifier.create(fruitsFluxOnErrorContinue)
		.expectNext("ORANGE","APPLE")
		.verifyComplete();
		
	}
	
	@Test
	void testFruitsFluxOnErrorMap() {
		
		Flux<String> fruitsFluxOnErrorMap = fluxAndMonoServices.fruitsFluxOnErrorMap();
		
		StepVerifier.create(fruitsFluxOnErrorMap)
		.expectNext("APPLE")
		.expectError(IllegalStateException.class)
		.verify();
		
	}
	
	
	
	

}
