package org.example.Models;

public class Positions {

    private Integer id;

    private String name;

    private Integer industry_id;

    public Positions() {
    }

    public Positions(Integer id, String name, Integer industry_id) {
        this.id = id;
        this.name = name;
        this.industry_id = industry_id;
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
        return industry_id;
    }

    public void setIndustry_id(Integer industry_id) {
        this.industry_id = industry_id;
    }


}
