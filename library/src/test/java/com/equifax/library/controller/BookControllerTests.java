package com.equifax.library.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import com.equifax.library.model.Book;
import com.equifax.library.service.BookService;
import com.equifax.library.service.UserService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = BookController.class)
@WithMockUser
public class BookControllerTests {
	
	
	   @Autowired
	   private MockMvc mockMvc;
	   
	   @MockBean
	   private UserService userService;
	   
	   @MockBean
	   private BookService bookService;
	   
	   
	   @Test
	   public void getAllBooks() throws Exception {
		   Book book = new Book(15,"Test book","availabile",2);
		   Book book1 = new Book(16,"Mybook","Unavailabile",2);
	       List<Book> allBooks = new ArrayList<Book>();
	       allBooks.add(book);
	       allBooks.add(book1);
	       RequestBuilder requestBuilder = MockMvcRequestBuilders
					.get("/getAllBooks")
					.accept(MediaType.APPLICATION_JSON);
					
	       Mockito.when(
	    		   bookService.getAllBooks()).thenReturn(allBooks);

			try {
				//result = mockMvc.perform(requestBuilder).andReturn();
				
				
				mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].bookName", is(book.getBookName())))
				.andExpect(jsonPath("$[1].bookName", is("Mybook")));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	   
	
	 
	   }