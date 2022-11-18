package com.author.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.author.model.Author;
import com.author.model.Book;
import com.author.services.IAuthorService;
import com.author.services.signup.IAuthSignupService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuthorController {

	@Autowired
	IAuthorService authorService;
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private IAuthSignupService authSignupService;
	
	private static String UPLOADED_FOLDER = System.getProperty("user.dir")+File.separator+"//src//main//resources";

	@PostMapping("/signup")
	Integer createAuthor(@RequestBody Author author) {

		Integer id = authSignupService.createAuthor(author);

		System.out.println(id);

		return id;

	}
	
	@GetMapping("/sigin/{userName}/{password}")
	public List<Author> signin(@PathVariable("userName") String userName,@PathVariable("password") String password)
	{
		List<Author> signedIn=authSignupService.sigin(userName, password);
		return signedIn;
		
	}
	
	@GetMapping("/author/{aid}")
	public Optional<Author> getAuthorById(@PathVariable Integer aid) {
		Optional<Author> auth = authSignupService.getAuthorById(aid);
		return auth;
	}
	
	@GetMapping("/author")
	public List<Author> getAllAuthor() {
		return authSignupService.getAllAuthor();
	}
	

	@RequestMapping("/hello")
	public String hello() {

		// System.out.println("HELLO");
		return "Welcome to Author MicroService";
	}


	
	
	@PostMapping("/addbook")
	public Integer saveBook(@RequestParam("bookstring") String book,@RequestParam("image") MultipartFile file) throws JsonMappingException, JsonProcessingException {

		if (file.isEmpty()) {

		}

		try {
			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths
					.get(UPLOADED_FOLDER + File.separator + "images" + File.separator + file.getOriginalFilename());
			Files.write(path, bytes);
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	Book bookobj=new ObjectMapper().readValue(book, Book.class);
	bookobj.setLogo(file.getOriginalFilename());
		return authorService.addBook(bookobj);
	}
	
	@GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam("image") String image) throws IOException {
     //   File file = new File(SERVER_LOCATION + File.separator + image + EXTENSION);
        File file=new File(UPLOADED_FOLDER+ File.separator + "images" +File.separator + image);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=img.jpg");
        header.add("Cache-Control", "no-cache, no-store, must-revalidate");
        header.add("Pragma", "no-cache");
        header.add("Expires", "0");

        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

        return ResponseEntity.ok()
                .headers(header)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

	
	
	

	@GetMapping("/books")
	public List<Book> getAllBook() {
		return authorService.getAllBook();
	}

	@GetMapping("/bookbyid/{id}")
	public Optional<Book> getBookById(@PathVariable Integer id) {
		
		System.out.println("inside author");
		Optional<Book> book = authorService.getBookById(id);
		System.out.println("Returing author");
		return book;
	}

	@DeleteMapping("/book/{bid}")
	public ResponseEntity<Book> deleteStudent(@PathVariable Integer bid) {

		System.out.println(bid);
		ResponseEntity<Book> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		try {
			authorService.deleteBook(bid);
			;
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return responseEntity;

	}
	
	@PutMapping("/updatebook/{id}")
	public ResponseEntity<Book> updateEmployee(@PathVariable("id") Integer id , @RequestBody Book book)
	{
		System.out.println(authorService.updateBook(book, id));
		return new ResponseEntity<Book>(authorService.updateBook(book, id),HttpStatus.OK);
		
	}
	
	@GetMapping("/Book/category/{category}")
	public List<Book> getBookByCategory(@PathVariable String category) {
		
		
		List<Book> book = authorService.getBookByCategory(category);
		
		return book;
	}
	
	@GetMapping("/Book/author/{author}")
	public List<Book> getBookByAuthor(@PathVariable String author) {
		
		
		List<Book> book = authorService.getBookByAuthor(author);
		
		return book;
	}
	
	@GetMapping("/Book/price/{price}")
	public List<Book> getBookByPrice(@PathVariable Double price) {
		
		System.out.println("inside author");
		List<Book> book = authorService.getBookByprice(price);
		System.out.println("Returing author");
		return book;
	}
	
	@GetMapping("/Book/pricecategory/{price}/{category}")
	public List<Book> getBookByPriceCategory
	(@PathVariable("price") Double price, @PathVariable("category") String category) {
		
		System.out.println("inside author");
		List<Book> book = authorService.getBookByPriceCategory(category, price);
		System.out.println("Returing author");
		return book;
	}
	
	@GetMapping("/totalrevenue/{author}")
	public Double totalRevenue(@PathVariable String author)
	{
		
		return authorService.getTotalRevenue(author);
	}
	
	@GetMapping("bookByAuthorId/{authorId}")
	public List<Book> getBookByAuthorId	(@PathVariable("authorId") Integer authorId) {
		
		System.out.println("inside author");
		List<Book> book = authorService.getBookByAuthorId(authorId);
		System.out.println("Returing author");
		return book;
	}
	
	

}
