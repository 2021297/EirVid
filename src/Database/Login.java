/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Entity.Admin;
import Entity.Level;
import Entity.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author deece
 */
public class Login extends ConnectionDB {

    public void login() throws ClassNotFoundException {

        System.out.println("-------------------------");
        System.out.println("------MAIN MENU----------");
        System.out.println("-------------------------");
        System.out.println("1. SIGN-UP");
        System.out.println("2. LOGIN");
        System.out.println("3. EXIT");
        System.out.println("Your choice: ");
        boolean quit = false;

        while (!quit) {
            try {
                Scanner sc = new Scanner(System.in);
                int input = sc.nextInt();
                switch (input) {
                    case 1:
                        System.out.println("Enter your email: ");
                        String email = sc.next();
                        System.out.println("Enter your password: ");
                        String password = sc.next();
                        User user = loginUser(email, password); //check user's username and password
                        Admin admin = loginAdmin(email, password); //check user's username and password
                        if (user != null) {
                            UserDB us = new UserDB(user); 
                            us.menuUserChoice();
                        } else if (admin != null) {
                            AdminDB ad = new AdminDB(admin);
                            ad.menuAdminChoice();
                        } else {
                            System.out.println("USERNAME OR PASSWORD IS WRONG");
                        }
                        break;
                    case 2:
                        Registration r = new Registration();
                        r.registerUser();
                        break;
                    case 3:
                        quit = true;
                        break;
                    default:
                        System.out.println("ENTER BETWEEN 1 - 3!");
                        break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("ENTER INTEGER!!!");
            }
        }
    }

    public User loginUser(String email, String password) throws ClassNotFoundException {

        User user = null;
        try {
            Connection con = DriverManager.getConnection(db_url, db_email, db_password);
            PreparedStatement login = con.prepareStatement("SELECT * FROM eirvid WHERE "
                    + "Email='" + email + "' && Password='" + password + "' "
            );
            login.execute();
            ResultSet rs = login.executeQuery();
            while (rs.next()) {
                int dbid = rs.getInt("id");
                String dbemail = rs.getString("email");
                String dbpassword = rs.getString("password");
                String dbfirstname = rs.getString("firstname");
                String dblastname = rs.getString("lastname");
                Level dblevel = Level.valueOf(rs.getString("level"));
                user = new User(dbid, dbemail, dbpassword, dbfirstname, dblastname, dblevel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Admin loginAdmin(String email, String password) throws ClassNotFoundException {

        Admin admin = null;
        try {
            Connection con = DriverManager.getConnection(db_url, db_email, db_password);
            PreparedStatement login = con.prepareStatement("SELECT * FROM eirvid WHERE "
                    + "email='" + email + "' && password='" + password + "' "
            );
            login.execute();
            ResultSet rs = login.executeQuery();
            while (rs.next()) {
                int dbid = rs.getInt("id");
                String dbemail = rs.getString("email");
                String dbpassword = rs.getString("password");
                String dbfirstname = rs.getString("firstname");
                String dblastname = rs.getString("lastname");
                Level dblevel = Level.valueOf(rs.getString("level"));
                admin = new Admin(dbid, dbemail, dbpassword, dbfirstname, dblastname, dblevel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
}