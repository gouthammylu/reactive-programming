package com.reactive.reactiveprogramming.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.reactive.reactiveprogramming.domain.Book;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class BookServiceTest {
	
	private BookInfoService bookInfoService=new BookInfoService();
	private ReviewService reviewServicenew=new ReviewService();
	private BookService bookService=new BookService(bookInfoService,reviewServicenew);

	@Test
	void testBookService() {
		
		Flux<Book> books = bookService.getBooks();
		
		StepVerifier.create(books)
					.assertNext(book->{
						assertEquals("Book One", book.getBookInfo().getTitle());
						assertEquals(3, book.getReview().size());
					})
		.assertNext(book->{
			assertEquals("Book Two", book.getBookInfo().getTitle());
			assertEquals(3, book.getReview().size());
		})
		.assertNext(book->{
			assertEquals("Book Three", book.getBookInfo().getTitle());
			assertEquals(3, book.getReview().size());
		}).verifyComplete();
	}
	
	@Test
	void testgetBookById(){
		
		Mono<Book> book = bookService.getBookById(1);
		
		StepVerifier.create(book)
		.assertNext(x->{
			assertEquals("Book One", x.getBookInfo().getTitle());
			assertEquals(3, x.getReview().size());
		}).verifyComplete();
	}

}
