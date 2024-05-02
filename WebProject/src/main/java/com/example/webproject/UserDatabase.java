package com.example.webproject;
import java.sql.*;

public class UserDatabase {
    Connection con;

    public UserDatabase(Connection con) {
        this.con = con;
    }

    public boolean savaUser(User user) {
        boolean set=false;
        try{
            String query = "insert into General_usr(username,password) values(?,?)";

            PreparedStatement pt= this.con.prepareStatement(query);
            pt.setString(1, user.getUserName());
            pt.setString(2, user.getPassword());

            pt.executeUpdate();
            set=true;
        }catch(Exception e){
            e.printStackTrace();
        }

        return set;
    }
}
