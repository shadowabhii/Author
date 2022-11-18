package com.author.services;

import java.util.List;
import java.util.Optional;

import com.author.model.Book;
public interface IAuthorService {
	
	public Integer addBook(Book book);
	public List<Book> getAllBook();
	Optional<Book> getBookById(Integer id);
	public void deleteBook(Integer id);
	public Book updateBook(Book book, Integer id);
	List<Book> getBookByCategory(String category);
	List<Book> getBookByprice(Double price);
	List<Book> getBookByPriceCategory(String category, Double price);
	List<Book> getBookByAuthor(String author);
	Double getTotalRevenue(String author);
	List<Book> getBookByAuthorId(Integer authorId);
	
}
