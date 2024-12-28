package edu.lmsService.api.model.dao;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "auth")
public class UserDAO {
    @Id
    private String id;
    @Indexed(unique = true)
    private String email;

    private String userId;
    private String name;
    private String password;
    private String role;
    private Date createdDateTime;
}
