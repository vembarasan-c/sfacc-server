package edu.lmsService.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.lmsService.api.model.dto.MemberDTO;
import edu.lmsService.api.service.IMemberService;



@RestController
@RequestMapping("/members")
public class MemberController {


    private final IMemberService MemberService;



    public MemberController(IMemberService MemberService) {
        this.MemberService = MemberService;
    }




    @GetMapping("/getAllMember")
    public List<MemberDTO> getAllMember() {
        return MemberService.getAllMember();
    }


    @PostMapping("/createMember")
    public ResponseEntity<String> postMethodName(@RequestBody MemberDTO MemberDetail) {
        Boolean isCreated = MemberService.createMember(MemberDetail);

        if (isCreated){


            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Member created successfully");



        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Member creation failed");
    }

}
