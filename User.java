package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class User {
    private Connection connection;
    private Scanner scanner;
    public User(Connection connection,Scanner scanner){
        this.connection=connection;
        this.scanner=scanner;
    }
    public void register(){
        scanner.nextLine();
        System.out.println("Full name:");
        String Full_name=scanner.nextLine();
        System.out.println("Email:");
        String email=scanner.nextLine();
        System.out.println("Password:");
        String password=scanner.nextLine();
        if(user_exist(email)){
            System.out.println("User Already Exist For this Email Address!!");
            return;
        }
        String register_querry="insert into user(user_name,email,password) values(?,?,?)";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(register_querry);
            preparedStatement.setString(1,Full_name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,password);
            int rowaffected=preparedStatement.executeUpdate();
            if(rowaffected>0){
                System.out.println("registration sucessfull");
            }else{
                System.out.println("registration failed");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }



    }
    public String login(){
        scanner.nextLine();
        System.out.println("Email:");
        String email=scanner.nextLine();
        System.out.println("password");
        String password=scanner.nextLine();
        String login_querry="select * from user where email=? and password=?";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(login_querry);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet =preparedStatement.executeQuery();
            if(resultSet.next()){
                return email;

            }else{
                return null;

            }
        }catch (SQLException e){
            e.printStackTrace();
        }return null;
    }
    public boolean user_exist(String email){
        String querry="select * from user where email=?";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(querry);
            preparedStatement.setString(1,email);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return  true;
            }else {
                return false;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }return  false;
    }




}
