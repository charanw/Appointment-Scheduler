package model;

public class Contact {

private int contactId;
private String contactName;
    /**Gets the contact ID
     * @return the contact ID
     * */
    public int getContactId() {
        return contactId;
    }
    /**Sets the contact ID
     * @param contactId the contact ID
     * */
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    /**Gets the contact's name
     * @return the contact's name
     * */
    public String getContactName() {
        return contactName;
    }
    /**Sets the contact's name
     * @param contactName the contact's name
     * */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    /**Creates a new Contact object
     * @param contactId the contact ID
     * @param contactName the contact's name
     * */
    public Contact(int contactId, String contactName) {
        this.contactId = contactId;
        this.contactName = contactName;
    }
    /**Overrides the contact object's toString method to display the contact ID and title
     * @return a string representing the contact object
     * */
    @Override
    public String toString(){
        return ("#" + contactId + " " + contactName);
    }
}
