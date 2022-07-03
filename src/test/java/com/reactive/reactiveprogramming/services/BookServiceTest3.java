package com.reactive.reactiveprogramming.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.reactive.reactiveprogramming.domain.BookInfo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@WebFluxTest(BookService.class)
class BookServiceTest3 {
	
	@Mock
	private BookInfoService bookInfoService;
	
	@Mock
	private ReviewService reviewService;
	
	@InjectMocks
	private BookService bookService;

	@Test
	void testFluxToMono() {
		
		Flux<BookInfo> booksInfo=Flux.just(new BookInfo(2,"Book1","Goutham","Alekya"),new BookInfo(3,"Book2","Tavi","BB123"));
		Mockito.when(bookInfoService.getBooks()).thenReturn(booksInfo);
		Mono<BookInfo> mono = bookService.fluxToMono();
		
		StepVerifier.create(mono).expectNextCount(1).verifyComplete();
		
		
	}

}
