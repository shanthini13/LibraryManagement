package com.equifax.library.service;

import com.equifax.library.dto.BookDTO;
import com.equifax.library.model.Book;
import com.equifax.library.repository.BookRepo;
import com.equifax.library.service.BookServiceImpl;
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

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

	@InjectMocks
	private BookServiceImpl bookService;

	@Mock
	private BookRepo bookRepository;

	@Test
	public void shouldSaveBookSuccessfully() {
		final BookDTO book = new BookDTO();
		book.setBookId(4);
		book.setBookName("Ann Frank");
		book.setBookStatus("Available");
		final Book book1 = new Book();
		book1.setBookId(book.getBookId());
		book1.setBookName(book.getBookName());
		book1.setBookStatus(book.getBookStatus());
		Mockito.when(bookRepository.save(any(Book.class))).thenReturn(book1);
		Book book2 = bookService.addBook(book);
		assertEquals(book2.getBookId(), 4);

	}

	@Test
	public void shouldDeleteBookSuccessfully() {
		final Book book = new Book();
		book.setBookId(4);
		book.setBookName("Ann Frank");
		book.setBookStatus("Available");
		Mockito.when(bookRepository.findById(book.getBookId())).thenReturn(java.util.Optional.of(book));
		bookService.deleteBook(book.getBookId());
		verify(bookRepository, times(1)).deleteById(book.getBookId());

	}

}
