package com.example.bejobapplication.Service;

import com.example.bejobapplication.Entity.Cv;
import com.example.bejobapplication.Exception.NoFoundException;
import com.example.bejobapplication.Reponsitory.CvReponsitory;
import com.example.bejobapplication.Reponsitory.TemplateReponsitory;
import com.example.bejobapplication.Reponsitory.UserReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CvService {
    @Autowired
    private CvReponsitory cvRepository;
    @Autowired
    private UserReponsitory userRepository;
    @Autowired
    private TemplateReponsitory templateReponsitory;
    public List<Cv> getAllCV() {
        List<Cv> cvs = cvRepository.findAll();
        if (cvs.isEmpty()) {
            throw new NoFoundException("Không có Curriculum Vitae nào trong hệ thống.");
        }
        return cvs;
    }

    public Cv getCvById(Integer id) {
        return cvRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy Curriculum Vitae với ID: " + id));
    }

    public Boolean deleteCv(Integer id) {
        if (!cvRepository.existsById(id)) {
            throw new NoFoundException("Không tìm thấy urriculum Vitae với ID: " + id);
        }
        cvRepository.deleteById(id);
        return true;
    }
    public Cv createCv(Cv cv) {
        validateCv(cv);
        return cvRepository.save(cv);
    }
    private void validateCv(Cv cv) {
        if (!userRepository.existsById(cv.getUser().getId())) {
            throw new NoFoundException("Người dùng với ID " + cv.getUser().getId() + " không tồn tại.");
        }

        if (!templateReponsitory.existsById(cv.getTemplate().getId())) {
            throw new NoFoundException("Người dùng với ID " + cv.getTemplate().getId() + " không tồn tại.");
        }

        if (cv.getContent() == null || cv.getContent().trim().isEmpty()) {
            throw new NoFoundException("Nội dung CV không được để trống.");
        }
    }
    public Cv updateCv(Integer id, Cv updatedCv) {
        if (updatedCv.getId() != null && !updatedCv.getId().equals(id)) {
            throw new NoFoundException("ID không thể thay đổi");
        }

        validateCv(updatedCv);


        return cvRepository.save(updatedCv);
    }



}
