package com.example.BEJobApplication.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "positions")
public class Positions {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "industry_id", nullable = false)
    private Integer industryId;

    public Positions() {
    }

    public Positions(Integer id, String name, Integer industryId) {
        this.id = id;
        this.name = name;
        this.industryId = industryId;
    }

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

    public Integer getIndustry_id() {
        return industryId;
    }

    public void setIndustry_id(Integer industry_id) {
        this.industryId = industry_id;
    }

    @Override
    public String toString() {
        return "position{"
                + "id=" + id
                + ", name=" + name
                + ", intrid=" + industryId
                + '}';
    }

}
