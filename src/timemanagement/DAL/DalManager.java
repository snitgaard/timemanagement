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
import timemanagement.BE.Project;
import timemanagement.BE.Task;
import timemanagement.BE.User;
import timemanagement.DAL.database.AdminDAO;
import timemanagement.DAL.database.ProjectDAO;
import timemanagement.DAL.database.TaskDAO;
import timemanagement.DAL.database.UserDAO;

/**
 *
 * @author Mads
 */
public class DalManager implements DalFacade {
    
    private final UserDAO userDAO; 
    private final AdminDAO adminDAO;
    private final ProjectDAO projectDAO;
    private final TaskDAO taskDAO;

    public DalManager() throws IOException {
        userDAO = new UserDAO();
        adminDAO = new AdminDAO();
        projectDAO = new ProjectDAO();
        taskDAO = new TaskDAO();
        
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
    public List<Project> getAllProjects() throws DalException {
        try {
            return projectDAO.getAllProjects();
        } catch (SQLException ex) {
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

    @Override
    public List<String> getAllTasks() throws DalException
    {
        try
        {
            return taskDAO.getAllTasks();
        } catch (SQLException ex)
        {
            Logger.getLogger(DalManager.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void deleteTask(Task task) throws DalException
    {
        taskDAO.deleteTask(task);
    }

    @Override
    public boolean createTask() throws DalException
    {
        return taskDAO.createTask();
    }
    
    
    
}
