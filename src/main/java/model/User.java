package model;

public class User {

    private int userId;
    private String userName;
    /**Gets the user ID
     * @return the user ID
     * */
    public int getUserId() {
        return userId;
    }
    /**Sets the user ID
     * @param userId the user ID
     * */
    public void setUserId(int userId) {
        this.userId = userId;
    }
    /**Gets the username
     * @return the username
     * */
    public String getUserName() {
        return userName;
    }
    /**Sets the username
     * @param userName the username
     * */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Creates a new user object
     * @param userId
     * @param userName
     */
    public User(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
    /**Overrides the user object's toString method to display the user ID and name
     * @return a string representing the user object
     * */
    @Override
    public String toString(){
        return ("#" + userId + " " + userName);
    }

}
