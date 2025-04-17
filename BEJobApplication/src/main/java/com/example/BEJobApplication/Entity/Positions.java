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
    private Integer industry_id;
    public Positions(){}
    public Positions(Integer id,String name,Integer industry_id)
    {
        this.id=id;
        this.name=name;
        this.industry_id=industry_id;
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
        return id;
    }

    public void setIndustry_id(Integer id) {
        this.id = id;
    }
}
