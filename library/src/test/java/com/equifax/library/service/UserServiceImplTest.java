package com.equifax.library.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.equifax.library.dto.UserDTO;
import com.equifax.library.model.Book;
import com.equifax.library.model.User;
import com.equifax.library.repository.BookRepo;
import com.equifax.library.repository.UserRepository;
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userimpl;
	
	@Mock
	private UserRepository userRepo;
	@Mock
	private BookRepo bookRepo;
	
	UserDTO userDTO;
	User user;
	
	@BeforeEach
	public void setup() {
		userDTO = new UserDTO(4,"Ann Frank","admin","Active","annmary9@gmail.com");
		user = new User(4,"Ann Frank","admin","Active","shanp@gmail.com");
	}
	@Test 
	public void updateUser() {
		Mockito.when(userRepo.findById(Mockito.anyInt())).thenReturn(java.util.Optional.of(user));
		Mockito.when(userRepo.save(user)).thenReturn(user);
		try {
			String result=userimpl.updateUser(4,"Active");
			assertEquals("User status updated Successfully",result);
		}catch (Exception e) {
			e.printStackTrace();
		}		
	}	
	@Test 
	public void addUsersuccessfully() {		
		Mockito.when(userRepo.save(any(User.class))).thenReturn(user);
		try {
			String result =userimpl.addUser(userDTO);
			assertEquals("User Added Successfully",result);
		}catch (Exception e) {
			e.printStackTrace();
		}			
	}	
	@Test 
	public void deleteUsersuccessfully() {
		Mockito.when(userRepo.findById(Mockito.anyInt())).thenReturn(java.util.Optional.of(user));
		Mockito.when(bookRepo.updateUserStatusForBooks(Mockito.anyInt())).thenReturn(Mockito.anyInt());
		try {
			userimpl.deleteUser(user.getUserId());
			verify(userRepo, times(1)).delete(user);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void shouldReturnAllUsers() {
	User user=new User(15,"Test","User","Active","Admin@gmail.com");
	List<User> list=new ArrayList<User>();
	   list.add(user);
	   
	ArrayList<UserDTO> newList = new ArrayList<UserDTO>();
	Mockito.when(userRepo.findAll()).thenReturn((list));
	   
	  newList= userimpl.getAlUsers();
	  assertEquals(newList.get(0).getUserId(),list.get(0).getUserId());
	}	
	
	@Test 
	public void authUsersuccessfully() {		
		Mockito.when(userRepo.findById(Mockito.anyInt())).thenReturn(java.util.Optional.of(user));
		try {
			Boolean result =userimpl.authenticateUser(user.getUserId());
			assertEquals(true,result);
		}catch (Exception e) {
			e.printStackTrace();
		}			
	}	
	
	@Test 
	public void validateUser() {
		userDTO =new UserDTO();
		userDTO.setUserRole("admin");
		String success= "User name cannot be empty";
		try {
			String result =userimpl.validateUser(userDTO);
			assertEquals(success,result);
		}catch (Exception e) {
			e.printStackTrace();
		}			
	}	
	
	@Test 
	public void validateUsernoRole() {
		userDTO =new UserDTO();
		userDTO.setUserName("Shanthini");
		String success= "User role cannot be empty";
		try {
			String result =userimpl.validateUser(userDTO);
			assertEquals(success,result);
		}catch (Exception e) {
			e.printStackTrace();
		}			
	}	
	
	@Test 
	public void validateUsernoStatus() {
		userDTO =new UserDTO();
		userDTO.setUserName("Shanthini");
		userDTO.setUserRole("admin");
		String success= "User status cannot be empty";
		try {
			String result =userimpl.validateUser(userDTO);
			assertEquals(success,result);
		}catch (Exception e) {
			e.printStackTrace();
		}			
	}	
	
	@Test 
	public void validateUsernoMail() {
		userDTO =new UserDTO();
		userDTO.setUserName("Shanthini");
		userDTO.setUserRole("admin");
		userDTO.setUserStatus("Active");
		String success= "Mail_Id cannot be empty";
		try {
			String result =userimpl.validateUser(userDTO);
			assertEquals(success,result);
		}catch (Exception e) {
			e.printStackTrace();
		}			
	}	
	
	@Test 
	public void validateExistingUser() {
		String success= "User Already Exists";
		Mockito.when(userRepo.findByMailId(Mockito.anyString())).thenReturn(user);

		try {
			String result =userimpl.validateUser(userDTO);
			assertEquals(success,result);
		}catch (Exception e) {
			e.printStackTrace();
		}			
	}	
	
}
