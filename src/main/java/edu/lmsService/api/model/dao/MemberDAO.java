package edu.lmsService.api.model.dao;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "member")
public class MemberDAO {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;
    private String password;

    private String name;
//    private String dateOfBirth;
//    private String fatherName;
//    private String address;
//    private String aadharNumber;
//    private String panNumber;
//    private String phoneNumber;
//    private String nominee;
//    private String place;
//    private String date;
//    private String signature;


}
