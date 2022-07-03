package com.reactive.reactiveprogramming.domain;

public class Review {
	
	private long reviewId;
	private long bookId;
	private double ratings;
	private String comments;
	public Review() {

	}
	public Review(long reviewId, long bookId, double ratings, String comments) {
		super();
		this.reviewId = reviewId;
		this.bookId = bookId;
		this.ratings = ratings;
		this.comments = comments;
	}
	public long getReviewId() {
		return reviewId;
	}
	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public double getRatings() {
		return ratings;
	}
	public void setRatings(double ratings) {
		this.ratings = ratings;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	

}
