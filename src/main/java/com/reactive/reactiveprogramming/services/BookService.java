package com.reactive.reactiveprogramming.services;

import java.time.Duration;
import java.util.List;

import com.reactive.reactiveprogramming.domain.Book;
import com.reactive.reactiveprogramming.domain.BookInfo;
import com.reactive.reactiveprogramming.domain.Review;
import com.reactive.reactiveprogramming.exception.BookException;

import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

public class BookService {
	
	private BookInfoService bookInfoService=new BookInfoService();
	private ReviewService reviewService=new ReviewService();
	
	public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
		super();
		this.bookInfoService = bookInfoService;
		this.reviewService = reviewService;
	}
	
	public BookService() {
		
	}
	
	public Flux<Book> getBooks(){
		
		Flux<BookInfo> booksInfo = bookInfoService.getBooks();
		
		return booksInfo.flatMap(record->{
			Mono<List<Review>> collector = reviewService.getReviews(record.getBookId()).collectList();
			return collector.map(review->new Book());
		}).log();

		/*return booksInfo.flatMap(record->{
			Mono<List<Review>> reviews=reviewService.getReviews(record.getBookId()).collectList();		
			return reviews.map(review->new Book(record,review));
		})
		.onErrorMap( Throwable->{
			return new BookException("Book Error");
		})
		.log();*/
		
	}
	
	public Flux<Book> getBooksRetry(){
		
		Flux<BookInfo> booksInfo = bookInfoService.getBooks();
		
		return booksInfo.flatMap(record->{
			Mono<List<Review>> reviews=reviewService.getReviews(record.getBookId()).collectList();		
			return reviews.map(review->new Book(record,review));
		})
		.onErrorMap( Throwable->{
			return new BookException("Book Error");
		})
		.retry(3)
		.log();
		
	}
	
	public Flux<Book> getBooksRetryWhen(){
		
		RetryBackoffSpec onRetryExhaustedThrow = Retry.backoff(3, Duration.ofMillis(1000))
		.filter(throwable->throwable instanceof BookException)
		.onRetryExhaustedThrow((retryBackoffSpec,retrySignal)->
		Exceptions.propagate(retrySignal.failure())
				);
		
		Flux<BookInfo> booksInfo = bookInfoService.getBooks();
		
		return booksInfo.flatMap(record->{
			Mono<List<Review>> reviews=reviewService.getReviews(record.getBookId()).collectList();		
			return reviews.map(review->new Book(record,review));
			
		})
		.onErrorMap( Throwable->{
			return new BookException("Book Error");
		})
		.retryWhen(onRetryExhaustedThrow)
		.log();
		
	}
	
	public Mono<Book> getBookById(long id){
		
		Mono<BookInfo> bookById = bookInfoService.getBookById(id);
		 Mono<List<Review>> reviews = reviewService.getReviews(id).collectList();

		return bookById.zipWith(reviews,(b,r)-> new Book(b,r));
		
	}
	
	
	public Mono<BookInfo> fluxToMono(){
		
		System.out.println("in fluxToMono");
		
		Flux<BookInfo> booksInfo = bookInfoService.getBooks();
		
		return booksInfo.filter(x->x.getAuthor().equals("Goutham")).take(1).last();
		
	}
	
	public Mono<Book> monoBookService(int bookId){
		
		Flux<BookInfo> booksInfo=bookInfoService.getBooks();
		
		Mono<BookInfo> bookInfo=booksInfo.filter(x->x.getBookId()==bookId).next();
		
		Mono<List<Review>> reviews=reviewService.getReviews(bookId).collectList();
		
		return bookInfo.zipWith(reviews, (b,r)->new Book(b,r));
		
	}

}
