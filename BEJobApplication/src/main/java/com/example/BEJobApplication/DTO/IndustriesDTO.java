package com.example.BEJobApplication.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class IndustriesDTO {

    private Integer id;

    @Size(max = 255)
    @NotNull
    private String name;

    // Constructor
    public IndustriesDTO() {}

    public IndustriesDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "IndustriesDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
