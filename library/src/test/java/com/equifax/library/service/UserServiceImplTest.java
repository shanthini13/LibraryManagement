package com.equifax.library.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.equifax.library.dto.UserDTO;
import com.equifax.library.model.User;
import com.equifax.library.repository.BookRepo;
import com.equifax.library.repository.UserRepository;


@RunWith(SpringRunner.class)
public class UserServiceImplTest {
	
	@Mock
	private BookRepo bookRepo;
	
	@Mock
	private UserRepository userRepo;
	
	@InjectMocks
    private UserServiceImpl userService;
	
	@Test
	public void shouldaddUserSuccessfully() {
		String successString="User Added Successfully";
		UserDTO userDTO = new UserDTO(15,"Test","User","Active","Admin@gmail.com");
		User user=new User(15,"Test","User","Active","Admin@gmail.com");
	
		Mockito.when(userRepo.save(Mockito.any(User.class))).thenReturn(user);
		
		String status = userService.addUser(userDTO);
		
		assertThat(status).isEqualTo(successString);
	}
	
	@Test
	public void shouldDeleteUserSuccessFully() {
		String successString="Test deleted";
		User user=new User(15,"Test","User","Active","Admin@gmail.com");
		
		Mockito.when(userRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		
		Mockito.when(bookRepo.updateUserStatusForBooks(Mockito.anyInt())).thenReturn(Mockito.anyInt());
		
		String status = userService.deleteUser(user.getUserId());
		
		assertThat(status).isEqualTo(successString);
		verify(userRepo,times(1)).delete(user);
		
	}
	
	
	@Test 
	public void shouldUpdateUserSuccessfully() {
		
		String successString="User status updated Successfully";
		User user=new User(15,"Test","User","Active","Admin@gmail.com");
		
		Mockito.when(userRepo.findById(Mockito.anyInt())).thenReturn(java.util.Optional.of(user));
		Mockito.when(userRepo.save(user)).thenReturn(user);
		try {
			String result=userService.updateUser(15, "Active");
			assertEquals(successString,result);
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
    
	   	newList= userService.getAlUsers();
   	 	assertThat(newList.get(0).getUserId()).isEqualTo(list.get(0).getUserId()); 
	}

}
