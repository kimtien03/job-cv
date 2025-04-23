package org.example.Models;

public class Positions {

    private Integer id;

    private String name;

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

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }


}
