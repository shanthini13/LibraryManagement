package com.equifax.library.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equifax.library.model.Book;
import com.equifax.library.repository.BookRepo;
import com.equifax.library.repository.UserRepository;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo bookRepo;

//	@Autowired
//	private UserService userService;

	@Override
	public Book addBook(Book book) {

		return bookRepo.save(book);

	}

	@Override
	public String deleteBook(int bookid, int verifyuser) {

		bookRepo.deleteById(bookid);
		return "book with id :" + bookid + " is deleted";
	}

}
