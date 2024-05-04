package com.example.webproject;
import java.sql.*;

public class UserDatabase {
    Connection con;

    public UserDatabase(Connection con) {
        this.con = con;
    }

    public boolean saveUser(User user) {
        boolean set=false;
        try{
            String query = "INSERT INTO General_usr(UserName,Email,Password) values(?,?,?)";

            PreparedStatement pt= this.con.prepareStatement(query);
            pt.setString(1, user.getUserName());
            pt.setString(2, user.getEmail());
            pt.setString(3, user.getPassword());

            pt.executeUpdate();
            set=true;
        }catch(Exception e){
            e.printStackTrace();
        }

        return set;
    }
}
