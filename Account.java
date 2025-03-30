package org.example;

import java.sql.*;
import java.util.Scanner;

public class Account {
    private Connection connection;
    private Scanner scanner;
    public Account(Connection connection,Scanner scanner){
        this.connection=connection;
        this.scanner=scanner;

    }
    private long generateAccountno(){
        try{
            Statement statement=connection.createStatement();
        ResultSet resultSet= statement.executeQuery("select account_no from accounts order by account_no desc limit 1");
        if(resultSet.next()){
            long Last_accn_no=resultSet.getLong("account_no");
            return Last_accn_no+1;
        }else {
            return 10000100;

        }
        }catch (SQLException e){
            e.printStackTrace();
        }return 10000100;
    }
    public long create_accn(String email){
        if(!Account_exist(email)){
            String CQuerry="insert into accounts(account_no,user_name,email,balance,security_pin)values(?,?,?,?,?)";
            scanner.nextLine();
            System.out.println("enter full name:");
            String name=scanner.nextLine();
            System.out.println("Enter initial ammount:");
            Double balance=scanner.nextDouble();
            scanner.nextLine();
            System.out.println("enter secirity pin");
            String pin=scanner.nextLine();
           try {
                long accountno=generateAccountno();
                PreparedStatement preparedStatement=connection.prepareStatement(CQuerry);
                preparedStatement.setLong(1,accountno);
                preparedStatement.setString(2,name);
                preparedStatement.setString(3,email);
                preparedStatement.setDouble(4,balance);
                preparedStatement.setString(5,pin);
                int rowaffect=preparedStatement.executeUpdate();
                if(rowaffect>0){
                    return accountno;
                }else {
                    System.out.println("Account creation sucessful");
                }
            }catch (SQLException e){
               e.printStackTrace();
           }

        } throw new RuntimeException("Account already exist");
    }

    public long get_accountno(String email){
        String querry="select * from accounts where email=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(querry);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("account_no");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }throw new RuntimeException("Account no dosent exist");

    }

    public boolean Account_exist(String email){
        String querry="select * from accounts where email=?";
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
