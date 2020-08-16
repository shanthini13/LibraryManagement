package com.equifax.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.equifax.library.model.Book;
import com.equifax.library.service.BookService;

@RestController
public class BookController {
@Autowired
private BookService bookService;

@RequestMapping(value="/addbook",method=RequestMethod.POST)
public String addBook(@RequestBody Book book) {
	Book book1 = bookService.addBook(book);
	 if(book1!=null) { 
    	 return "SUCESSFULLY ADDED TO DATABASE";
}
     else {    
         return "INSERT OPERATION FAILED";

     }
}
@RequestMapping(value="/deletebook/{id}",method=RequestMethod.DELETE)
public String deleteBook(@PathVariable int id) {
	bookService.deleteBook(id);
	return "SUCESSFULLY DELETED FROM DATABASE";

}

@GetMapping(value="/getAllBooks")
public List <Book> getAllBooks(){
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



}
