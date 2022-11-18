package com.author.services.signup;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.author.model.Author;

@Service
public class AuthSignUpServiceImpl implements IAuthSignupService {
	
	@Autowired
	private IAuthSignRepository authsignRepository;

	@Override
	public Integer createAuthor(Author author) {
		
		Author savingAuthor=	authsignRepository.save(author);
		System.out.println(savingAuthor);
		
		return savingAuthor.getaId();
	}

	@Override
	public Optional<Author> getAuthorById(Integer aid) {
		// TODO Auto-generated method stub
		return authsignRepository.findById(aid);
	}

	@Override
	public List<Author> getAllAuthor() {
		// TODO Auto-generated method stub
		return authsignRepository.findAll();
	}

	@Override
	public List<Author> sigin(String userName, String password) {
		System.out.println(authsignRepository.findAll());
		List<Author> login= authsignRepository.findAll().stream().
				filter(e->(e.getUserName().equals(userName))&&(e.getPassword().equals(password)))
				.collect(Collectors.toList());
		
		System.out.println(login);
		
		return login;
	}

}
