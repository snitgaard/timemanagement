/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.DAL;

import timemanagement.BE.*;
import timemanagement.DAL.database.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Mads
 */
public class DalManager implements DalFacade
{

    private final UserDAO userDAO;
    private final ProjectDAO projectDAO;
    private final TaskDAO taskDAO;
    private final ClientDAO clientDAO;

    public DalManager() throws IOException
    {
        userDAO = new UserDAO();
        projectDAO = new ProjectDAO();
        taskDAO = new TaskDAO();
        clientDAO = new ClientDAO();

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
    public Task createTask(String taskName, int projectId, long usedTime, String date, String description, int paid, String projectName, int isDeleted, int userId) throws DalException
    {
        return taskDAO.createTask(taskName, projectId, usedTime, date, description, paid, projectName, isDeleted, userId);
    }

    @Override
    public void addTime(long usedTime, int id) throws DalException
    {
        taskDAO.addTime(usedTime, id);
    }

    @Override
    public void addRoundedTime(double usedTime, int id) throws DalException
    {
        taskDAO.addRoundedTime(usedTime, id);
    }

    @Override
    public boolean updateTask(Task task) throws DalException
    {
        return taskDAO.updateTask(task);
    }

    public List<Task> getAllTasksProjectName() throws DalException
    {
        try
        {
            return taskDAO.getAllTasksProjectName();
        } catch (SQLException ex)
        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public Project createProject(String projectName, int clientId, String startDate, long usedTime, int isDeleted, String clientName, double hourlyRate, int rounded) throws DalException
    {
        return projectDAO.createProject(projectName, clientId, startDate, usedTime, isDeleted, clientName, hourlyRate, rounded);
    }


    @Override
    public User createUser(String userLogin, String userPassword, int isAdmin, String email, String fullName) throws DalException
    {
        return userDAO.createUser(userLogin, userPassword, isAdmin, email, fullName);
    }

    @Override
    public int getClientId(String clientName) throws DalException
    {
        return clientDAO.getClientId(clientName);
    }

    @Override
    public List<Project> getProjectClientName() throws DalException
    {
        try
        {
            return projectDAO.getProjectClientName();
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
    public void updateProjectTime(Project project) throws DalException
    {
        projectDAO.updateProjectTime(project);
    }


    @Override
    public void deleteUser(User user) throws DalException
    {
        userDAO.deleteUser(user);
    }

    @Override
    public int getIsAdminInt(String userLogin, String userPassword) throws DalException
    {
        return userDAO.getIsAdminInt(userLogin, userPassword);
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
    public List<Client> getAllClients() throws DalException
    {
        try
        {
            return clientDAO.getAllClients();
        } catch (SQLException ex)
        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public Client createClient(String clientName, String contactPerson, String email, double hourlyRate, int isDeleted) throws DalException
    {
        return clientDAO.createClient(clientName, contactPerson, email, hourlyRate, isDeleted);
    }

    @Override
    public List<Task> getAllTasksOnProject(int projectId) throws DalException
    {
        try
        {
            return taskDAO.getAllTasksOnProject(projectId);
        } catch (SQLException ex)
        {
            throw new DalException(ex.getMessage());
        }
    }

    @Override
    public void deleteProject(Project project, int isDeleted) throws DalException
    {
        projectDAO.deleteProject(project, isDeleted);
    }

    @Override
    public void deleteTask(Task task, int isDeleted) throws DalException
    {
        taskDAO.deleteTask(task, isDeleted);
    }

    @Override
    public void deleteClient(Client client, int isDeleted) throws DalException
    {
        clientDAO.deleteClient(client, isDeleted);
    }

    @Override
    public void deleteTaskOnProject(Task task, int isDeleted, int projectId) throws DalException
    {
        taskDAO.deleteTaskOnProject(task, isDeleted, projectId);
    }

    @Override
    public Project deleteProjectOnClient(Project project, int isDeleted, int clientId) throws DalException
    {
        return projectDAO.deleteProjectOnClient(project, isDeleted, clientId);
    }

}
