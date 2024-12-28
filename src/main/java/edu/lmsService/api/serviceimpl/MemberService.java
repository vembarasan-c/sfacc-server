package edu.lmsService.api.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import edu.lmsService.api.model.dto.UserDTO;
import edu.lmsService.api.service.IUserService;
import org.springframework.stereotype.Service;

import edu.lmsService.api.model.dao.MemberDAO;
import edu.lmsService.api.model.dto.MemberDTO;
import edu.lmsService.api.repo.IMemberRepository;
import edu.lmsService.api.service.IMemberService;
import edu.lmsService.api.util.MapperUtil;



@Service
public class MemberService implements IMemberService{
    
    private final IMemberRepository MemberRepo;

    private final IUserService UserService;


    public MemberService(IMemberRepository MemberRepo,   IUserService userService) {
        this.MemberRepo = MemberRepo;
        this.UserService = userService;
    }



    @Override
    public Boolean createMember(MemberDTO MemberDetail) {
        if (!MemberDetail.getEmail().isEmpty() && !MemberRepo.findByEmail(MemberDetail.getEmail()).isPresent()){

            String email =  MemberDetail.getEmail();
            String password = MemberDetail.getPassword();
//            String hashedPassword = encoder.encode(password);
//            // Create New user and set hashed(encrypted) password
            UserDTO user = new UserDTO();
            user.setEmail(email);
            user.setPassword(password);
            UserService.registerUser(user);


            MemberDAO MemberData = MapperUtil.map(MemberDetail, MemberDAO.class);

            MemberDetail.setPassword(null);
            MemberRepo.save(MemberData);


            return true;
        }
        return false;
    }

            @Override
    public List<MemberDTO> getAllMember () {
        var MemberDatas = MemberRepo.findAll();
        List<MemberDTO> MemberResponse = new ArrayList<>();
        for (MemberDAO Member : MemberDatas){
            MemberDTO MemberDetail = MapperUtil.map(Member, MemberDTO.class);
//            MemberDetail.setKey((MemberResponse.size() +1)+"");
            MemberResponse.add(MemberDetail);
        }
        return MemberResponse;
    }
}