package com.equifax.library.service;

import com.equifax.library.dto.BookDTO;
import com.equifax.library.model.Book;
import com.equifax.library.model.User;
import com.equifax.library.repository.BookRepo;
import com.equifax.library.repository.UserRepository;
import com.equifax.library.service.BookServiceImpl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Optional;


@RunWith(SpringRunner.class)
public class BookServiceImplTest {

	@InjectMocks
    private BookServiceImpl bookService;
	
	@Mock
	private UserRepository userRepo;

    @Mock
    private BookRepo bookRepository;
    
    
    @Test
    public void shouldReturnAllBooksSuccessully() {
    	  Book book = new Book(16,"Mybook","Unavailabile",2);
    	  ArrayList<Book> list = new ArrayList<Book>();
    	  list.add(book);
    	  ArrayList<BookDTO> bookList = new ArrayList<BookDTO>();
    	
    	 Mockito.when(bookRepository.findAll()).thenReturn((list));
    	 bookList= bookService.getAllBooks();
    	 assertThat(bookList.get(0).getBookName()).isEqualTo(list.get(0).getBookName()); 	
    }
    
    
    
    @Test
    public void shouldUpdateBookStatusSuccessfully(){
    	String successString="Book status updated successfully";	
    	User user=new User(2,"Test","User","Active","Admin@gmail.com");
    	Book book = new Book(15,"Test book","Available",2);
    	
	   Mockito.when(bookRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(book)); 
	   
	   Mockito.when(userRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
	   
	   Mockito.when(bookRepository.save(book)).thenReturn(book);
	   
	   String status= bookService.updateBookStatus(book.getUserId(), user.getUserId());
	   
	   assertThat(status).isEqualTo(successString); 	
	    
    	
    }
    
    
    

    
    @Test
	public void shouldSaveBookSuccessfully() {
    BookDTO	bookDTO = new BookDTO(15,"Test book","Available",2);
    Book book = new Book(15,"Test book","Available",2);
    	
		Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
		try {
			Book book1 = bookService.addBook(bookDTO);
			assertThat(book1.equals(book));
		}catch (Exception e) {
			e.printStackTrace();
		}

	}

    
    @Test
	public void shouldDeleteBookSuccessfully() {
    Book book = new Book(15,"Test book","Available",2);
    	
		Mockito.when(bookRepository.findById(book.getBookId())).thenReturn(java.util.Optional.of(book));
		try {
			bookService.deleteBook(book.getBookId());
			verify(bookRepository, times(1)).deleteById(book.getBookId());
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
















