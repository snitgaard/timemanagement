/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BE;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author The Cowboys
 */
public class User
{
    private int id;
    private String userLogin;
    private String userPassword;
    private int isAdmin;
    private long hourlyRate;
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
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.isAdmin = isAdmin;
        this.hourlyRate = hourlyRate;   
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
        return hourlyRate;
    }

    /**
     * setter for hourlyRate
     * @param hourlyRate 
     */
    public void setHourlyRate(long hourlyRate) {
        this.hourlyRate = hourlyRate;
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
        return userLogin;
    }

    /**
     * setter for userLogin
     * @param userLogin 
     */
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    /**
     * getter for userPassword
     * @return userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * setter for userPassword
     * @param userPassword 
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    } 

    /**
     * getter for isAdmin
     * @return isAdmin
     */
    public int getIsAdmin() {
        return isAdmin;
    }

    /**
     * setter for isAdmin
     * @param isAdmin 
     */
    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * toString Method for User
     * @return userLogin
     */
    @Override
    public String toString()
    {
        return userLogin;
    }
    
    
}
