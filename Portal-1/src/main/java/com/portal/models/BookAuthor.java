package com.portal.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book_author")
public class BookAuthor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id")
	private Book book;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	private Author author;
	
	public BookAuthor() {}
	
	
	public BookAuthor(Book book, Author author) {
		this.book = book;
		this.author = author;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}


}

