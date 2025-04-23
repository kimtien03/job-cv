package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.DTO.UserCvsDTO;
import com.example.BEJobApplication.Entity.Styles;
import com.example.BEJobApplication.Entity.Template_cvs;
import com.example.BEJobApplication.Entity.User;
import com.example.BEJobApplication.Entity.User_cvs;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Responsitory.StylesRepository;
import com.example.BEJobApplication.Responsitory.Template_cvsRepository;
import com.example.BEJobApplication.Responsitory.UserReponsitory;
import com.example.BEJobApplication.Responsitory.User_cvsRepository;
import com.example.BEJobApplication.Mapper.UserCvsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class User_cvsService {

    @Autowired
    private User_cvsRepository userCvsRepository;
    @Autowired
    private StylesRepository stylesRepository;
    @Autowired
    private UserReponsitory userRepository;
    @Autowired
    private Template_cvsRepository templateCvsRepository;

    // Phương thức tạo User_cvs mới
    public UserCvsDTO createUserCvs(UserCvsDTO userCvsDTO) {
        User_cvs userCvs = UserCvsMapper.toUserCvsEntity(userCvsDTO);
        userCvs = userCvsRepository.save(userCvs);  // Lưu vào cơ sở dữ liệu
        return UserCvsMapper.toUserCvsDTO(userCvs);  // Trả về DTO
    }

    // Phương thức lấy tất cả User_cvs
    public List<UserCvsDTO> getAllUserCvs() {
        List<User_cvs> userCvsList = userCvsRepository.findAll();
        return userCvsList.stream()
                .map(UserCvsMapper::toUserCvsDTO)
                .collect(Collectors.toList());
    }

    // Phương thức lấy User_cvs theo ID
    public UserCvsDTO getUserCvsById(Integer id) {
        User_cvs userCvs = userCvsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy User_cvs với ID: " + id));

        return  UserCvsMapper.toUserCvsDTO(userCvs);
    }

    // Phương thức cập nhật User_cvs
    public UserCvsDTO updateUserCvs(Integer id, UserCvsDTO userCvsDTO) {
        // Kiểm tra xem User_cvs có tồn tại không
        User_cvs existingUserCvs = userCvsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy thông tin CV với ID: " + id));

        // Chuyển các giá trị mới từ DTO vào Entity cũ
        existingUserCvs.setCv_name(userCvsDTO.getCvName());
        Optional<Styles> optionalStyle = stylesRepository.findById(userCvsDTO.getStyleId());
        Styles style = optionalStyle.orElseThrow(() -> new NoFoundException("Không tìm thấy Style với ID: " + userCvsDTO.getStyleId()));
        existingUserCvs.setStyle(style);

// Tìm user
        Optional<User> optionalUser = userRepository.findById(userCvsDTO.getUserId());
        User user = optionalUser.orElseThrow(() -> new NoFoundException("Không tìm thấy User với ID: " + userCvsDTO.getUserId()));
        existingUserCvs.setUser(user);

// Tìm template
        Optional<Template_cvs> optionalTemplateCvs = templateCvsRepository.findById(userCvsDTO.getTemplateId());
        Template_cvs templateCvs = optionalTemplateCvs.orElseThrow(() -> new NoFoundException("Không tìm thấy Template với ID: " + userCvsDTO.getTemplateId()));
        existingUserCvs.setTemplates(templateCvs);
        existingUserCvs.setUpdateAt(LocalDateTime.now());

        // Cập nhật và lưu vào cơ sở dữ liệu
        existingUserCvs = userCvsRepository.save(existingUserCvs);

        return UserCvsMapper.toUserCvsDTO(existingUserCvs);  // Trả về DTO đã cập nhật
    }

    // Phương thức xóa User_cvs
    public void deleteUserCvs(Integer id) {
        User_cvs userCvs = userCvsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy User_cvs với ID: " + id));

        userCvsRepository.delete(userCvs);
    }
}
