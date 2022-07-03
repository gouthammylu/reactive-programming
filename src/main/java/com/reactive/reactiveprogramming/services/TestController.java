package com.reactive.reactiveprogramming.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.reactiveprogramming.domain.Book;
import com.reactive.reactiveprogramming.domain.BookInfo;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("/bookInfo")
	public Mono<BookInfo> getBook(){
		
		return bookService.fluxToMono();
		
	}
	
	public Mono<Book> monoBookService(@PathVariable("bookId") int bookId){
		
		return bookService.monoBookService(bookId);
	}

}
