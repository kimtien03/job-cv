package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.Entity.User_cvs;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Responsitory.User_cvsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User_cvsService {

    @Autowired
    private User_cvsRepository userCvsRepository;

    // Lấy tất cả CV của user
    public List<User_cvs> getAllUser() {
        List<User_cvs> users_cvs = userCvsRepository.findAll();
        if (users_cvs.isEmpty()) {
            throw new NoFoundException("Không có CV của user nào.");
        }
        return users_cvs;
    }

    // Lấy CV theo ID
    public User_cvs getUserCvsById(Integer id) {
        return userCvsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy CV với id: " + id));
    }

    // Thêm mới một CV
    public User_cvs createUserCvs(User_cvs userCvs) {
        return userCvsRepository.save(userCvs);
    }

    // Cập nhật một CV theo ID
    public User_cvs updateUserCvs(Integer id, User_cvs userCvsDetails) {
        User_cvs userCvs = userCvsRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy CV với id: " + id));

        userCvs.setUser_id(userCvs.getUser_id());
        userCvs.setTemplates_id(userCvs.getTemplates_id());
        userCvs.setStyle_id(userCvs.getStyle_id());
        userCvs.setCv_name(userCvs.getCv_name());
        userCvs.setCreateAt(userCvs.getCreateAt());
        userCvs.setUpdateAt(userCvs.getUpdateAt());

        return userCvsRepository.save(userCvs);
    }

    // Xóa một CV theo ID
    public void deleteUserCvs(Integer id) {
        if (!userCvsRepository.existsById(id)) {
            throw new NoFoundException("Không tìm thấy CV với id: " + id);
        }
        userCvsRepository.deleteById(id);
    }
}
