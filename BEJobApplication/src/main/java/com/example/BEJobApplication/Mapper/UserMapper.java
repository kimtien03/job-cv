package com.example.BEJobApplication.Mapper;


import com.example.BEJobApplication.DTO.UserDTO;
import com.example.BEJobApplication.Entity.User;

public class UserMapper {

    // Entity -> DTO
    public static UserDTO toUserDTO(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setBirthday(user.getBirth_day());
        dto.setGender(user.getGender());
        dto.setRole(user.getRole());

        return dto;
    }

    // DTO -> Entity (nếu cần tạo mới user từ form)
    public static User toUserEntity(UserDTO dto) {
        if (dto == null) return null;

        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setBirth_day(dto.getBirthday());
        user.setGender(dto.getGender());
        user.setRole(dto.getRole());

        // Không set password ở đây để tránh gán bừa bãi
        return user;
    }

    // Chuyển đổi rút gọn chỉ gồm id và username (ví dụ dùng trong CampaignResponse)
    public static UserDTO toUserSimple(User user) {
        if (user == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        return dto;
    }
}
