package com.reactive.reactiveprogramming.services;

import java.util.Arrays;
import java.util.List;

import com.reactive.reactiveprogramming.domain.BookInfo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class BookInfoService {

	
	public Flux<BookInfo> getBooks(){
		
		List<BookInfo> booksInfo=Arrays.asList(new BookInfo(1,"Book One","Author One","121212"),
				new BookInfo(2,"Book Two","Author Two","23456"),
				new BookInfo(3,"Book Three","Author Three","67890"));
		System.out.println("in repo");
		return Flux.fromIterable(booksInfo);
	}
	
	public Mono<BookInfo> getBookById(long id){
		BookInfo book=null;
		if(id==1)
		book=new BookInfo(1,"Book One","Author One","121212");
		else if(id==2)
		book=new BookInfo(2,"Book Two","Author Two","23456");
		else
		book=new BookInfo(2,"Book Three","Author Three","67890");
		
		return Mono.just(book);
		
	}
}
