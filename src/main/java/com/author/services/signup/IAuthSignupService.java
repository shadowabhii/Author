package com.author.services.signup;

import java.util.List;
import java.util.Optional;

import com.author.model.Author;

public interface IAuthSignupService {
	
	Integer createAuthor(Author author);
	public Optional<Author> getAuthorById(Integer aid);
	public List<Author> getAllAuthor();
	public List<Author> sigin(String userName , String password);

}
