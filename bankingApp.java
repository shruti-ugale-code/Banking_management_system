package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class bankingApp {
    private static final String url="jdbc:mysql://localhost:3306/banking_systen";
    private static final String username="root";
    private static final String password="Shruti@123";
    public static void main(String args[]) throws ClassNotFoundException, SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection connection= DriverManager.getConnection(url,username,password);
            System.out.println("connection establish sucessfully");
            Scanner scanner=new Scanner(System.in);
            User user=new User(connection,scanner);
            AccountManager accountManager=new AccountManager(connection,scanner);
            Account account=new Account(connection,scanner);
            String email;
            Long Account_no;
            while(true){
                String text = "\u001B[1m\u001B[97mWELCOME TO BANKING SYSTEM!!!!\u001B[0m";

                System.out.println(text);
                System.out.println();
                System.out.println("1.REGISTER");
                System.out.println("2.LOGIN");
                System.out.println("3.EXIT");
                System.out.println();
                System.out.println("Enter your choice:-");
                int choice1=scanner.nextInt();
                switch (choice1){
                    case 1:
                        user.register();
                        break;
                    case 2:
                        email=user.login();
                        if(email!=null) {
                            System.out.println();
                            System.out.println("user logged in");
                            if (!account.Account_exist(email)) {
                                System.out.println();


                                System.out.println("1.OPEN BANK ACCOUNT");
                                System.out.println("2.EXIT");

                                if (scanner.nextInt() == 1) {
                                    Account_no = account.create_accn(email);
                                    System.out.println("ACCOUNT CREATED SUCESSFULLY");
                                    System.out.println("YOUR ACCOUNT NO IS :" + Account_no);

                                } else {
                                    break;
                                }
                            }

                                Account_no = account.get_accountno(email);
                                int choice2 = 0;
                                while (choice2 != 5) {
                                    System.out.println("1.Debit money");
                                    System.out.println("2.credit money");
                                    System.out.println("3.Transfer money");
                                    System.out.println("4.check balance");
                                    System.out.println("5.LOg out");
                                    System.out.println("ENTER YOUR CHOICE");
                                    choice2 = scanner.nextInt();
                                    switch (choice2) {
                                        case 1:
                                            accountManager.debit_money(Account_no);
                                            break;
                                        case 2:
                                            accountManager.credit_money(Account_no);
                                            break;
                                        case 3:
                                            accountManager.transfer_money(Account_no);
                                            break;
                                        case 4:
                                            accountManager.getbalance(Account_no);
                                            break;
                                        case 5:
                                            break;
                                        default:
                                            System.out.println("Enter valid choice");
                                            break;


                                    }

                                }

                            } else {
                                System.out.println("Incorrect email or password");
                            }

                            case 3:
                                System.out.println("THANKYOU FOR USING BANKING SYSTENM!!!!");
                                System.out.println("EXITING SYSTEM");
                                return;
                            default:
                                System.out.println("Enter valid choice");
                                break;

                }
            }





        }catch (SQLException e){
e.printStackTrace();
        }
    }

}
