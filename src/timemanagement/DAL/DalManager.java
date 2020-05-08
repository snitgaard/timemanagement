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
import timemanagement.BE.Kunde;
import timemanagement.BE.Project;
import timemanagement.BE.Task;
import timemanagement.BE.User;

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
    private final ProjectDAO projectDAO;
    private final TaskDAO taskDAO;
    private final KundeDAO kundeDAO;

    public DalManager() throws IOException
    {
        userDAO = new UserDAO();
        projectDAO = new ProjectDAO();
        taskDAO = new TaskDAO();
        kundeDAO = new KundeDAO();

    }

    @Override
    public boolean checkUserCredentials(String userLogin, String userPassword, int isAdmin) throws DalException
    {
        try
        {
            return userDAO.checkUserCredentials(userLogin, userPassword, isAdmin);
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
    public Project createProject(String projektNavn, int kundeId, String startDato, long brugtTid, int ongoing, long brugtTidMinutter, String kundeNavn) throws DalException {
        return projectDAO.createProject(projektNavn, kundeId, startDato, brugtTid, ongoing, brugtTidMinutter, kundeNavn);
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
    public User createUser(String userLogin, String userPassword, int isAdmin) throws DalException
    {
        return userDAO.createUser(userLogin, userPassword, isAdmin);
    }    

    @Override
    public User createUserAdmin(String userLogin, String userPassword, int isAdmin) throws DalException
    {
        return userDAO.createUserAdmin(userLogin, userPassword, isAdmin);
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

    @Override
    public void updateProjectTime() throws DalException {
        projectDAO.updateProjectTime();
    }

    @Override
    public boolean updateTask(Task task) throws DalException {
        return taskDAO.updateTask(task);
    }

    @Override
    public void deleteUser(User user) throws DalException
    {
        userDAO.deleteUser(user);
    }

    @Override
    public int getIsAdminInt(String userLogin, String userPassword) throws DalException {
        return userDAO.getIsAdminInt(userLogin, userPassword);
    }

    @Override
    public void archiveProject(Project project) throws DalException {
        projectDAO.archiveProject(project);
    }

    public void updateUserRoles(User user) throws DalException
    {
        userDAO.updateUserRoles(user);
    }

    @Override
    public boolean editTask(String opgaveNavn, String beskrivelse, int betalt, String opgaveTitel) throws DalException
    {
        return taskDAO.editTask(opgaveNavn, beskrivelse, betalt, opgaveTitel);
    }

    @Override
    public List<Kunde> getAllKunder() throws DalException
    {
        try
        {
            return kundeDAO.getAllKunder();
        } catch (SQLException ex)
        {
            throw new DalException(ex.getMessage());
        }
    }
 
}
