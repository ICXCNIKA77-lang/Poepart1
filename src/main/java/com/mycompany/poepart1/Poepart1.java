/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poepart1;
import java.util.Scanner;
/**
 *
 * @author given
 */

public class Poepart1 {
    public static void main(String[] args) {
     
   try (Scanner input = new Scanner(System.in)) {
      System.out.println(" User Registration");
            
      System.out.print("Enter first name: ");
                 String firstName = input.nextLine();           
      System.out.print("Enter last name: ");
                 String lastName = input.nextLine().trim();

            //for Username
      System.out.println("\n[(Username must contain an underscore (_) and be 5 characters or less)]");
      System.out.print("Enter username: ");
                 String username = input.nextLine().trim();

            //for Password
      System.out.println("\n[(Password must be 8+ chars, have a capital letter, a number, and a special character)]");
      System.out.print("Enter password: ");
                 String password = input.nextLine().trim();

            //for Cellphone
            System.out.println("\n[(Start with +27 and be 13 characters totaL)]");
            System.out.print("Enter cellphone number: ");
                 String cellphone = input.nextLine().trim();

            Login reg = new Login(firstName, lastName, username, password, cellphone);

            // Call the registration method
                 String registerResult = reg.registerUser(); 
            System.out.println(registerResult);

            // Check if registration was successful
            if (registerResult.equals("The user has successfully registered")) {
                System.out.println("User Login");
                System.out.print("Enter username to login: ");
                  String loginUsername = input.nextLine();
                System.out.print("Enter password to login: ");
                  String loginPassword = input.nextLine();
                
                  String loginResult = reg.returnLoginStatus(loginUsername, loginPassword);
                System.out.println(loginResult);
            }
        }
    }
}