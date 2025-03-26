package com.example.BEJobApplication.Entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cvs")
public class Cv {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @jakarta.validation.constraints.NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private com.example.BEJobApplication.Entity.User user;

    @jakarta.validation.constraints.NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "template_id", nullable = false)
    private com.example.BEJobApplication.Entity.Template template;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private Instant createdAt;

}