package com.example.BEJobApplication.Mapper;


import com.example.BEJobApplication.DTO.UserCvsDTO;
import com.example.BEJobApplication.Entity.User_cvs;

public class UserCvsMapper {

    // Entity -> DTO
    public static UserCvsDTO toUserCvsDTO(User_cvs userCvs) {
        if (userCvs == null) {
            return null;
        }

        return new UserCvsDTO(
                userCvs.getId(),
                userCvs.getUser().getId(),
                userCvs.getTemplates().getId(),
                userCvs.getCv_name(),
                userCvs.getStyle().getId(),
                userCvs.getCreateAt(),
                userCvs.getUpdateAt()
        );
    }

    // DTO -> Entity
    public static User_cvs toUserCvsEntity(UserCvsDTO userCvsDTO) {
        if (userCvsDTO == null) {
            return null;
        }

        User_cvs userCvs = new User_cvs();
        userCvs.setId(userCvsDTO.getId());
        // Set other properties here
        return userCvs;
    }
}
