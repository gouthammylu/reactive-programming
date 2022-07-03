package com.reactive.reactiveprogramming.domain;

import java.util.List;

public class Book {
	
	private BookInfo bookInfo;
	private List<Review> review;
	
	public Book(BookInfo bookInfo, List<Review> review) {
		super();
		this.bookInfo = bookInfo;
		this.review = review;
	}
	public Book() {

	}
	public BookInfo getBookInfo() {
		return bookInfo;
	}
	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}
	public List<Review> getReview() {
		return review;
	}
	public void setReview(List<Review> review) {
		this.review = review;
	}
	
	

}
