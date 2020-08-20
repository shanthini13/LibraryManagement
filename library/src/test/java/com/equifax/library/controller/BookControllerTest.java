package com.equifax.library.controller;

import com.equifax.library.controller.BookController;
import com.equifax.library.dto.BookDTO;
import com.equifax.library.model.Book;
import com.equifax.library.service.BookService;
import com.equifax.library.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {

	@InjectMocks
	private BookController bookController;

	String exampleCourseJson = "{\"bookName\":\"Ann Frank\",\"BookStatus\":\"Available\",\"userId\":\"1\"}";

	BookDTO bookDTO;
	Book book;
	List<Book> allBooks;
	Optional<Book> book3;
	@Mock
	private BookService bookService;
	@Mock
	private UserService userService;
	
	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		bookDTO = new BookDTO(4,"Ann Frank","Available",1);
		book = new Book(4,"Ann Frank","Available",1);
		
		allBooks = new ArrayList<Book>();
		allBooks.add(book);
		Book book1 = new Book(16,"Mybook","Unavailabile",2);
		allBooks.add(book1);
		JacksonTester.initFields(this, new ObjectMapper());
		mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}

	@Test
	void successfyllyAdduser() throws Exception {

		Mockito.when(bookService.addBook(Mockito.any(BookDTO.class))).thenReturn(book);
		Mockito.when(bookService.validateBook(Mockito.any(BookDTO.class))).thenReturn("Success");
		Mockito.when(userService.authenticateUser(Mockito.anyInt())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addbook").header("userid", 1)
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson).contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			assertEquals(201, response.getStatus());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void AdduserAuthFail() throws Exception {

		Mockito.when(userService.authenticateUser(Mockito.anyInt())).thenReturn(false);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addbook").header("userid", 1)
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson).contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			assertEquals(401, response.getStatus());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void AdduserValidationFail() throws Exception {
		Mockito.when(userService.authenticateUser(Mockito.anyInt())).thenReturn(true);
		Mockito.when(bookService.validateBook(Mockito.any(BookDTO.class))).thenReturn("Failed validation");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addbook").header("userid", 1)
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson).contentType(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			assertEquals(206, response.getStatus());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	   public void getAllBooks() throws Exception {
		   RequestBuilder requestBuilder = MockMvcRequestBuilders
					.get("/getAllBooks")
					.accept(MediaType.APPLICATION_JSON);
			Mockito.when(bookService.getAllBooks()).thenReturn(allBooks);
			try {	

				MvcResult result = mockMvc.perform(requestBuilder).andReturn();
				MockHttpServletResponse response = result.getResponse();
				assertEquals(200, response.getStatus());
			} catch (Exception e) {
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
				e.printStackTrace();
			}
					
	     }
	   
	@Test
	public void getBookById() throws Exception {
		Mockito.when(bookService.getBookbyId(Mockito.anyInt())).thenReturn(java.util.Optional.of(book));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/books/1").accept(MediaType.APPLICATION_JSON);

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

