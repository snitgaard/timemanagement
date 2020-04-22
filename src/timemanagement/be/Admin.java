/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.be;

/**
 *
 * @author The Cowboys
 */
public class Admin extends User
{

    /**
     * Constructor for Admin BE
     * @param id 
     */
    
    private String adminLogin;
    private String adminPassword;
    
    public Admin(int id) {
        super(id);
    }

    public String getAdminLogin() {
        return adminLogin;
    }

    public void setAdminLogin(String adminLogin) {
        this.adminLogin = adminLogin;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
    
    

    
}
