package model;

import java.time.LocalDateTime;

public class Appointment {

private int appointmentId;
private String title;

private String description;
private String location;
private String type;
private LocalDateTime start;
private LocalDateTime end;
private Customer customer;
private User user;
private Contact contact;

    /**Gets the appointment ID
    * @return the appointment ID
    * */
    public int getAppointmentId() {
        return appointmentId;
    }
    /**Sets the appointment ID
     * @param appointmentId the appointment ID
     * */
    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
    /**Gets the appointment title
     * @return the appointment title
     * */
    public String getTitle() {
        return title;
    }
    /**Sets the appointment title
     * @param title the appointment title
     * */
    public void setTitle(String title) {
        this.title = title;
    }
    /**Gets the appointment location
     * @return the appointment location
     * */
    public String getLocation() {
        return location;
    }
    /**Sets the appointment location
     * @param location the appointment location
     * */
    public void setLocation(String location) {
        this.location = location;
    }
    /**Gets the appointment type
     * @return the appointment type
     * */
    public String getType() {
        return type;
    }
    /**Sets the appointment type
     * @param type  the appointment type
     * */
    public void setType(String type) {
        this.type = type;
    }
    /**Gets the appointment start date and time
     * @return the appointment start date and time
     * */
    public LocalDateTime getStart() {
        return start;
    }
    /**Sets the appointment start date and time
     * @param start the appointment start date and time
     * */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }
    /**Gets the appointment end date and time
     * @return the appointment end date and time
     * */
    public LocalDateTime getEnd() {
        return end;
    }
    /**Sets the appointment end date and time
     * @param end the appointment end date and time
     * */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
    /**Gets the appointment's customer
     * @return the appointment's customer
     * */
    public Customer getCustomer() {
        return customer;
    }
    /**Sets the appointment's customer
     * @param customer the appointment's customer
     * */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    /**Gets the appointment's user
     * @return the appointment's user
     * */
    public User getUser() {
        return user;
    }
    /**Sets the appointment's user
     * @param user the appointment's user
     * */
    public void setUser(User user) {
        this.user = user;
    }
    /**Gets the appointment's contact
     * @return the appointment's contact
     * */
    public Contact getContact() {
        return contact;
    }
    /**Sets the appointment's contact
     * @param contact the appointment's contact
     * */
    public void setContact(Contact contact) {
        this.contact = contact;
    }
    /**Gets the appointment's description
     * @return the appointment's description
     * */
    public String getDescription() {
        return description;
    }
    /**Sets the appointment's description
     * @param description the appointment's description
     * */
    public void setDescription(String description) {
        this.description = description;
    }
    /**Creates a new appointment object
     * @param appointmentId the appointment ID
     * @param title the appointment's title
     * @param description the appointment's description
     * @param location the appointment's location
     * @param type the appointment's type
     * @param start the appointment's start date and time
     * @param end the appointment's end date and time
     * @param customer the appointment's customer
     * @param user the appointment's user
     * @param contact the appointment's contact
     * */
    public Appointment(int appointmentId, String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, Customer customer, User user, Contact contact) {
        this.appointmentId = appointmentId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customer = customer;
        this.user = user;
        this.contact = contact;
    }
    /**Overrides the appointment object's toString method to display the appointment ID and title
     * @return a string representing the appointment object
     * */
    @Override
    public String toString(){
        return ("#" + appointmentId + " " + title);
    }
}
