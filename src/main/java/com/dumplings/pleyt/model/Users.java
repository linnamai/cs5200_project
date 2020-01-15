package com.dumplings.pleyt.model;

public class Users {
    protected int UserId;
    protected String UserName;
    protected String Password;
    protected String FirstName;
    protected String LastName;

    public Users(int userId, String userName, String password, String firstName, String lastName) {
        this.UserId = userId;
        this.UserName = userName;
        this.Password = password;
        this.FirstName = firstName;
        this.LastName = lastName;
    }

    public Users() {}

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        this.UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        this.UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

}
