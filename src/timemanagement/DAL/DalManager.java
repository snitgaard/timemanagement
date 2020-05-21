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

import timemanagement.DAL.database.ClientDAO;
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
    private final ClientDAO kundeDAO;

    public DalManager() throws IOException
    {
        userDAO = new UserDAO();
        projectDAO = new ProjectDAO();
        taskDAO = new TaskDAO();
        kundeDAO = new ClientDAO();

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
    public Task createTask(String opgaveNavn, int projektId, long brugtTid, String dato, String beskrivelse, int betalt, String projektNavn, int isDeleted, int userId) throws DalException
    {
        return taskDAO.createTask(opgaveNavn, projektId, brugtTid, dato, beskrivelse, betalt, projektNavn, isDeleted, userId);
    }

    @Override
    public void addTime(long brugtTid, int id) throws DalException {
        taskDAO.addTime(brugtTid, id);
    }
    
    @Override
    public void addRoundedTime(double brugtTid, int id) throws DalException {
        taskDAO.addRoundedTime(brugtTid, id);
    }
    
    @Override
    public boolean updateTask(Task task) throws DalException {
        return taskDAO.updateTask(task);
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
    public Project createProject(String projektNavn, int kundeId, String startDato, long brugtTid, int isDeleted, String kundeNavn, double hourlyRate, int rounded) throws DalException {
        return projectDAO.createProject(projektNavn, kundeId, startDato, brugtTid, isDeleted, kundeNavn, hourlyRate, rounded);
    }

    
    @Override
    public User createUser(String userLogin, String userPassword, int isAdmin, String email, String fullName) throws DalException
    {
        return userDAO.createUser(userLogin, userPassword, isAdmin, email, fullName);
    }    
    
    @Override
    public int getKundeId(String kundeNavn) throws DalException
    {
        return kundeDAO.getClientId(kundeNavn);
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
    public void updateProjectTime(Project project) throws DalException {
        projectDAO.updateProjectTime(project);
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

    public void updateUserRoles(User user, int isAdmin) throws DalException
    {
        userDAO.updateUserRoles(user, isAdmin);
    }

    @Override
    public void editTask(Task task) throws DalException
    {
        taskDAO.editTask(task);
    }

    @Override
    public List<Kunde> getAllKunder() throws DalException
    {
        try
        {
            return kundeDAO.getAllClients();
        } catch (SQLException ex)
        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public Kunde createKunde(String kundeNavn, String kontaktPerson, String email, double hourlyRate, int isDeleted) throws DalException
    {
        return kundeDAO.createClient(kundeNavn, kontaktPerson, email, hourlyRate, isDeleted);
    }

    @Override
    public List<Task> getAllTasksOnProject(int projektId) throws DalException {
        try {
            return taskDAO.getAllTasksOnProject(projektId);
        } catch (SQLException ex) {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public void deleteProject(Project project, int isDeleted) throws DalException {
        projectDAO.deleteProject(project, isDeleted);
    }

    @Override
    public void deleteTask(Task task, int isDeleted) throws DalException {
        taskDAO.deleteTask(task, isDeleted);
    }

    @Override
    public void deleteKunde(Kunde kunde, int isDeleted) throws DalException {
       kundeDAO.deleteClient(kunde, isDeleted);
    }

    @Override
    public void deleteTaskOnProject(Task task, int isDeleted, int projektId) throws DalException {
        taskDAO.deleteTaskOnProject(task, isDeleted, projektId);
    }

    @Override
    public Project deleteProjectOnClient(Project project, int isDeleted, int kundeId) throws DalException {
        return projectDAO.deleteProjectOnClient(project, isDeleted, kundeId);
    }

}
