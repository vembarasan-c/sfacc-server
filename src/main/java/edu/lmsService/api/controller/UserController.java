package edu.lmsService.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.lmsService.api.model.dto.LoginDTO;
import edu.lmsService.api.model.dto.UserDTO;
import edu.lmsService.api.model.response.LoginResponse;
import edu.lmsService.api.service.IUserService;
import edu.lmsService.api.util.UserRole;


@RestController
@RequestMapping("/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/logIn")
    private ResponseEntity<LoginResponse> authenticateUser(@RequestParam String username, @RequestParam String password) {
    Boolean isValidUser = userService.authenticateUser(new LoginDTO(username, password));





    if (isValidUser) {
            UserDTO userData = userService.getUserDetails(username);
            LoginResponse response = LoginResponse.builder().isValidUser(true).role(userData.getRole())
                    .name(userData.getName()).userId(userData.getUserId()).message("User authenticated successfully")
                    .build();
            return ResponseEntity.status(HttpStatus.OK)
                    .body(response);
        }
    LoginResponse response = LoginResponse.builder().isValidUser(false).role(null).name(null)
            .message("Invalid username or password").build();
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(response);
    }

    @PostMapping("/signIn")
    private ResponseEntity<String> registerUser(@RequestBody UserDTO userDetail) {
        userDetail.setRole(UserRole.ADMIN.toString());
        Boolean isCreated = userService.registerUser(userDetail);
        if (isCreated){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User created successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("User creation failed");
    }
}