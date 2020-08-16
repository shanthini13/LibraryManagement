package com.equifax.library.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equifax.library.dto.BookDTO;
import com.equifax.library.model.Book;
import com.equifax.library.repository.BookRepo;
@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo bookRepo;

	@Override
	public Book addBook(BookDTO bookDTO) {
		Book book=createBookFromBookDTO(bookDTO);
		 return bookRepo.save(book);
	}

	@Override
	public String deleteBook(int id) {
		Book book=bookRepo.findById(id).orElse(null);
		if(book!=null) {
			bookRepo.deleteById(id);
			return "Successfully deleted book with id : "+id;
		}
		else
			return "Book not found";
	}
	
	private Book createBookFromBookDTO(BookDTO bookDTO) {
		Book book = new Book();
		book.setBookName(bookDTO.getBookName());
		book.setBookStatus(bookDTO.getBookStatus());
		book.setUserId(bookDTO.getUserId());
		return book;
	}
	
	public String validateBook(BookDTO bookDTO) {
		if(StringUtils.isBlank(bookDTO.getBookName())) {
			return "Book name cannot be empty";
		}else if(StringUtils.isBlank(bookDTO.getBookStatus()))
		{
			return "Book status cannot be empty";
		}else if(bookDTO.getBookStatus().equals("Claimed")&& bookDTO.getUserId()==0) {
			
				return "UserID Cannot be null when book is claimed";
		}
		else 
			return "Success";		
	}

	@Override
	public Book getBookId(int id) {
		Book book=bookRepo.findById(id).orElse(null);
		if(book!=null) {
			return book;
		}

		return null;
		
	}

}
