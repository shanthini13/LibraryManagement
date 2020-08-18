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
		Book book2=new Book(5,"Ann","Claimed",1);
		books =new ArrayList<>();
		books.add(book1);
		books.add(book2);
		
		user= new User();
		user.setUserId(1);
		user.setUserName("Shanthini");
		user.setUserRole("admin");
		user.setUserStatus("Active");
	}

	@Test
	public void shouldSaveBookSuccessfully() {
		Mockito.when(bookRepository.save(any(Book.class))).thenReturn(book1);
		Book book2 = bookService.addBook(book);
		assertEquals(book2.getBookId(), 4);

	}

	@Test
	public void shouldDeleteBookSuccessfully() {
		Mockito.when(bookRepository.findById(book1.getBookId())).thenReturn(java.util.Optional.of(book1));
		bookService.deleteBook(book1.getBookId());
		verify(bookRepository, times(1)).deleteById(book1.getBookId());

	}
	
	@Test
	public void shouldGetBookbyId() {
		Mockito.when(bookRepository.findById(book1.getBookId())).thenReturn(java.util.Optional.of(book1));
		Optional<Book> book3 = bookService.getBookbyId(book1.getBookId());
		Integer bookIdValue = book3.get().getBookId();
		assertEquals(bookIdValue,book1.getBookId());
	}

	@Test
	public void shouldReturnAllBooks() {
		Mockito.when(bookRepository.findAll()).thenReturn(books);
		List<Book> booklist = bookService.getAllBooks();
		assertEquals(books,booklist);
	}
	@Test
	public void shouldUpdateBookStatus() {
		Mockito.when(bookRepository.findById(4)).thenReturn(java.util.Optional.of(book1));
		Mockito.when(userRepo.findById(1)).thenReturn(java.util.Optional.of(user));
		String result=bookService.updateBookStatus(4, 1);
		assertEquals("Book status updated successfully",result);
	}
}
