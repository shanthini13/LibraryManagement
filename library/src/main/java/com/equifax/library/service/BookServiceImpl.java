package com.equifax.library.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Book addBook(Book book) {

		 return bookRepo.save(book);
	}

	@Override
	public void deleteBook(int id) {
		bookRepo.deleteById(id);
	}

	public List <Book> getAllBooks(){
	return (List<Book>) bookRepo.findAll();
	}
	
	public String updateBookStatus(Integer bookId,Integer userId)
	{
		System.err.println("Inside update book:: userId ::"+userId+" BookId::"+bookId);
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
