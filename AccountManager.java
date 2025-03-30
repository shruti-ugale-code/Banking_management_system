package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManager {
    private Connection connection;
    private Scanner scanner;
    public AccountManager(Connection connection,Scanner scanner){
        this.connection=connection;
        this.scanner=scanner;
    }
    public void debit_money(Long accountno)throws SQLException{
        scanner.nextLine();
        System.out.println("enter Amount:");
        Double Amount=scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter security pin:");
        String pass=scanner.nextLine();
        try{
            connection.setAutoCommit(false);
            if(accountno!=0) {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from accounts where account_no=? and security_pin=?");
                preparedStatement.setLong(1, accountno);
                preparedStatement.setString(2, pass);
                ResultSet resultSet=preparedStatement.executeQuery();

                if(resultSet.next()){
                    double current_balance=resultSet.getDouble("balance");
                    if(Amount<=current_balance){
                        String debit_querry="update accounts set balance=balance-? where account_no=?";
                        PreparedStatement prep1=connection.prepareStatement(debit_querry);
                        prep1.setDouble(1,Amount);
                        prep1.setLong(2,accountno);
                        int rowaff=prep1.executeUpdate();
                        if(rowaff>0){
                            System.out.println("amount rs-: "+Amount+"debited sucessfully");
                            connection.commit();
                            connection.setAutoCommit(true);
                            return;
                        }else{
                            System.out.println("transaction failed");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }

                    }else{
                        System.out.println("insuffiecnt balance");
                    }
                }else {
                    System.out.println("incorrect pin!!");
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        connection.setAutoCommit(true);
    }
    public void credit_money(Long accountno)throws SQLException{
        scanner.nextLine();
        System.out.println("enter Amount:");
        Double Amount=scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter security pin:");
        String pass=scanner.nextLine();
        try{
            connection.setAutoCommit(false);
            if(accountno!=0) {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from accounts where account_no=? and security_pin=?");
                preparedStatement.setLong(1, accountno);
                preparedStatement.setString(2, pass);
                ResultSet resultSet=preparedStatement.executeQuery();

                if(resultSet.next()){
                    double current_balance=resultSet.getDouble("balance");

                        String debit_querry="update accounts set balance=balance + ? where account_no=?";
                        PreparedStatement prep1=connection.prepareStatement(debit_querry);
                        prep1.setDouble(1,Amount);
                        prep1.setLong(2,accountno);
                        int rowaff=prep1.executeUpdate();
                        if(rowaff>0){
                            System.out.println("amount rs-: "+Amount+"credited sucessfully");
                            connection.commit();
                            connection.setAutoCommit(true);
                            return;
                        }else{
                            System.out.println("transaction failed");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }

                }else {
                    System.out.println("incorrect pin!!");
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        connection.setAutoCommit(true);
    }
    public void getbalance(long accn_no){
        scanner.nextLine();
        System.out.println("eneter security  pin:");
        String pin=scanner.nextLine();
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("select *from accounts where account_no=? and security_pin=?");
            preparedStatement.setLong(1,accn_no);
            preparedStatement.setString(2,pin);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                double balance =resultSet.getDouble("balance");
                System.out.println("your account balance is rs:"+balance);
            }else {
                System.out.println("invalid pin");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
    public void transfer_money(Long senders_accountno)throws SQLException{
        scanner.nextLine();
        System.out.println("enter recivers account no:");
        Long Reciver_ac=scanner.nextLong();
        scanner.nextLine();
        System.out.println("enter Amount:");
        Double Amount=scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter security pin:");
        String pass=scanner.nextLine();
        try{
            connection.setAutoCommit(false);
            if(senders_accountno!=0 &&Reciver_ac!=0) {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from accounts where account_no=? and security_pin=?");
                preparedStatement.setLong(1, senders_accountno);
                preparedStatement.setString(2, pass);
                ResultSet resultSet=preparedStatement.executeQuery();

                if(resultSet.next()){

                    double current_balance=resultSet.getDouble("balance");
                    if(Amount<=current_balance) {


                        String credit_querry = "update accounts set balance=balance - ? where account_no=?";

                        String debit_querry = "update accounts set balance=balance + ? where account_no=?";
                        PreparedStatement creditprep = connection.prepareStatement(credit_querry);
                        PreparedStatement debitprep = connection.prepareStatement(debit_querry);

                        creditprep.setDouble(1, Amount);
                        creditprep.setLong(2, Reciver_ac);
                        debitprep.setDouble(1, Amount);
                        debitprep.setLong(2, senders_accountno);
                        int rowaff1 = debitprep.executeUpdate();
                        int rowaff2 = creditprep.executeUpdate();
                        if (rowaff1 > 0 && rowaff2 > 0) {
                            System.out.println("amount rs-: " + Amount + "transfer sucessfully");
                            connection.commit();
                            connection.setAutoCommit(true);
                            return;
                        } else {
                            System.out.println("transaction failed");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    }else {
                        System.out.println("insufficent Amount ");
                    }

                }else {
                    System.out.println("incorrect pin!!");
                }


            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        connection.setAutoCommit(true);
    }





}
