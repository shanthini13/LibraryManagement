package com.equifax.library.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.equifax.library.model.Book;
import com.equifax.library.repository.BookRepo;

//@RunWith(SpringRunner.class)
public class BookServiceImplTest {
	private MockMvc mockMvc;
	@Autowired
	private BookRepo bookRepo;
//	@Mock
//	private UserService userService;
	@InjectMocks
	private BookServiceImpl bookServiceImpl;

	//private BookRepo bookRepo;
//	
//	@Before
//	public void setUp() {
//		
//		Book book = new Book();
//		book.setId(1);
//		book.setBookName("My Book");
//		book.setBookStatus("Available");
//		book.setUserDetails("UserId");
//		mockMvc = MockMvcBuilders.standaloneSetup(bookServiceImpl).build();
//		Mockito.when(bookRepo.save(book)).thenReturn(book);
//	}
	
	@Test
	void test() {
		Book book = new Book();
		Mockito.when(bookRepo.save(book)).thenReturn(book);
		Book book1 = new Book();
		book1.setId(1);
		book1.setBookName("My Book");
		book1.setBookStatus("Available");
		book1.setUserDetails("UserId");
		
		
		Book book2= bookRepo.save(book1);
		assertEquals(book2.getBookName(),"My Book");
	}

}
