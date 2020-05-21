/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitTest;

import java.io.IOException;
import java.net.UnknownHostException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import timemanagement.gui.model.Model;
import timemanagement.gui.model.ModelException;
import static utilities.encryptThisString.encryptThisString;

/**
 *
 * @author jigzi
 */
public class NewEmptyJUnitTest {
    
    private Model model;
    
    public NewEmptyJUnitTest() {
        model = new Model();
    }
    

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    private boolean throwException() {
        throw new IllegalArgumentException();
    }
    
    
    /**
     * Test method that checks the login credentials. 
     * @throws UnknownHostException
     * @throws ModelException 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoginCredentials() throws UnknownHostException, ModelException {
        System.out.println("timeMangement:TestLoginCredentials");

        String username = "3";
        String password = encryptThisString("3");

        if (model.checkUserCredentials(username, password, 0)) {
            assertFalse(throwException());
        }
        if (model.checkUserCredentials(username, password, 1)) {
          assertFalse(throwException());
        }
    }
    
    /**
     * Test method that checks if the encryption works
     * @throws UnknownHostException 
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIfEncryptWorks() throws UnknownHostException {
        System.out.println("timeMangement:testIfEncryptWorks");
        String password = encryptThisString("3");

        if (password == "3") {
            assertTrue(false);
        } else {
            assertFalse(throwException());
        }
    }
    
    @Test(expected = IllegalAccessException.class)
    public void 
}
