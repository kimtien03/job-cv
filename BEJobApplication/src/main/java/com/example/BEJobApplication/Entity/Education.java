package com.example.BEJobApplication.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "education")
public class Education {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @jakarta.validation.constraints.NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private com.example.BEJobApplication.Entity.User user;

    @jakarta.validation.constraints.Size(max = 255)
    @jakarta.validation.constraints.NotNull
    @Column(name = "school", nullable = false)
    private String school;

    @jakarta.validation.constraints.Size(max = 255)
    @Column(name = "degree")
    private String degree;

    @jakarta.validation.constraints.Size(max = 255)
    @Column(name = "major")
    private String major;

    @jakarta.validation.constraints.NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

}