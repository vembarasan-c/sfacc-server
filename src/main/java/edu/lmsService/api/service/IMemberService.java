package edu.lmsService.api.service;

import java.util.List;

import edu.lmsService.api.model.dto.MemberDTO;

public interface IMemberService {
    List<MemberDTO> getAllMember ();

    Boolean createMember(MemberDTO memberDetail);
}
