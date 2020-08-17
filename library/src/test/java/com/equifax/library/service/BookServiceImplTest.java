package com.equifax.library.service;

import com.equifax.library.model.Book;
import com.equifax.library.repository.BookRepo;
import com.equifax.library.service.BookService;
import com.equifax.library.service.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.aspectj.lang.annotation.Before;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepo bookRepository;
    
   
//
//    @Test
//    public void shouldSaveBookSuccessfully() {
//        final Book book = new Book();
//        book.setBookId(4);
//        book.setBookName("abc");
//        book.setBookStatus("sucess");
//        given(bookRepository.save(book))
//                .willReturn(book);
//        Book book2 =bookService.addBook(book);
//        assertEquals( book2.getBookId(),4);
//
//    }


    @Test
    public void shouldDeleteBookSuccessfully() {
        final Book book = new Book();
        book.setBookId(4);
        book.setBookName("abc");
        book.setBookStatus("sucess");
        bookService.deleteBook(book.getBookId());
        verify(bookRepository,times(1)).deleteById(book.getBookId());

    }

}

//	    @Test
//	    public void shouldSaveBookSuccessfully() {
//	        final BookDTO book = new BookDTO();
//	        book.setBookId(4);
//	        book.setBookName("Shanthini");
//	        book.setBookStatus("Available");
//	        final Book book1=new Book();
//	        book1.setBookId(book.getBookId());
//	        book1.setBookName(book.getBookName());
//	        book1.setBookStatus(book.getBookStatus());
//	        given(bookRepository.save(book1))
//	                .willReturn(book1);
//	        Book book2 =bookService.addBook(book);
//	        assertEquals( book2.getBookId(),4);
//
//	    }
//
//
//	    @Test
//	    public void shouldDeleteBookSuccessfully() {
//	        final Book book = new Book();
//	        book.setBookId(4);
//	        book.setBookName("Shanthini");
//	        book.setBookStatus("Available");
//	        bookService.deleteBook(book.getBookId());
//	        verify(bookRepository,times(1)).deleteById(book.getBookId());
//
//	    }
