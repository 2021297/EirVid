/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eirvid;

/**
 *
 * @author yjang
 */

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class database {
  
    public static void main(String[] args){
    
        String url="jdbc:mysql://localhost:3306/eirvid";
        String username="root";
        String password="root";
        try{
        
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection connection=DriverManager.getConnection(url,username,password);
       
             Statement statement = connection.createStatement();
            
            }catch (Exception e){
            System.out.println(e);
            }
 

    }
}
