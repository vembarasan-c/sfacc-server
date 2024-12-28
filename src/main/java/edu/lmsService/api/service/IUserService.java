package edu.lmsService.api.service;

import edu.lmsService.api.model.dto.LoginDTO;
import edu.lmsService.api.model.dto.UserDTO;

public interface IUserService {

    public boolean registerUser(UserDTO userDetail);
    public boolean authenticateUser(LoginDTO loginDetail);
    public UserDTO getUserDetails(String userName);

}
