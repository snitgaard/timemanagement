/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.DAL;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import timemanagement.BE.Admin;
import timemanagement.BE.User;
import timemanagement.DAL.database.AdminDAO;
import timemanagement.DAL.database.UserDAO;

/**
 *
 * @author Mads
 */
public class DalManager implements DalFacade {
    
    private final UserDAO userDAO; 
    private final AdminDAO adminDAO;

    public DalManager() throws IOException {
        userDAO = new UserDAO();
        adminDAO = new AdminDAO();
    }
    
    

    @Override
    public boolean checkUserCredentials(String userLogin, String userPassword) throws DalException {
        try
        {
            return userDAO.checkUserCredentials(userLogin, userPassword);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public List<User> getUser(String userLogin) throws DalException {
        try {
            return userDAO.getUser(userLogin);
        } catch (DalException ex) {
            throw new DalException(ex.getMessage());
        } 
    }

    @Override
    public User getSpecificUser(String userLogin) throws DalException {
        return userDAO.getSpecificUser(userLogin);
    }

    @Override
    public boolean checkAdminCredentials(String adminLogin, String adminPassword) throws DalException {
        return adminDAO.checkAdminCredentials(adminLogin, adminPassword);
    }

    @Override
    public List<Admin> getAdmin(String adminLogin) throws DalException {
        return adminDAO.getAdmin(adminLogin);
    }

    @Override
    public Admin getSpecificAdmin(String adminLogin) throws DalException {
        return adminDAO.getSpecificAdmin(adminLogin);
    }
    
    
    
}
