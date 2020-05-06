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
    private LongProperty hourlyRate;
    private StringProperty adminRights; 

    
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
     * @param hourlyRate
     * @param adminRights
     */
    public User(int id, String userLogin, String userPassword, int isAdmin, long hourlyRate, String adminRights) {
        this.id = id;
        this.userLogin = new SimpleStringProperty(userLogin);
        this.userPassword = new SimpleStringProperty(userPassword);
        this.isAdmin = new SimpleIntegerProperty(isAdmin);
        this.hourlyRate = new SimpleLongProperty(hourlyRate);   
        this.adminRights = new SimpleStringProperty(adminRights);
    }

    

    
    public String getAdminRights() {
        return adminRights.get();
    }

    public void setAdminRights(String adminRights) {
        this.adminRights.set(adminRights);
    }
    
    public StringProperty adminRighsProperty() {
        return adminRights;
    }

    /**
     * getter for hourlyRate
     * @return hourlyRate
     */
    public long getHourlyRate() {
        return hourlyRate.get();
    }

    /**
     * setter for hourlyRate
     * @param hourlyRate 
     */
    public void setHourlyRate(long hourlyRate) {
        this.hourlyRate.set(hourlyRate);
    }
    
    public ObservableValue<Long> hourlyRateObservable()
    {
        return hourlyRate.asObject();
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
