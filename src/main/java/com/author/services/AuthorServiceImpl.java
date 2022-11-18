package com.author.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.author.Exception.ResourceNotFoundException;
import com.author.model.Book;



@Service
public class AuthorServiceImpl implements IAuthorService {

	@Autowired
	IAuthorRepository authorRepository;
	
	@Override
	public Integer addBook(Book book) {
	Book bookAdded = 	authorRepository.save(book);
		return bookAdded.getbId();
	}
	
//to get all books
	@Override
	public List<Book> getAllBook() {
		
		return authorRepository.findAll();
	}
//get book by id
	@Override
	public Optional<Book> getBookById(Integer id) {
		
		
		
		return authorRepository.findById(id);
	}
//deletebook
	@Override
	public void deleteBook(Integer id) {
		
		authorRepository.deleteById(id);
	}

	@Override
	public Book updateBook(Book book, Integer id) {
		
		System.out.println(id);
		System.out.println(book);
		
		Book existingbook = authorRepository.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Book","id",id));
		
		System.out.println(existingbook+"hello");
		
		existingbook.setTitle(book.getTitle());
		existingbook.setPublisher(book.getPublisher());
		existingbook.setPublishedDate(book.getPublishedDate());
		existingbook.setPrice(book.getPrice());
		existingbook.setLogo(book.getLogo());
		existingbook.setChapterOrContent(book.getChapterOrContent());
		existingbook.setCategory(book.getCategory());
		existingbook.setAuthor(book.getAuthor());
		existingbook.setActive(book.isActive());
			
		
		authorRepository.save(existingbook);
		System.out.println(existingbook);
		
		
		return existingbook;
	}

	@Override
	public List<Book> getBookByCategory(String category) {
		// TODO Auto-generated method stub
		List<Book> book=	authorRepository.findAll().stream().filter(e-> (e.getCategory().equalsIgnoreCase(category))&&(e.isActive())).
		collect(Collectors.toList());
		
		
		return book;
	}

	@Override
	public List<Book> getBookByprice(Double price) {
		
		System.out.println(price);
		List<Book> book=	authorRepository.findAll().stream().filter(e-> (e.getPrice().equals(price))&&(e.isActive())).
				collect(Collectors.toList());
		
		System.out.println(book);
				return book;
	}

	@Override
	public List<Book> getBookByPriceCategory(String category, Double price) {
		// TODO Auto-generated method stub
		List<Book> book=	authorRepository.findAll().stream().filter(e->
		(e.getPrice().equals(price))&&(e.isActive())&&e.getCategory().equalsIgnoreCase(category)).
				collect(Collectors.toList());
		
		System.out.println(book);
				return book;

	}

	@Override
	public List<Book> getBookByAuthor(String author) {
		// TODO Auto-generated method stub
		List<Book> book=	authorRepository.findAll().stream().filter(e-> (e.getAuthor().equalsIgnoreCase(author))&&(e.isActive())).
		collect(Collectors.toList());
		return book;
	}

	@Override
	public Double getTotalRevenue(String author) {
		
		List<Book> book=	authorRepository.findAll().stream().filter(e-> (e.getAuthor().equalsIgnoreCase(author))&&(e.isActive())).
				collect(Collectors.toList());
		
	double totalPrice=0.0;
	for(int i=0;i<book.size();i++)
	{
		totalPrice=totalPrice+book.get(i).getPrice();
	}
		
		return totalPrice;
	}

	@Override
	public List<Book> getBookByAuthorId(Integer authorId) {
		// TODO Auto-generated method stub
		List<Book> book=	authorRepository.findAll().stream().filter(e-> (e.getaId()==authorId)).
		collect(Collectors.toList());
		return book;
	}


}
