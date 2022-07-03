package com.reactive.reactiveprogramming.services;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Addition addition=new Addition();
		
		System.out.println(addition.add(2, 3));
		
		Addition Addition1=new MYAddition();
		
		System.out.println(Addition1.add(2, 3));
		
		MYAddition myAddition=new MYAddition();
		myAddition.add(2, 20, 8);

	}

}
