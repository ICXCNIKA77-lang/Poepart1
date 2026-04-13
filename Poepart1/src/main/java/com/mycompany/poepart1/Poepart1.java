/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poepart1;
import java.util.Scanner;
/**
 *
 * @author given
 */


/**
 * The PoePart1 class serves as the entry point for the application.
 * It handles user input for registration and manages the login flow.
 * * @author [Your Name]
 */
public class Poepart1 {

    public static void main(String[] args) {
        // Using try-with-resources to ensure the Scanner closes automatically
        try (Scanner sc = new Scanner(System.in)) {
            
            System.out.println(" Registration ");
            
            //Collect Registration information from teh user.
            System.out.print("Enter first name: ");
            String firstName = sc.nextLine().trim();
            
            System.out.print("Enter last name: ");
            String lastName = sc.nextLine().trim();
            
            System.out.print("Enter Username: ");
            String username = sc.nextLine().trim();
            
            System.out.print("Enter Cellphone Number (+27...): ");
            String cellNo = sc.nextLine().trim();
            
            System.out.print("Enter Password: ");
            String password = sc.nextLine();
            
            //Login object using the constructor
            Login auth = new Login(firstName, lastName, username, password, cellNo);
            
             //Process Registration and display feedback
            String regStatus = auth.registerUser();
            System.out.println("\n" + regStatus);
            
            /* * Decision Structure only proceeds to login if all registration 
             * requirements (Username, Password, and Cell Number) are met.
             */
            if (regStatus.contains("successfully captured")) {
                System.out.println("\n LOGIN");
                
                System.out.print("Enter Username: ");
                String loginUser = sc.nextLine().trim();
                
                System.out.print("Enter Password: ");
                String loginPass = sc.nextLine();
                
                //Verify login credentials
                boolean loginResult = auth.loginUser(loginUser, loginPass);
                
                //Display the final status message (Welcome or Error)
                System.out.println(auth.returnLoginStatus(loginResult));
            } else {
                System.out.println("\nRegistration failed. Please restart the application and follow the formatting rules.");
            }
        }
    }
} 