package com.portal.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String price;
	private String note;
	private String source;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publishing_id")
	private Publishing publishing;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id")
	private Branch branch;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_cover_id")
	private BookCover bookCover;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lang_id")
	private Language language;
	@OneToMany(mappedBy = "book")
	@JsonManagedReference
	private List<ImageBook> imageBook = new ArrayList<>();
	@OneToMany(mappedBy = "book")
	@JsonManagedReference
	private List<BookAuthor> bookAuthor = new ArrayList<>();
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private int views;
	private String link;
	@OneToMany(mappedBy = "book")
	private List<Comment> comment = new ArrayList<Comment>();
	
	public Book() {
	}

	public Book(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Publishing getPublishing() {
		return publishing;
	}

	public void setPublishing(Publishing publishing) {
		this.publishing = publishing;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public BookCover getBookCover() {
		return bookCover;
	}

	public void setBookCover(BookCover bookCover) {
		this.bookCover = bookCover;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<ImageBook> getImageBook() {
		return imageBook;
	}

	public void setImageBook(List<ImageBook> imageBook) {
		this.imageBook = imageBook;
	}

	public List<BookAuthor> getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(List<BookAuthor> bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	
	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
}
