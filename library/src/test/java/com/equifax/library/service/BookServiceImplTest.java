package com.equifax.library.service;

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

import java.util.ArrayList;
import java.util.List;
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
    public void shouldReturnAllBooksSuccessull() {
    	List <Book> bookList = new ArrayList<Book>();
    	List <Book> list = new ArrayList<Book>();
        Book book=new Book();
        book.setBookId(12);
        book.setBookName("Test book");
        book.setBookStatus("Active");
        book.setUserId(2);
        bookList.add(book);
        
    	 Mockito.when(bookRepository.findAll()).thenReturn((bookList));
    	 list= bookService.getAllBooks();
    	 assertThat(bookList).isEqualTo(list); 	
    }
    
    
    
    @Test
    public void shouldUpdateBookStatusSuccessfully(){
    	String successString="Book status updated successfully";
    	User user=new User();
		user.setUserId(12);
		user.setUserName("Test");
		user.setUserRole("User");
		user.setUserStatus("Active");
			Book book=new Book();
		 	book.setBookId(17);
	        book.setBookName("Test book");
	        book.setBookStatus("Active");
	        book.setUserId(12);
	   Mockito.when(bookRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(book)); 
	   
	   Mockito.when(userRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
	   
	   String status= bookService.updateBookStatus(book.getUserId(), user.getUserId());
	   
	   assertThat(status).isEqualTo(successString); 	
	    
    	
    }

}
















