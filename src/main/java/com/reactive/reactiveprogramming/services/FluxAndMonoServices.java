package com.reactive.reactiveprogramming.services;

import java.time.Duration;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class FluxAndMonoServices {
	
	
	public Flux<String> fruitsFlux(){
		
		return Flux.fromIterable(Arrays.asList("mango","banana","apple")).log();
	}
	
	public Mono<String> fruitsMono(){
		return Mono.just("kiwi").log();
	}
	//filter method
	public Flux<String> fruitsFluxFilterMap(int number){
		
		return Flux.fromIterable(Arrays.asList("mango","banana","apple"))
				   .filter(x->x.length()>number)
				   .map(String::toUpperCase);
	}
	// flatMap Flux<String> to Flux<String>  -> "mango" -> "m","a","n","g","o"
	public Flux<String> fruitsFluxFatMapAsync(){
		
		return Flux.fromIterable(Arrays.asList("mango","banana","apple"))
				  .flatMap(x->Flux.just(x.split("")))
				  .delayElements(Duration.ofMillis(new Random().nextInt(1000)))
				   .log();
	}
	
	// flatMapMany Mono<String> to Flux<String>
	public Flux<String> fruitsMonoFatMapMany(){
		
		return Mono.just("mango")
					.flatMapMany(x->Flux.just(x.split("")))
					.log();
	}
	// transform and default if empty method
	public Flux<String> fruitsFluxTransform(int number){
		
		Function<Flux<String>,Flux<String>> filterData=data->data.filter(x->x.length()>number);
		
		return Flux.fromIterable(Arrays.asList("mango","banana","apple"))
				.transform(filterData)
				.defaultIfEmpty("Default")
				.log();
		
	}
	//Transform and Switch if empty methods
	public Flux<String> fruitsFluxTransformSwitchIfEmpty(int number){
		
		Function<Flux<String>,Flux<String>> filterData=data->data.filter(x->x.length()>number);
		
		return Flux.fromIterable(Arrays.asList("mango","banana","apple"))
				.transform(filterData)
				.switchIfEmpty(Flux.just("pineapple","pomogranate"))
				.transform(filterData)
				.log();
		
	}
	
	//concat method. used to combine two flux
	public Flux<String> fruitsFluxConcat(){
		
		Flux<String> fruits=Flux.just("mango","orange");
		Flux<String> veggies=Flux.just("tomato","lemon");
		
		//return Flux.concat(fruits,veggies).log();
		// (or)
		return fruits.concatWith(veggies);
	}
	
	//concat method. used to combine two mono
	public Flux<String> fruitsMonoConcat(){
		
		Mono<String> fruits=Mono.just("mango");
		Mono<String> veggies=Mono.just("tomato");
		
		//return Mono.concat(fruits,veggies).log();
		// (or)
		return fruits.concatWith(veggies);
	}
	
	//merge method. used to combine two flux. not in sequential
	public Flux<String> fruitsFluxMerge(){
		
		Flux<String> fruits=Flux.just("mango","orange")
								.delayElements(Duration.ofMillis(50));
		Flux<String> veggies=Flux.just("tomato","lemon")
								.delayElements(Duration.ofMillis(75));
		
		//return Flux.merge(fruits,veggies).log();
		// (or)
		return fruits.mergeWith(veggies);
	}
	
	//merge method. used to combine two flux. not in sequential
	public Flux<String> fruitsFluxMergeWithSequential(){
		
		Flux<String> fruits=Flux.just("mango","orange")
								.delayElements(Duration.ofMillis(50));
		Flux<String> veggies=Flux.just("tomato","lemon")
								.delayElements(Duration.ofMillis(75));
		
		return Flux.mergeSequential(fruits,veggies).log();
	}
	
	public Flux<String> fruitsFluxZip(){
		
		Flux<String> fruits=Flux.just("mango","orange");
		Flux<String> veggies=Flux.just("tomato","lemon");
		
		//return Flux.zip(fruits, veggies, (first,second)->first+second).log();
		
		//or
		return fruits.zipWith(veggies, (first,second)->first+second).log();
	}
	
	public Flux<String> fruitsFluxTuple(){
		
		Flux<String> fruits=Flux.just("mango","orange");
		Flux<String> veggies=Flux.just("tomato","lemon");
		Flux<String> moreveggies=Flux.just("potato","beans");

		return Flux.zip(fruits, veggies, moreveggies)
				.map(x->x.getT1()+x.getT2()+x.getT3()).log();
	}
	
	//doOnError,doOnSubscribe,doOnComplete are  methods kind of used for debug puropse
	public Flux<String> fruitsFluxDoOn() {

		return Flux.just("mango","orange")
					.doOnNext(X->System.out.println(X))
					.doOnSubscribe(sub->System.out.println(sub.toString()))
					.log();
	}
	
	// on error default value will be emitted
	public Flux<String> fruitsFluxOnErrorReturn(){
		
		return Flux.just("mango","orange")
					.concatWith(Flux.error(new RuntimeException("Exception occured")))
					.onErrorReturn("apple");
	}
	
	// on error it will skip the emitting error value
	public Flux<String> fruitsFluxOnErrorContinue(){
		
		return Flux.just("mango","orange","apple")
					.map(s->{
						
						if(s.equalsIgnoreCase("mango"))
							throw new RuntimeException("Exception occured");
						return s.toUpperCase();
						
					}).onErrorContinue((e,f)->{
						System.out.println("e="+e);
						System.out.println("f="+f);
					}).log();
					
	}
	
	public Flux<String> fruitsFluxOnErrorMap(){
		
		return Flux.just("apple","mango","orange")
					.map(s->{
						
						if(s.equalsIgnoreCase("mango"))
							throw new RuntimeException("Exception occured");
						return s.toUpperCase();
						
					}).onErrorMap(throwable->{
						System.out.println("throwable:"+throwable);
						return new IllegalStateException("from onError map");
					}).log();
					
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		FluxAndMonoServices services=new FluxAndMonoServices();
		
		services.fruitsFlux().subscribe(x->System.out.println(x));
		
		services.fruitsMono().subscribe(x->System.out.println(x));

	}

}
