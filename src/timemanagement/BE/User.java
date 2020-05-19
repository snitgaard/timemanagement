/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author The Cowboys
 */
public class User
{
    private int id;
    private StringProperty userLogin;
    private StringProperty userPassword;
    private IntegerProperty isAdmin;
    private StringProperty adminRights; 
    private StringProperty email;
    private StringProperty fullName;

    
    /**
     * Constructor for User
     * @param id 
     */
    public User(int id) {
        this.id = id;
    }
    
    /**
     * Constructor for User
     * @param id
     * @param userLogin
     * @param userPassword
     * @param isAdmin
     * @param email
     * @param fullName
     * @param adminRights
     */
    public User(int id, String userLogin, String userPassword, int isAdmin, String adminRights, String email, String fullName) {
        this.id = id;
        this.userLogin = new SimpleStringProperty(userLogin);
        this.userPassword = new SimpleStringProperty(userPassword);
        this.isAdmin = new SimpleIntegerProperty(isAdmin);
        this.adminRights = new SimpleStringProperty(adminRights);
        this.email = new SimpleStringProperty(email);
        this.fullName = new SimpleStringProperty(fullName);
    }

    

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.fullName.set(email);
    }
    
    public StringProperty emailProperty() {
        return email;
    }
    
    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }
    
    public StringProperty fullNameProperty() {
        return fullName;
    }
    
    public String getAdminRights() {
        return adminRights.get();
    }

    public void setAdminRights(String adminRights) {
        this.adminRights.set(adminRights);
    }
    
    public StringProperty adminRightsProperty() {
        return adminRights;
    }
    
    /**
     * getter for Id
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter for userLogin
     * @return userLogin
     */
    public String getUserLogin() {
        return userLogin.get();
    }

    /**
     * setter for userLogin
     * @param userLogin 
     */
    public void setUserLogin(String userLogin) {
        this.userLogin.set(userLogin);
    }
    
    public StringProperty userLoginProperty()
    {
        return userLogin;
    }

    /**
     * getter for userPassword
     * @return userPassword
     */
    public String getUserPassword() {
        return userPassword.get();
    }

    /**
     * setter for userPassword
     * @param userPassword 
     */
    public void setUserPassword(String userPassword) {
        this.userPassword.set(userPassword);
    } 
    
    public StringProperty userPasswordProperty()
    {
        return userPassword;
    }

    /**
     * getter for isAdmin
     * @return isAdmin
     */
    public int getIsAdmin() {
        return isAdmin.get();
    }

    /**
     * setter for isAdmin
     * @param isAdmin 
     */
    public void setIsAdmin(int isAdmin) {
        this.isAdmin.set(isAdmin);
    }
    
    public ObservableValue<Integer> isAdminObservable()
    {
        return isAdmin.asObject();
    }

    /**
     * toString Method for User
     * @return userLogin
     */
    @Override
    public String toString()
    {
        return getUserLogin();
    }
    
    
}
