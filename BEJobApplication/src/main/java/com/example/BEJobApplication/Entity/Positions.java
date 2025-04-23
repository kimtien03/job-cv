package com.example.BEJobApplication.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "positions")
public class Positions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "industry_id", nullable = false)
    private Industries industry;

    // Constructors
    public Positions() {}

    public Positions(Integer id, String name, Industries industry) {
        this.id = id;
        this.name = name;
        this.industry = industry;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Industries getIndustry() {
        return industry;
    }

    public void setIndustry(Industries industry) {
        this.industry = industry;
    }

    // toString
    @Override
    public String toString() {
        return "Positions{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", industry=" + (industry != null ? industry.getId() : null) +
                '}';
    }
}
