package edu.lmsService.api.serviceimpl;

import java.util.Date;

import org.springframework.stereotype.Service;

import edu.lmsService.api.model.dao.UserDAO;
import edu.lmsService.api.model.dto.LoginDTO;
import edu.lmsService.api.model.dto.UserDTO;
import edu.lmsService.api.repo.IUserRepository;
import edu.lmsService.api.service.IUserService;
import edu.lmsService.api.util.MapperUtil;


@Service
public class UserService implements IUserService {
    private IUserRepository userRepo;

    public UserService(IUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean registerUser(UserDTO userDetail) {
        if(!userDetail.getEmail().isEmpty() && !userRepo.findByEmail(userDetail.getEmail()).isPresent()){
            UserDAO userData = UserDAO.builder()
            .name(userDetail.getName())
            .email(userDetail.getEmail())
            .password(userDetail.getPassword())
            .role(userDetail.getRole())
            .userId(userDetail.getUserId())
            .createdDateTime(new Date()).build();
            var saveDetail = userRepo.save(userData);
            System.out.println(saveDetail);
            return true;
        }
        return false;
    }

    @Override
    public boolean authenticateUser(LoginDTO loginDetail) {
        var userData = userRepo.findByEmail(loginDetail.getEmail());
        if (userData.isPresent() && userData.get().getPassword().equalsIgnoreCase(loginDetail.getPassword())){
            return true;
        }
        return false;
    }

    @Override
    public UserDTO getUserDetails(String userName) {
        var userData = userRepo.findByEmail(userName);
        if (userData.isPresent() ){
            return MapperUtil.map(userData.get(), UserDTO.class);
        }
        return new UserDTO();
    }

}
