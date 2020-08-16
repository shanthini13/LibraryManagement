package com.equifax.library.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.equifax.library.model.Book;

public interface BookRepo extends CrudRepository<Book, Integer> {

	List<Book> findAllByBookName(String bookname);

}
