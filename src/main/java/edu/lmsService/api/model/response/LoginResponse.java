package edu.lmsService.api.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private Boolean isValidUser;
    private String role;
    private String name;
    private String userId;
    private String message;
}