package com.equifax.library.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.equifax.library.dto.UserDTO;
import com.equifax.library.model.User;
import com.equifax.library.repository.UserRepository;


@RunWith(SpringRunner.class)
public class UserServiceImplTest {
	
	
	
	@Mock
	private UserRepository userRepo;
	
	@InjectMocks
    private UserServiceImpl userService;
	
	@Test
	public void shouldaddUserSuccessfully() {
		String successString="User Added Successfully";
		UserDTO userDTO = new UserDTO(15,"Test","User","Active");
		
		User user=new User();
		user.setUserId(15);
		user.setUserName("Test");
		user.setUserRole("User");
		user.setUserStatus("Active");
		
		//Mockito.when(userService.createUserFromUserDTO(userDTO)).thenReturn(user);
		Mockito.when(userRepo.save(Mockito.any(User.class))).thenReturn(user);
		
		String status = userService.addUser(userDTO);
		
		System.err.println("Status::"+status);
		
	    //verify(userRepo,times(1)).save(user);
		assertThat(status).isEqualTo(successString);
	}
	
	@Test
	public void shouldDeleteUserSuccessFully() {
		String successString="Test deleted";
		User user=new User();
		user.setUserId(15);
		user.setUserName("Test");
		user.setUserRole("User");
		user.setUserStatus("Active");
		
		Mockito.when(userRepo.findById(Mockito.anyInt())).thenReturn(Optional.of(user));
		
		String status = userService.deleteUser(user.getUserId());
		System.err.println("Deletion Status::"+status);
		
		assertThat(status).isEqualTo(successString);
		verify(userRepo,times(1)).delete(user);
		
	}

}
