package com.example.model;

public class Customer {

    private int customerId;
    private String customerName;
    private String customerPhone;
    private String address;
    private String postalCode;
    private FirstLevelDivision division;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    private Country country;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public FirstLevelDivision getDivision() {
        return division;
    }

    public void setDivision(FirstLevelDivision division) {
        this.division = division;
    }

    public Customer(int customerId, String customerName, String customerPhone, String address, String postalCode, FirstLevelDivision division, Country country) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.address = address;
        this.postalCode = postalCode;
        this.division = division;
        this.country = country;
    }
    @Override
    public String toString(){
        return ("#" + customerId + " " + customerName);
    }
}
