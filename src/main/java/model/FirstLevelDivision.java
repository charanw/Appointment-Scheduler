package model;

public class FirstLevelDivision {
    private int divisionId;
    private String division;
    private Country country;
    /**Gets the division ID
     * @return the division ID
     * */
    public int getDivisionId() {
        return divisionId;
    }
    /**Sets the division ID
     * @param divisionId the division ID
     * */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }
    /**Gets the division name
     * @return the division's name
     * */
    public String getDivision() {
        return division;
    }
    /**Sets the division name
     * @param division the division's name
     * */
    public void setDivision(String division) {
        this.division = division;
    }
    /**Gets the division's country
     * @return the division's country
     * */
    public Country getCountry() {
        return country;
    }
    /**Sets the division's country
     * @param country the division's country
     * */
    public void setCountry(Country country) {
        this.country = country;
    }
    /**Creates a new first-level division object
     * @param divisionId the division ID
     * @param division the division's name
     * @param country the division's country
     * */
    public FirstLevelDivision(int divisionId, String division, Country country) {
        this.divisionId = divisionId;
        this.division = division;
        this.country = country;
    }
    /**Overrides the first-level division object's toString method to display the division ID and name
     * @return a string representing the first-level division object
     * */
    @Override
    public String toString(){
        return ("#" + divisionId + " " + division);
    }
}
