package model;

public class Country {
    private int countryId;
    private String country;
    /**Gets the country ID
     * @return the country ID
     * */
    public int getCountryId() {
        return countryId;
    }
    /**Sets the country ID
     * @param countryId the country ID
     * */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
    /**Gets the country
     * @return the country's name
     * */
    public String getCountry() {
        return country;
    }
    /**Sets the country
     * @param country the country's name
     * */
    public void setCountry(String country) {
        this.country = country;
    }
    /**Creates a new country object
     * @param countryId the country ID
     * @param country the country's name
     * */
    public Country(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }
    /**Overrides the country object's toString method to display the country ID and name
     * @return a string representing the country object
     * */
    @Override
    public String toString(){
        return ("#" + countryId + " " + country);
    }
}
