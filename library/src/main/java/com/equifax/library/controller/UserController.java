package com.equifax.library.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.equifax.library.dto.UserDTO;
import com.equifax.library.service.UserService;


@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(method=RequestMethod.POST,value="/addUser")
	public String addUser(@RequestBody UserDTO userDTO,@RequestHeader("verifyUser") int userId) {
		if(userService.authenticateUser(userId)) {
			String validationStatus=userService.validateUser(userDTO);
			if(validationStatus.equalsIgnoreCase("success")) {
				try {
						userService.addUser(userDTO);
						return "User added successfully";
				}
				catch (Exception e) {
					e.printStackTrace();
					return "Exception Occured while adding the user";
				}
				
			}else {
				return validationStatus;
			}
								
		}else {
			return "User does not have privileges to add another user";
			
		}
	}
	
	@DeleteMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable(name="id") int userId,@RequestHeader("Verifyuser") int Verifyuser) {
		if(userService.authenticateUser(Verifyuser)) {
			try {
				String status=userService.deleteUser(userId);
				return status;
			}catch(Exception e) {
				e.printStackTrace();
				return "Some exception occured";
			}
		}else 
			return "User does not have privileges to delete another user";	
		
	}
	
	@PutMapping("/updateUser/{adminId}")
	public String updateUser(@PathVariable int adminId,@RequestHeader("UserId") int userId,
			@RequestHeader("UserStatus") String userStatus) {
		if(userService.authenticateUser(adminId)) {
			try {
				String status=userService.updateUser(userId,userStatus);
				return status;
			}catch(Exception e) {
				e.printStackTrace();
				return "Some exception occured while updating UserStatus";
			}
		}else 
			return "User does not have privileges to update another user's status";	
	}
	
	@GetMapping("/getAllUsers")
	public ArrayList <UserDTO> getAlUsers(){
		return userService.getAlUsers();
		
	}
	
	
	
	
	@PutMapping("/updateUser/{adminId}")
	public String updateUser(@PathVariable int adminId,@RequestHeader("UserId") int userId,@RequestHeader("UserStatus") String userStatus) {
		if(userService.authenticateUser(adminId)) {
			try {
				
				String status=userService.updateUser(userId,userStatus);
				return status;
			}catch(Exception e) {
				e.printStackTrace();
				return "Some exception occured while updating UserStatus";
			}
		}else 
			return "User does not have privileges to update another user's status";	
	}
	
	
	
}

