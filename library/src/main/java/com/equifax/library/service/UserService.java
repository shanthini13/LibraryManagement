package com.equifax.library.service;

import com.equifax.library.dto.UserDTO;

public interface UserService {
	
	
	public String addUser(UserDTO userDTO);
	public String deleteUser(int userId);
	public boolean authenticateUser(int userId);
	public String validateUser(UserDTO userDTO);

}
