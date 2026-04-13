/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author given
 */
package com.mycompany.poepart1;

import java.util.regex.Pattern;

/**
 * The Login class handles user registration, credential validation, 
 * and authentication logic for the POE Part 1.
 * * @author [Your Name/Student Number]
 */
public class Login {
    private final String firstName;
    private final String lastName;
    private final String userName;
    private final String password;
    private final String cellphoneNumber;

    /**
     * Constructor to initialize user credentials.
     * @param firstName
     * @param lastName
     * @param userName
     * @param password
     * @param cellphoneNumber
     */
    public Login(String firstName, String lastName, String userName, String password, String cellphoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.cellphoneNumber = cellphoneNumber;
    }

    /**
     * Validates that the username contains an underscore and is 
     * no more than five characters long.
     * @return true if formatting is correct.
     */
    public boolean checkUserName() {
        return userName.contains("_") && userName.length() <= 5;
    }

    /**
     * Validates the cellphone number using Regex.
     * Attribution: Regex pattern researched and assisted by Ai.
     * @return true if number matches +27 format with 9 trailing digits.
     */
    public boolean checkCellNumber() {
        // Research: This regex ensures the prefix is exactly +27 followed by 9 digits.
        String regex = "^\\+27[0-9]{9}$";
        return Pattern.compile(regex).matcher(cellphoneNumber).matches();
    }

    /**
     * Checks password for 8+ chars, Uppercase, Number, and Special Characters.
     * @return true if complexity requirements are met.
     */
    public boolean checkPasswordComplexity() {
        if (password == null || password.length() < 8) return false;
        
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true; 
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasUpper && hasDigit && hasSpecial;
    }

    /**
     * Processes registration and returns the status message.
     * @return A string indicating success or specific formatting errors.
     */
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number and a special character.";
        }
        if (!checkCellNumber()) {
            return "Phone number is not correctly formatted; please ensure it starts with +27 and contains 12 characters in total.";
        }
        return "Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.";
    }

    /**
     * Authenticates the user by comparing entered credentials to stored data.
     * @param enteredUsername
     * @param enteredPassword
     * @return 
     */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername.equals(this.userName) && enteredPassword.equals(this.password);
    }

    /**
     * Returns the final login status message.
     * @param loggedIn
     * @return 
     */
    public String returnLoginStatus(boolean loggedIn) {
        if (loggedIn) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
             return "Username or password incorrect, please try again.";
        }
    }
} 