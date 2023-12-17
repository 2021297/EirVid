/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Entity.Level;
import Entity.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author deece
 */
public class Registration extends ConnectionDB {

    public boolean registerUser() {
        Scanner sc = new Scanner(System.in);
        String dbEmail = "";
        try {
            Connection con = DriverManager.getConnection(db_url, db_email, db_password);
            System.out.println("Enter your email address: ");
            String email = sc.next();
            PreparedStatement check = con.prepareStatement("SELECT email FROM user_data WHERE "
                    + "email= '" + email + "'"
            );
            check.execute("USE eirvid");
            ResultSet rs = check.executeQuery();
            while (rs.next()) {
                dbEmail = rs.getString("email");
            }
            if (email.equals(dbEmail)) {
                System.out.println("EMAIL EXISTS ALREADY");
            } else {
                System.out.println("Enter your password: ");
                String pass = sc.next();
                System.out.println("Enter your firstname: ");
                String fname = sc.next();
                System.out.println("Enter your lastname: ");
                String lname = sc.next();

                User user = new User(0, email, pass, fname, lname, Level.regular);

                PreparedStatement insert = con.prepareStatement("INSERT INTO user_data ("
                        + "email, password, firstname, lastname, level) VALUES ("
                        + "'" + email + "', '" + pass + "', '" + fname + "', '" + lname + "', '" + Level.regular + "');"
                );
                insert.executeUpdate();
                System.out.println("USER REGISTERED SUCCESSFULLY");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
