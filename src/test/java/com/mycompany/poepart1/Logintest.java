/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.poepart1;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author given
 */
public class Logintest {
    
    public Logintest() {
    }
    
 
    /**
     * Test of checkPasswordComplexity method, of class Login.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        Login instance = null;
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity();
        assertEquals(expResult, result);
        
       
    }

    /**
     * Test of checkUserName method, of class Login.
     */
    @Test
    public void testCheckUserName() {
        System.out.println("checkUserName");
        Login instance = null;
        boolean expResult = false;
        boolean result = instance.checkUserName();
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of checkPhoneNumber method, of class Login.
     */
    @Test
    public void testCheckPhoneNumber() {
        System.out.println("checkPhoneNumber");
        Login instance = null;
        boolean expResult = false;
        boolean result = instance.checkPhoneNumber();
        assertEquals(expResult, result);
        
        
    }

    /**
     * Test of registerUser method, of class Login.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        Login instance = null;
        String expResult = "";
        String result = instance.registerUser();
        assertEquals(expResult, result);
       
        
    }

    /**
     * Test of loginUser method, of class Login.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        String enteredUserName = "";
        String enteredPassword = "";
        Login instance = null;
        boolean expResult = false;
        boolean result = instance.loginUser(enteredUserName, enteredPassword);
        assertEquals(expResult, result);
        
       
    }

    /**
     * Test of returnLoginStatus method, of class Login.
     */
    @Test
    public void testReturnLoginStatus() {
        System.out.println("returnLoginStatus");
        String entereduserName = "";
        String enteredpassword = "";
        Login instance = null;
        String expResult = "";
        String result = instance.returnLoginStatus(entereduserName, enteredpassword);
        assertEquals(expResult, result);
       
        
    }
    
}
