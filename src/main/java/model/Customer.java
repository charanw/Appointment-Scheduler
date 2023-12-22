package model;

public class Customer {

    private int customerId;
    private String customerName;
    private String customerPhone;
    private String address;
    private String postalCode;
    private FirstLevelDivision division;

    private Country country;
    /**Gets the customer ID
     * @return the customer ID
     * */
    public int getCustomerId() {
        return customerId;
    }
    /**Sets the customer ID
     * @param customerId the customer ID
     * */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    /**Gets the customer's name
     * @return the customer's name
     * */
    public String getCustomerName() {
        return customerName;
    }
    /**Sets the customer's name
     * @param customerName the customer's name
     * */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    /**Gets the customer's phone number
     * @return the customer's phone number
     * */
    public String getCustomerPhone() {
        return customerPhone;
    }
    /**Sets the customer's phone number
     * @param customerPhone the customer's phone number
     * */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    /**Gets the customer's address
     * @return the customer's address
     * */
    public String getAddress() {
        return address;
    }
    /**Sets the customer's address
     * @param address the customer's address
     * */
    public void setAddress(String address) {
        this.address = address;
    }
    /**Gets the customer's postal code
     * @return the customer's postal code
     * */
    public String getPostalCode() {
        return postalCode;
    }
    /**Sets the customer's postal code
     * @param postalCode the customer's postal code
     * */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    /**Gets the customer's first-level division
     * @return the customer's first-level division
     * */
    public FirstLevelDivision getDivision() {
        return division;
    }
    /**Sets the customer's first-level division
     * @param division the customer's first-level division
     * */
    public void setDivision(FirstLevelDivision division) {
        this.division = division;
    }
    /**Gets the customer's country
     * @return the customer's country
     * */
    public Country getCountry() {
        return country;
    }
    /**Sets the customer's country
     * @param country the customer's country
     * */
    public void setCountry(Country country) {
        this.country = country;
    }
    /**Creates a new customer object
     * @param customerId the customer ID
     * @param customerName the customer's name
     * @param customerPhone the customer's phone number
     * @param address the customer's address
     * @param postalCode the customer's postal code
     * @param division the customer's division
     * @param country the customer's country
     * */
    public Customer(int customerId, String customerName, String customerPhone, String address, String postalCode, FirstLevelDivision division, Country country) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.address = address;
        this.postalCode = postalCode;
        this.division = division;
        this.country = country;
    }
    /**Overrides the customer object's toString method to display the customer ID and name
     * @return a string representing the customer object
     * */
    @Override
    public String toString(){
        return ("#" + customerId + " " + customerName);
    }
}
