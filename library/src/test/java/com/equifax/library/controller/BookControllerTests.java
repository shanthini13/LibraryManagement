package com.equifax.library.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.equifax.library.dto.BookDTO;
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
	   
	   String exampleCourseJson = "{\"bookName\":\"Ann Frank\",\"BookStatus\":\"Available\",\"userId\":\"1\"}";
	   
	   
	   @Test
	   public void getAllBooks() throws Exception {
		   BookDTO book = new BookDTO(16,"Mybook","Unavailabile",2);  
	       ArrayList<BookDTO> allBooks = new ArrayList<BookDTO>();
	       allBooks.add(book);
	       RequestBuilder requestBuilder = MockMvcRequestBuilders
					.get("/getAllBooks")
					.accept(MediaType.APPLICATION_JSON);
					
	       Mockito.when(
	    		   bookService.getAllBooks()).thenReturn(allBooks);

			try {
				mockMvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].bookName", is(book.getBookName())));
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	   
	   @Test
	   public void updateBookStatus() {
		   
		   String success = "Success";
		   
		   Mockito.when(
	    		   bookService.updateBookStatus(Mockito.anyInt(),Mockito.anyInt())).thenReturn("Success");
		   
		   RequestBuilder requestBuilder = MockMvcRequestBuilders
					.put("/updateBookStatus").header("userId", 2).header("bookId", 5)
					.accept(MediaType.APPLICATION_JSON);
		   
		   MvcResult result;
			try {
				result = mockMvc.perform(requestBuilder).andReturn();
				MockHttpServletResponse response = result.getResponse();
				assertEquals(success,response.getContentAsString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
	     }
	   
	   
	   
	   @Test
		public void addBook() throws Exception {
		   
		   Book book = new Book(15,"Test book","availabile",2);

			Mockito.when(bookService.addBook(Mockito.any(BookDTO.class))).thenReturn(book);
			Mockito.when(bookService.validateBook(Mockito.any(BookDTO.class))).thenReturn("Success");
			Mockito.when(userService.authenticateUser(Mockito.anyInt())).thenReturn(true);
			RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addbook").header("userid", 1)
					.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson).contentType(MediaType.APPLICATION_JSON);
			try {
				MvcResult result = mockMvc.perform(requestBuilder).andReturn();
				MockHttpServletResponse response = result.getResponse();
				assertEquals(200, response.getStatus());
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	   
	   
	   @Test
		public void getBookById() throws Exception {
		   
		   Book book = new Book(15,"Test book","availabile",2);
		   
			Mockito.when(bookService.getBookId(Mockito.anyInt())).thenReturn(java.util.Optional.of(book));
			RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/books/15").accept(MediaType.APPLICATION_JSON);

			try {

				MvcResult result = mockMvc.perform(requestBuilder).andReturn();
				MockHttpServletResponse response = result.getResponse();
				assertEquals(200, response.getStatus());

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	   
	   @Test
		public void deleteBook() throws Exception {
			Mockito.when(userService.authenticateUser(Mockito.anyInt())).thenReturn(true);
			Mockito.when(bookService.deleteBook(Mockito.anyInt())).thenReturn("Successfully deleted book");
			RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/deletebook/4").header("Verifyuser", 2)
					.accept(MediaType.APPLICATION_JSON);
			try {

				MvcResult result = mockMvc.perform(requestBuilder).andReturn();
				MockHttpServletResponse response = result.getResponse();
				assertEquals(200, response.getStatus());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	   	 
	  }