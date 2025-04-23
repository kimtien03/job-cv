package com.example.BEJobApplication.Service;

import com.example.BEJobApplication.Entity.Industries;
import com.example.BEJobApplication.Exception.NoFoundException;
import com.example.BEJobApplication.Responsitory.IndustriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndustriesService {

    @Autowired
    private IndustriesRepository industriesRepository;

    // Lấy tất cả các industries
    public List<Industries> getAllIndustries() {
        List<Industries> industries = industriesRepository.findAll();
        if (industries.isEmpty()) {
            throw new NoFoundException("Không có ngành nào.");
        }
        return industries;
    }

    // Lấy ngành theo ID
    public Industries getIndustryById(Integer id) {
        return industriesRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy ngành với ID: " + id));
    }

    // Thêm mới ngành
    public Industries createIndustry(Industries industry) {
        return industriesRepository.save(industry);
    }

    // Cập nhật ngành
    public Industries updateIndustry(Integer id, Industries updatedIndustry) {
        Industries existingIndustry = industriesRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy ngành với ID: " + id));

        existingIndustry.setName(updatedIndustry.getName());
        return industriesRepository.save(existingIndustry);
    }

    // Xóa ngành
    public void deleteIndustry(Integer id) {
        if (!industriesRepository.existsById(id)) {
            throw new NoFoundException("Không tìm thấy ngành với ID: " + id);
        }
        industriesRepository.deleteById(id);
    }
}
