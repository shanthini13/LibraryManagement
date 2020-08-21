package com.equifax.library.service;

import com.equifax.library.dto.BookDTO;
import com.equifax.library.model.Book;
import com.equifax.library.model.User;
import com.equifax.library.repository.BookRepo;
import com.equifax.library.repository.UserRepository;
import com.equifax.library.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Matchers.any;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

	@InjectMocks
	private BookServiceImpl bookService;

	@Mock
	private BookRepo bookRepository;
	
	@Mock
	private BookDTO bookDTO;
	
	@Mock
	private UserRepository userRepo;
	
	BookDTO book;
	Book book1;
	User user;
	List<Book> books;
	
	@BeforeEach
	public void setup() {
		book = new BookDTO(4,"Ann Frank","Available",1);
		book1 = new Book(4,"Ann Frank","Available",1);
		books =new ArrayList<>();
		books.add(book1);
		user= new User();
		user.setUserId(1);
		user.setUserName("Shanthini");
		user.setUserRole("admin");
		user.setUserStatus("Active");
	}

	@Test
	public void shouldSaveBookSuccessfully() {
		Mockito.when(bookRepository.save(any(Book.class))).thenReturn(book1);
		try {
			Book book2 = bookService.addBook(book);
			assertEquals(book2.getBookId(), 4);
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void shouldDeleteBookSuccessfully() {
		Mockito.when(bookRepository.findById(book1.getBookId())).thenReturn(java.util.Optional.of(book1));
		try {
			bookService.deleteBook(book1.getBookId());
			verify(bookRepository, times(1)).deleteById(book1.getBookId());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void shouldGetBookbyId() {
		Mockito.when(bookRepository.findByBookId(book1.getBookId())).thenReturn(book1);
		try {
			BookDTO bookDTO = bookService.getBookbyId(book1.getBookId());
			Integer bookIdValue = bookDTO.getBookId();
			assertEquals(bookIdValue,book1.getBookId());
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}

	@Test
	public void shouldReturnAllBooks() {
		Mockito.when(bookRepository.findAll()).thenReturn(books);
		try {
			ArrayList<BookDTO> booklist = bookService.getAllBooks();
			assertEquals(books.get(0).getBookName(),booklist.get(0).getBookName());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void shouldUpdateBookStatus() {
		Mockito.when(bookRepository.findById(4)).thenReturn(java.util.Optional.of(book1));
		Mockito.when(bookRepository.save(book1)).thenReturn(book1);
		Mockito.when(userRepo.findById(1)).thenReturn(java.util.Optional.of(user));
		try {
			String result=bookService.updateBookStatus(4, 1);
			assertEquals("Book status updated successfully",result);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void shouldUpdateBookStatustoUnavailable() {
		Book book = new Book();
		book.setBookId(4);
		book.setBookName("Dark");
		book.setBookStatus("Available");
		Mockito.when(userRepo.findById(1)).thenReturn(java.util.Optional.of(user));
		Mockito.when(bookRepository.findById(4)).thenReturn(java.util.Optional.of(book));
		Mockito.when(bookRepository.save(book)).thenReturn(book);
		try {
			String result=bookService.updateBookStatus(4, 1);
			assertEquals("Book status updated successfully",result);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test 
	public void validateBookName() {
		bookDTO =new BookDTO();
		String success= "Book name cannot be empty";
		try {
			String result =bookService.validateBook(bookDTO);
			assertEquals(success,result);
		}catch (Exception e) {
			e.printStackTrace();
		}			
	}	
	@Test 
	public void validateBookStatus() {
		bookDTO =new BookDTO();
		bookDTO.setBookName("Book1");
		String success= "Book status cannot be empty";
		try {
			String result =bookService.validateBook(bookDTO);
			assertEquals(success,result);
		}catch (Exception e) {
			e.printStackTrace();
		}			
	}	
	
	@Test 
	public void validateuseridnull() {
		bookDTO =new BookDTO();
		bookDTO.setBookName("Book1");
		bookDTO.setBookStatus("Available");
		bookDTO.setUserId(1);
		String success= "UserID Cannot be present when book is Available";
		try {
			String result =bookService.validateBook(bookDTO);
			assertEquals(success,result);
		}catch (Exception e) {
			e.printStackTrace();
		}			
	}	
	
	@Test 
	public void validateuseridNotnull() {
		bookDTO =new BookDTO();
		bookDTO.setBookName("Book1");
		bookDTO.setBookStatus("Unavaliable");
		//bookDTO.setUserId(1);
		String success= "UserID Cannot be null when book is claimed";
		try {
			String result =bookService.validateBook(bookDTO);
			assertEquals(success,result);
		}catch (Exception e) {
			e.printStackTrace();
		}			
	}	
	
	@Test 
	public void validateExistingBook() {
		String success= "Book Already present in DB";
		bookDTO =new BookDTO();
		bookDTO.setBookName("Ann Frank");
		bookDTO.setBookStatus("Available");
		Mockito.when(bookRepository.findByBookName(Mockito.anyString())).thenReturn(book1);

		try {
			String result =bookService.validateBook(bookDTO);
			assertEquals(success,result);
		}catch (Exception e) {
			e.printStackTrace();
		}			
	}	
	
}
