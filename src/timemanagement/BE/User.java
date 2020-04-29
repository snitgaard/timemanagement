/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BE;

/**
 *
 * @author The Cowboys
 */
public class User
{
    private int id;
    private String userLogin;
    private String userPassword;
    private int adminId;

    
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
     * @param adminId
     */
    public User(int id, String userLogin, String userPassword, int adminId) {
        this.id = id;
        this.userLogin = userLogin;
        this.userPassword = userPassword;
        this.adminId = adminId;
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

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
    
    
}
