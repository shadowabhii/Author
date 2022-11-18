package com.author.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.author.model.Book;

public interface IAuthorRepository extends JpaRepository<Book, Integer> {

}
