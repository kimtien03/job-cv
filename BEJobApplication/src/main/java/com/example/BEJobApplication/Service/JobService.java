package com.example.bejobapplication.Service;


import com.example.bejobapplication.Entity.Job;
import com.example.bejobapplication.Exception.NoFoundException;
import com.example.bejobapplication.Reponsitory.JobReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobReponsitory jobRepository;

    public List<Job> getAllJobs() {
        List<Job> jobs = jobRepository.findAll();
        if (jobs.isEmpty()) {
            throw new NoFoundException("Không có công việc nào trong hệ thống.");
        }
        return jobs;
    }

    public Job getJobById(Integer id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy công việc với ID: " + id));
    }

    public Job createJob(Job job) {
        if (job.getName() == null || job.getName().trim().isEmpty()) {
            throw new NoFoundException("Tên công việc không được để trống.");
        }
        return jobRepository.save(job);
    }

    public Job updateJob(Integer id, Job updatedJob) {
        if (updatedJob.getId() != null && !updatedJob.getId().equals(id)) {
            throw new NoFoundException("ID không thể thay đổi.");
        }

        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new NoFoundException("Không tìm thấy công việc với ID: " + id));

        existingJob.setName(updatedJob.getName());
        return jobRepository.save(existingJob);
    }

    public Boolean deleteJob(Integer id) {
        if (!jobRepository.existsById(id)) {
            throw new NoFoundException("Không tìm thấy công việc với ID: " + id);
        }
        jobRepository.deleteById(id);
        return true;
    }
}
