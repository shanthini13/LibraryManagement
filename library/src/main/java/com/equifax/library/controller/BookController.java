package com.equifax.library.controller;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.equifax.library.dto.BookDTO;
import com.equifax.library.model.Book;
import com.equifax.library.service.BookService;
import com.equifax.library.service.UserService;


@SuppressWarnings("unchecked")
@RestController
public class BookController {
	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;
	
	

	@GetMapping(value="/getAllBooks")
	public ArrayList <BookDTO> getAllBooks(){
	return bookService.getAllBooks();
		
	}
	

	@PutMapping(value="/updateBookStatus")
	public String updateBookStatus(@RequestHeader(name="userId") Integer userId,@RequestHeader(name="bookId") Integer bookId) {
		if(null != userId  && null != bookId) {
		String bookStatus=bookService.updateBookStatus(bookId,userId);
		return bookStatus;
		}
		else {
			return "UserId and BookId cannot be null";
		}
	}
	
	

	@RequestMapping(value = "/addbook", method = RequestMethod.POST)
	public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO, @RequestHeader("userid") int userid) {
		JSONObject obj = new JSONObject();
		if (userService.authenticateUser(userid)) {
			String validationStatus = bookService.validateBook(bookDTO);
			if (validationStatus.equals("Success")) {
				try {
					bookService.addBook(bookDTO);
					obj.put("status", "True");
					obj.put("Message", "Successfully added book to DB");
					return new ResponseEntity<Object>(obj, HttpStatus.OK);

				} catch (Exception e) {
					e.printStackTrace();
					obj.put("status", "False");
					obj.put("Message", "Exception Occured while adding the book");
					return new ResponseEntity<Object>(obj, HttpStatus.OK);
				}
			} else
		    obj.put("status", "False");
			obj.put("Message", validationStatus);
			return new ResponseEntity<Object>(obj, HttpStatus.OK);

		} else {
			obj.put("status", "False");
			obj.put("Message", "EAuthentication FAILED : User does not have access to perform this operation");
			return new ResponseEntity<Object>(obj, HttpStatus.OK);


		}
	}


	@RequestMapping("/books/{bookId}")
	public ResponseEntity<?> bookId(@PathVariable Integer bookId) {
		JSONObject obj = new JSONObject();
		try {
		Optional<Book> book=bookService.getBookId(bookId);
		if(book.isPresent()) {
			obj.put("status", "True");
			obj.put("Message", book);
			return new ResponseEntity<Object>(obj, HttpStatus.OK);
		}else
		{
		obj.put("status", "False");
		obj.put("Message", "Book with given BookName not found");
		return new ResponseEntity<Object>(obj, HttpStatus.BAD_REQUEST);
		}
		}catch (Exception e) {
			e.printStackTrace();
			obj.put("status", "False");
			obj.put("Message", "Exception Occured while fetching the book details");
			return new ResponseEntity<Object>(obj, HttpStatus.OK);
		}
	}


	@RequestMapping(value = "/deletebook/{bookid}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBook(@PathVariable int bookid, @RequestHeader("Verifyuser") int Verifyuser) {
		JSONObject obj = new JSONObject();
		if (userService.authenticateUser(Verifyuser)) {
			try {
				String message = bookService.deleteBook(bookid);
				obj.put("Message", message);
				return new ResponseEntity<Object>(obj, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				obj.put("status", "False");
				obj.put("Message", "Some exception occured while deleting book from DB");
				return new ResponseEntity<Object>(obj, HttpStatus.OK);
			}
		} else
			obj.put("status", "False");
		obj.put("Message", "Authentication FAILED : User does not have access to perform this operation");
		return new ResponseEntity<Object>(obj, HttpStatus.OK);
	}
	
	
	
	
}
