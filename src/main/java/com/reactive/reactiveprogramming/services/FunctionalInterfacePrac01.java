package com.reactive.reactiveprogramming.services;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfacePrac01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Supplier<Integer> supplier=()-> 2*2;
		
		System.out.println(supplier.get());
		
		Consumer<String> consumer=x->System.out.println(x);
		consumer.accept("hello");
		
		Predicate<Integer> predicate=x->x/2==1;
		System.out.println(predicate.test(2));
		
		Function<Integer,String> function=x->x+"*";
		System.out.println(function.apply(2));
		
		//BiConsumer, BiPredicate, BiFunction
		
		BiPredicate<Integer,Integer> bipredicate=(a,b)->a+b==2;
		System.out.println(bipredicate.test(1, 2));
		
		BiFunction<Integer,String,String> bifunction=(a,b)->a+"*"+b;
		System.out.println(bifunction.apply(2, "2"));
		
		BiConsumer<Integer,Integer> biconsumer=(a,b)->{
			System.out.println("addition is:"+(a+b));
		};
		
		biconsumer.accept(2, 3);
		
		

	}

}
