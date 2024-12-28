package edu.lmsService.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private String name="";
    private String email="";
    private String password="";

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

