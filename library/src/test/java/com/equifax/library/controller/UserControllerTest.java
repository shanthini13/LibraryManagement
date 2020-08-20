package com.equifax.library.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.equifax.library.dto.UserDTO;
import com.equifax.library.model.User;
import com.equifax.library.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
	@InjectMocks
	private UserController userController;
	@Mock
	private UserService userService;
	
	UserDTO userDTO;
	User user;
	
	private MockMvc mockMvc;
	String testUserJson = "{\"userName\":\"Wolverin\",\"userRole\":\"Admin\",\"userStatus\":\"Active\"}";
	@BeforeEach
	public void setup() {
		userDTO = new UserDTO(4,"Ann Frank","admin","Active");
		user = new User(4,"Ann Frank","admin","Active");
		JacksonTester.initFields(this, new ObjectMapper());
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
	@Test
	public void addUser() {
		String sucess = "User added successfully";
		
		Mockito.when(
				userService.authenticateUser(Mockito.anyInt())).thenReturn(true);
		
		Mockito.when(
				userService.validateUser(Mockito.any(UserDTO.class))).thenReturn("Success");
		Mockito.when(
				userService.addUser(Mockito.any(UserDTO.class))).thenReturn("User added successfully");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/addUser").header("verifyUser", 2)
				.accept(MediaType.APPLICATION_JSON).content(testUserJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result;
		try {
			result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			assertEquals(sucess,response.getContentAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void deleteUser() {
		String success="Ann Frank deleted";
		
		Mockito.when(
				userService.authenticateUser(Mockito.anyInt())).thenReturn(true);
		
		Mockito.when(
				userService.deleteUser(Mockito.anyInt())).thenReturn(userDTO.getUserName() + " deleted");
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/deleteUser/4").header("verifyUser", 2)
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
	public void updateUser() {
		
		String success = "User status updated Successfully";
		Mockito.when(
				userService.authenticateUser(Mockito.anyInt())).thenReturn(true);
		Mockito.when(
				userService.updateUser(Mockito.anyInt(),Mockito.anyString())).thenReturn("User status updated Successfully");
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/updateUser/4").header("UserId", 2)
				.header("UserStatus", "Inactive")
				.accept(MediaType.APPLICATION_JSON);
		try {
			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			assertEquals(success,response.getContentAsString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}	