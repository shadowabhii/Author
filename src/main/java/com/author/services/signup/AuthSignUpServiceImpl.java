package com.author.services.signup;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.author.model.Author;

import ch.qos.logback.core.encoder.Encoder;

@Service
public class AuthSignUpServiceImpl implements IAuthSignupService {
	
	@Autowired
	private IAuthSignRepository authsignRepository;
	
	/*
	 * @Autowired private PasswordEncoder passwordEncoder;
	 */
	
	@Bean
	public BCryptPasswordEncoder passwordEncode()
	{
		return new BCryptPasswordEncoder();
	}

	@Override
	public Integer createAuthor(Author author) {

		
		  String encodePass=passwordEncode().encode(author.getPassword());
		  author.setPassword(encodePass);
		 
		
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
		
		BCryptPasswordEncoder passdecode = new BCryptPasswordEncoder();
		//passdecode.matches(password, authsignRepository)
		
		System.out.println(authsignRepository.findAll());
		
		List<Author> user= authsignRepository.findAll().stream().
				filter(e->(e.getUserName().equals(userName)))
				.collect(Collectors.toList());
		
		if(passdecode.matches(password, user.get(0).getPassword()))
		{
			return user;
		}
		return null;
		
		/*
		 * List<Author> login= authsignRepository.findAll().stream().
		 * filter(e->(e.getUserName().equals(userName))&&(passdecode.matches(password,e.
		 * getPassword()))) .collect(Collectors.toList());
		 */
		
		
		
		
	}

}
