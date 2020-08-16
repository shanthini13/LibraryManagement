package com.equifax.library.repository;

import org.springframework.data.repository.CrudRepository;

import com.equifax.library.model.Book;

public interface BookRepo extends CrudRepository<Book, Integer> {

//	Book findByBookName(String bookName);

	//Book findAllByBookName(String bookName);

	

}
