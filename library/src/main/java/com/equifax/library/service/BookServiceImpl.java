package com.equifax.library.service;

import java.util.List;

import java.util.Optional;


import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.equifax.library.dto.BookDTO;
import com.equifax.library.model.Book;
import com.equifax.library.model.User;
import com.equifax.library.repository.BookRepo;
import com.equifax.library.repository.UserRepository;
@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepo bookRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public Book addBook(BookDTO bookDTO) {
		Book book=createBookFromBookDTO(bookDTO);
		 return bookRepo.save(book);
	}

	@Override
	public String deleteBook(int id) {
		Book book=bookRepo.findById(id).orElse(null);
		if(book!=null)  {
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
		}else if(bookDTO.getBookStatus().equals("Claimed")&& bookDTO.getUserId()==null) {
			
				return "UserID Cannot be null when book is claimed";
		}
		else 
			return "Success";		
	}

	@Override
	public Optional<Book> getBookbyId(Integer bookId){
		Optional<Book> book=bookRepo.findById(bookId);
			return book;
	}

	public List <Book> getAllBooks(){
	return (List<Book>) bookRepo.findAll();
	}
	
	public String updateBookStatus(Integer bookId,Integer userId)
	{
		User user=userRepo.findById(userId).orElse(null);
		if(null != user) {
			Book book=bookRepo.findById(bookId).orElse(null);		
			if(null != book) {
				if(null != book.getUserId()) {
					if(book.getUserId()==userId || user.getUserRole().equalsIgnoreCase("admin")) {
						book.setUserId(null);
						book.setBookStatus("Avaliable");
						bookRepo.save(book);
						return "Book status updated successfully";
					
					}else {
						return "User don't have privilege to change the book status";
					}
				}else {
					book.setUserId(userId);
					book.setBookStatus("Unavaliable");
					bookRepo.save(book);
					return "Book status updated successfully";
				}	
				
			}else {
				return "Book not found";
			}
		}else {
			return "User not found";
		}
				
	}
}
