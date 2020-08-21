package com.equifax.library.service;

import java.util.ArrayList;
import java.util.List;

import com.equifax.library.dto.UserDTO;
import com.equifax.library.model.User;

public interface UserService {
	
	
	public String addUser(UserDTO userDTO);
	public String deleteUser(int userId);
	public boolean authenticateUser(int userId);
//	public boolean validateUserStatus(int userId);
	public String validateUser(UserDTO userDTO);
	public String updateUser(int userId, String userStatus);
  public ArrayList<UserDTO> getAlUsers();

}
