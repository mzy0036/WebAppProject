package com.example.webproject;

public class User {
    int UsrID;
    String UserName;
    String Email;
    String Password;

    public User(){

    }
    public User(int UsrID, String UserName, String Email, String Password) {
        this.UsrID = UsrID;
        this.UserName = UserName;
        this.Email = Email;
        this.Password = Password;
    }

    public User(String UserName,String Email, String Password) {
        this.UserName = UserName;
        this.Email = Email;
        this.Password = Password;
    }

    public User(String Email, String Password) {
        this.Email = Email;
        this.Password = Password;
    }

    public int getUsrID() {
        return UsrID;
    }
    public void setUsrID(int UsrID) {
        this.UsrID = UsrID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        UserName = UserName;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String Email) {
        Email = Email;
    }
    public String getPassword() {
        return Password;
    }
    public void setPassword(String Password) {
        Password = Password;
    }
}


