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

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

	@InjectMocks
	private BookServiceImpl bookService;

	@Mock
	private BookRepo bookRepository;
	
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
		
	Book book = new Book(15,"Test book","Available",2);
		
		Mockito.when(bookRepository.findById(book.getBookId())).thenReturn(java.util.Optional.of(book));
		try {
			Optional<Book> book3 = bookService.getBookId(book.getBookId());
			Integer bookIdValue = book3.get().getBookId();
			assertEquals(bookIdValue,book.getBookId());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}


}




	
//	@Test
//	public void shouldDeleteBookSuccessfullyFail() {
//		Book book3=null;
//		Mockito.when(bookRepository.findById(Mockito.anyInt())).thenReturn(java.util.Optional.of(book3));
//		System.err.println(book3.getBookName());
//		bookService.deleteBook(book1.getBookId());
//		verify(bookRepository, times(0)).deleteById(book1.getBookId());
//		
//	}
	
	@Test
	public void shouldGetBookbyId() {
		Mockito.when(bookRepository.findById(book1.getBookId())).thenReturn(java.util.Optional.of(book1));
		try {
			Optional<Book> book3 = bookService.getBookbyId(book1.getBookId());
			Integer bookIdValue = book3.get().getBookId();
			assertEquals(bookIdValue,book1.getBookId());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void shouldReturnAllBooks() {
		Mockito.when(bookRepository.findAll()).thenReturn(books);
		try {
			List<Book> booklist = bookService.getAllBooks();
			assertEquals(books,booklist);
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
}

