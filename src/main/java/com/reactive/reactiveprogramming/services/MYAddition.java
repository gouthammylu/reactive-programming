package com.reactive.reactiveprogramming.services;

public class MYAddition extends Addition {

	@Override
	public String add(int a, int b) {
		
		int c=a+b+2;

		return String.valueOf(c);
	}
	
	public void add(int a,int b,int c) {
		
		System.out.println("sum of three:"+(a+b+c));
		
	}
	
	

}
