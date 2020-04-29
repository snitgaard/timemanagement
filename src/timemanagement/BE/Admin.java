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
public class Admin extends User
{
    private String adminLogin;
    private String adminPassword;

    /**
     * Constructor for Admin
     * @param adminLogin
     * @param adminPassword
     * @param id 
     */
    public Admin(int id, String adminLogin, String adminPassword) {
        super(id);
        this.adminLogin = adminLogin;
        this.adminPassword = adminPassword;
    }
    
    /**
     * getter for adminLogin
     * @return adminLogin
     */
    public String getAdminLogin() {
        return adminLogin;
    }

    /**
     * setter for adminLogin
     * @param adminLogin 
     */
    public void setAdminLogin(String adminLogin) {
        this.adminLogin = adminLogin;
    }

    /**
     * getter for adminPassword
     * @return adminPassword
     */
    public String getAdminPassword() {
        return adminPassword;
    }

    /**
     * setter for adminPassword
     * @param adminPassword 
     */
    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    
    
    
}
