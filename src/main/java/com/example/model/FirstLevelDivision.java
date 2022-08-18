package com.example.model;

public class FirstLevelDivision {
    private int divisionId;
    private String division;
    private Country country;

    public int getDivisionId() {
        return divisionId;
    }

    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public FirstLevelDivision(int divisionId, String division, Country country) {
        this.divisionId = divisionId;
        this.division = division;
        this.country = country;
    }
}
