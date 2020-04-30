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
import timemanagement.BE.Kunde;
import timemanagement.BE.Project;
import timemanagement.BE.Task;
import timemanagement.BE.User;
import timemanagement.DAL.database.AdminDAO;
import timemanagement.DAL.database.KundeDAO;
import timemanagement.DAL.database.ProjectDAO;
import timemanagement.DAL.database.TaskDAO;
import timemanagement.DAL.database.UserDAO;

/**
 *
 * @author Mads
 */
public class DalManager implements DalFacade
{

    private final UserDAO userDAO;
    private final AdminDAO adminDAO;
    private final ProjectDAO projectDAO;
    private final TaskDAO taskDAO;
    private final KundeDAO kundeDAO;

    public DalManager() throws IOException
    {
        userDAO = new UserDAO();
        adminDAO = new AdminDAO();
        projectDAO = new ProjectDAO();
        taskDAO = new TaskDAO();
        kundeDAO = new KundeDAO();

    }

    @Override
    public boolean checkUserCredentials(String userLogin, String userPassword) throws DalException
    {
        try
        {
            return userDAO.checkUserCredentials(userLogin, userPassword);
        } catch (DalException ex)

        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public List<User> getUser(String userLogin) throws DalException
    {
        try
        {
            return userDAO.getUser(userLogin);
        } catch (DalException ex)
        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public List<Project> getAllProjects() throws DalException
    {
        try
        {
            return projectDAO.getAllProjects();
        } catch (SQLException ex)
        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public User getSpecificUser(String userLogin) throws DalException
    {
        return userDAO.getSpecificUser(userLogin);
    }

    @Override
    public boolean checkAdminCredentials(String adminLogin, String adminPassword) throws DalException
    {
        return adminDAO.checkAdminCredentials(adminLogin, adminPassword);
    }

    @Override
    public List<Admin> getAdmin(String adminLogin) throws DalException
    {
        return adminDAO.getAdmin(adminLogin);
    }

    @Override
    public Admin getSpecificAdmin(String adminLogin) throws DalException
    {
        return adminDAO.getSpecificAdmin(adminLogin);
    }

    @Override
    public List<Task> getAllTasks() throws DalException
    {
        try
        {
            return taskDAO.getAllTasks();
        } catch (SQLException ex)
        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public void deleteTask(Task task) throws DalException
    {
        taskDAO.deleteTask(task);
    }

    @Override
    public boolean createTask(String opgaveNavn, int projektId, long brugtTid, String dato, String beskrivelse, int betalt) throws DalException
    {
        return taskDAO.createTask(opgaveNavn, projektId, brugtTid, dato, beskrivelse, betalt);
    }

    @Override
    public void addTime(long brugtTid, String opgaveNavn) throws DalException {
        taskDAO.addTime(brugtTid, opgaveNavn);
    }
    
    
    
    public List<Task> getAllTasksProjektNavn() throws DalException
    {
        try
        {
            return taskDAO.getAllTasksProjektNavn();
        } catch (SQLException ex)
        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public boolean createProject(String projektNavn, int kundeId, String startDato) throws DalException {
        return projectDAO.createProject(projektNavn, kundeId, startDato);
    }

    @Override
    public List<Task> getAllTasksByProject(int projektId) throws DalException {
        try {
            return taskDAO.getAllTasksByProject(projektId);
        } catch (SQLException ex) {
            throw new DalException(ex.getMessage());
        }
    }
    
    @Override
    public boolean createUser(String userLogin, String userPassword, String adminId) throws DalException
    {
        return userDAO.createUser(userLogin, userPassword, adminId);
    }    

    @Override
    public boolean createUserAdmin(String userLogin, String userPassword, int adminId) throws DalException
    {
        return userDAO.createUserAdmin(userLogin, userPassword, adminId);
    }   
    
    @Override
    public boolean createAdmin(String adminLogin, String adminPassword) throws DalException
    {
        return adminDAO.createAdmin(adminLogin, adminPassword);
    }
    
    @Override
    public boolean createKunde(String kundeNavn) throws DalException
    {
        return kundeDAO.createKunde(kundeNavn);
    }
    
    @Override
    public int getKundeId(String kundeNavn) throws DalException
    {
        return kundeDAO.getKundeId(kundeNavn);
    }
    
    @Override
    public int getAdminId(String adminLogin) throws DalException
    {
        return adminDAO.getAdminId(adminLogin);
    }

    @Override
    public List<Project> getProjectKundeNavn() throws DalException
    {
        try
        {
            return projectDAO.getProjectKundeNavn();
        } catch (SQLException ex)
        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public List<Admin> getAllAdmins() throws DalException
    {
        try
        {
            return adminDAO.getAllAdmins();
        } catch (SQLException ex)
        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() throws DalException
    {
        try
        {
            return userDAO.getAllUsers();
        } catch (SQLException ex)
        {
            throw new DalException(ex.getMessage());
        }
    }
}
