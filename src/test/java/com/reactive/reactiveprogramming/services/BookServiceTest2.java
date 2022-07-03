package com.reactive.reactiveprogramming.services;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.reactive.reactiveprogramming.domain.BookInfo;

import reactor.core.publisher.Mono;


@RunWith(SpringRunner.class)
@WebFluxTest(TestController.class)
class BookServiceTest2 {
	
	
	@Autowired
	WebTestClient webTestClient;
	
	@InjectMocks
	private TestController testController;
	
	@MockBean
	private BookService bookService;
	

	@Test
	void getBookTest() {
		
		Mono<BookInfo> booksInfo=Mono.just(new BookInfo(2,"Book1","Goutham","Alekya"));
		
		//Mockito.when(bookInfoService.getBooks()).thenReturn(booksInfo);
		Mockito.when(bookService.fluxToMono()).thenReturn(booksInfo);
	
		
		
		webTestClient.get().uri("/test/bookInfo")
					.exchange()
					.expectStatus().isOk().returnResult(BookInfo.class).getResponseBody();
		
	}

}
