package com.equifax.library.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.equifax.library.dto.UserDTO;
import com.equifax.library.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
@WithMockUser
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;
	
	//UserDTO mockUserDTO = new UserDTO(99,"Rintu","Admin","Active");
	
	String testUserJson = "{\"userName\":\"Wolverin\",\"userRole\":\"Admin\",\"userStatus\":\"Active\"}";
	
	@Test
	public void addUser() {
		UserDTO mockUserDTO = new UserDTO(99,"Wolverin","Admin","Active");
		String sucess = "User added successfully";
		
		Mockito.when(
				userService.authenticateUser(Mockito.anyInt())).thenReturn(true);
		
		Mockito.when(
				userService.validateUser(Mockito.any(UserDTO.class))).thenReturn("Success");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/addUser").header("verifyUser", 2)
				.accept(MediaType.APPLICATION_JSON).content(testUserJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();

			System.err.println("Response User Add::"+response.getContentAsString());
			//assertEquals(HttpStatus.CREATED.value(), response.getStatus());
			assertEquals(sucess,response.getContentAsString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void deleteUser() {
		String success="Rintu deleted";
		
		UserDTO mockUserDTO = new UserDTO(4,"Rintu","Admin","Active");
		
		Mockito.when(
				userService.authenticateUser(Mockito.anyInt())).thenReturn(true);
		
		Mockito.when(
				userService.deleteUser(Mockito.anyInt())).thenReturn(mockUserDTO.getUserName() + " deleted");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/deleteUser/4").header("verifyUser", 2)
				.accept(MediaType.APPLICATION_JSON);
				//.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			System.err.println("Response User Delete::"+response.getContentAsString());
			assertEquals(success,response.getContentAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
     }
	
}	
	
	
	
