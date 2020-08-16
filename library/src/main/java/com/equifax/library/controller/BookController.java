package com.equifax.library.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.equifax.library.dto.BookDTO;
import com.equifax.library.model.Book;
import com.equifax.library.service.BookService;
import com.equifax.library.service.UserService;

@RestController
public class BookController {
@Autowired
private BookService bookService;
@Autowired
private UserService userService;

@RequestMapping(value = "/addbook", method = RequestMethod.POST)
public String addBook(@RequestBody BookDTO bookDTO, @RequestHeader("userid") int userid) {
	if(userService.authenticateUser(userid)) {
		String validationStatus=bookService.validateBook(bookDTO);
		if(validationStatus.equals("Success")) {
			try {
				bookService.addBook(bookDTO);
				return"Successfully added to DB";
			}catch (Exception e) {
				e.printStackTrace();
				return "Exception Occured while adding the user";
			}
			
		}
		else return validationStatus;
	}
	 
     else {    
         return "Authentication FAILED : User does not have access to perform this operation";

     }
}
@RequestMapping(value="/deletebook/{bookid}",method=RequestMethod.DELETE)
public String deleteBook(@PathVariable int bookid, @RequestHeader("Verifyuser") int Verifyuser) {
	if(userService.authenticateUser(Verifyuser)) {
		try {
	return bookService.deleteBook(bookid);
	}catch(Exception e) {
		e.printStackTrace();
		return "Some exception occured while deleting from DB";
	}
}
	else 
		return "Authentication FAILED : User does not have access to perform this operation";

}
}
