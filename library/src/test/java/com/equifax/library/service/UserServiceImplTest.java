package com.equifax.library.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
		userDTO = new UserDTO(4,"Ann Frank","admin","Active");
		user = new User(4,"Ann Frank","admin","Active");
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
}
