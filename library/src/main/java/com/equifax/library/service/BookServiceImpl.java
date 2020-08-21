package com.equifax.library.service;

import java.util.ArrayList;
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
		Book book = createBookFromBookDTO(bookDTO);
		return bookRepo.save(book);
	}

	@Override
	public String deleteBook(int id) {
		Book book = bookRepo.findById(id).orElse(null);
		if (book != null) {
			bookRepo.deleteById(id);
			return "Successfully deleted book with id : " + id;
		} else
			return "Book not found";
	}

	@Override
	public Optional<Book> getBookbyId(Integer bookId) {
		Optional<Book> book = bookRepo.findById(bookId);
		return book;
	}
  
 
	public ArrayList<BookDTO> getAllBooks(){
		ArrayList<Book> booklist=new ArrayList<Book>();
		ArrayList<BookDTO> bookDTOS=new ArrayList<BookDTO>();
		
		booklist=(ArrayList<Book>) bookRepo.findAll();
		for(Book book:booklist) {
			
			BookDTO bookDTO=createBookDTOFromBook(book);
			bookDTOS.add(bookDTO);
		}
		
	return bookDTOS; 
	}


	public String updateBookStatus(Integer bookId, Integer userId) {
		User user = userRepo.findById(userId).orElse(null);
		if (null != user) {
			Book book = bookRepo.findById(bookId).orElse(null);
			if (null != book) {
				if (null != book.getUserId()) {
					if (book.getUserId() == userId || user.getUserRole().equalsIgnoreCase("admin")) {
						book.setUserId(null);
						book.setBookStatus("Avaliable");
						bookRepo.save(book);
						return "Book status updated successfully";

					} else {
						return "User don't have privilege to change the book status";
					}
				} else {
					book.setUserId(userId);
					book.setBookStatus("Unavaliable");
					bookRepo.save(book);
					return "Book status updated successfully";
				}

			} else {
				return "Book not found";
			}
		} else {
			return "User not found";
		}

	}
  
  
  
  
	private Book createBookFromBookDTO(BookDTO bookDTO) {
		Book book = new Book();
		book.setBookName(bookDTO.getBookName());
		book.setBookStatus(bookDTO.getBookStatus());
		book.setUserId(bookDTO.getUserId());
		return book;
	}

	
	private BookDTO createBookDTOFromBook(Book book) {
		BookDTO bookDTO=new BookDTO();
		bookDTO.setBookId(book.getBookId());
		bookDTO.setBookName(book.getBookName());
		bookDTO.setBookStatus(book.getBookStatus());
		bookDTO.setUserId(book.getUserId());
		return bookDTO;
	}
	
	public String validateBook(BookDTO bookDTO) {
		Book book = (Book) bookRepo.findByBookName(bookDTO.getBookName());
		if (StringUtils.isBlank(bookDTO.getBookName())) {
			return "Book name cannot be empty";
		}else if(book!=null) {
			return "Book Already present in DB";
		}
		else if (StringUtils.isBlank(bookDTO.getBookStatus())) {
			return "Book status cannot be empty";
		} else if (bookDTO.getBookStatus().equals("Available")&& bookDTO.getUserId() != null) {
			return "UserID Cannot be present when book is Available";
		} else if (bookDTO.getBookStatus().equals("Unavaliable") && bookDTO.getUserId() == null) {

			return "UserID Cannot be null when book is claimed";
		} else
			return "Success";

	}

}
