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

    /**
     * Checks if the role of a user is "Admin" or "User".
     * Also checks if the login credentials that the user put in are correct.
     * @param userLogin
     * @param userPassword
     * @param isAdmin
     * @return
     * @throws DalException 
     */
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

    /**
     * Returns a list of all projects in the database
     * @return
     * @throws DalException 
     */
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

    /**
     * Returns a specific user from the database
     * @param userLogin
     * @return
     * @throws DalException 
     */
    @Override
    public User getSpecificUser(String userLogin) throws DalException
    {
        return userDAO.getSpecificUser(userLogin);
    }

    /**
     * Returns a list of all tasks in the database.
     * @return
     * @throws DalException 
     */
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

    /**
     * Creates a task in the database with the given parameters.
     * @param taskName
     * @param projectId
     * @param usedTime
     * @param date
     * @param description
     * @param payed
     * @param projectName
     * @param isDeleted
     * @param userId
     * @return
     * @throws DalException 
     */
    @Override
    public Task createTask(String taskName, int projectId, long usedTime, String date, String description, int payed, String projectName, int isDeleted, int userId) throws DalException
    {
        return taskDAO.createTask(taskName, projectId, usedTime, date, description, payed, projectName, isDeleted, userId);
    }

    /**
     * Adds time spent to the selected task.
     * @param usedTime
     * @param id
     * @throws DalException 
     */
    @Override
    public void addTime(long usedTime, int id) throws DalException
    {
        taskDAO.addTime(usedTime, id);
    }

    /**
     * Adds time spent to the selected task and rounds up to the nearest 15 minutes.
     * @param usedTime
     * @param id
     * @throws DalException 
     */
    @Override
    public void addRoundedTime(double usedTime, int id) throws DalException
    {
        taskDAO.addRoundedTime(usedTime, id);
    }

    /**
     * Updates the time spent on a task based on manual user input.
     * @param task
     * @return
     * @throws DalException 
     */
    @Override
    public boolean updateTask(Task task) throws DalException
    {
        return taskDAO.updateTask(task);
    }

    /**
     * Returns a list of all tasks in the database and their project name.
     * @return
     * @throws DalException 
     */
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

    /**
     * Creates a project in the database with the given parameters.
     * @param projectName
     * @param clientId
     * @param startDate
     * @param usedTime
     * @param isDeleted
     * @param clientName
     * @param hourlyRate
     * @param rounded
     * @return
     * @throws DalException 
     */
    @Override
    public Project createProject(String projectName, int clientId, String startDate,
            long usedTime, int isDeleted, String clientName, double hourlyRate, int rounded) throws DalException
    {
        return projectDAO.createProject(projectName, clientId, startDate, usedTime, isDeleted, clientName, hourlyRate, rounded);
    }


    /**
     * Creates a user in the database with the given parameters
     * @param userLogin
     * @param userPassword
     * @param isAdmin
     * @param email
     * @param fullName
     * @return
     * @throws DalException 
     */
    @Override
    public User createUser(String userLogin, String userPassword, int isAdmin, String email, String fullName) throws DalException
    {
        return userDAO.createUser(userLogin, userPassword, isAdmin, email, fullName);
    }

    /**
     * Returns the id of a client based on client name.
     * @param clientName
     * @return
     * @throws DalException 
     */
    @Override
    public int getClientId(String clientName) throws DalException
    {
        return clientDAO.getClientId(clientName);
    }

    /**
     * Returns a list of all projects in the database and the name of their client.
     * @return
     * @throws DalException 
     */
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

    /**
     * Returns a list of all users in the database.
     * @return
     * @throws DalException 
     */
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

    /**
     * Updates time spent on a project in the database.
     * @param project
     * @throws DalException 
     */
    @Override
    public void updateProjectTime(Project project) throws DalException
    {
        projectDAO.updateProjectTime(project);
    }

    /**
     * Deletes a user from the database.
     * @param user
     * @throws DalException 
     */
    @Override
    public void deleteUser(User user) throws DalException
    {
        userDAO.deleteUser(user);
    }

    /**
     * Returns 0 or 1 depending on if the user is an admin or not.
     * @param userLogin
     * @param userPassword
     * @return
     * @throws DalException 
     */
    @Override
    public int getIsAdminInt(String userLogin, String userPassword) throws DalException
    {
        return userDAO.getIsAdminInt(userLogin, userPassword);
    }

    /**
     * Updates the role of a user in the database. Can either be an admin or user.
     * @param user
     * @param isAdmin
     * @throws DalException 
     */
    public void updateUserRoles(User user, int isAdmin) throws DalException
    {
        userDAO.updateUserRoles(user, isAdmin);
    }

    /**
     * Edits the parameters of a task in the database.
     * @param task
     * @throws DalException 
     */
    @Override
    public void editTask(Task task) throws DalException
    {
        taskDAO.editTask(task);
    }

    /**
     * Returns a list of all clients in the database.
     * @return
     * @throws DalException 
     */
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

    /**
     * Creates a client in the database with the given parameters.
     * @param clientName
     * @param contactPerson
     * @param email
     * @param hourlyRate
     * @param isDeleted
     * @return
     * @throws DalException 
     */
    @Override
    public Client createClient(String clientName, String contactPerson, String email, double hourlyRate, int isDeleted) throws DalException
    {
        return clientDAO.createClient(clientName, contactPerson, email, hourlyRate, isDeleted);
    }

    /**
     * Returns a list of all tasks in the database and their project name.
     * @return
     * @throws DalException 
     */
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

    /**
     * Archives a project in the database. 
     * @param project
     * @param isDeleted
     * @throws DalException 
     */
    @Override
    public void deleteProject(Project project, int isDeleted) throws DalException
    {
        projectDAO.deleteProject(project, isDeleted);
    }

    /**
     * Archives a task in the database.
     * @param client
     * @param isDeleted
     * @throws DalException 
     */
    @Override
    public void deleteTask(Task task, int isDeleted) throws DalException
    {
        taskDAO.deleteTask(task, isDeleted);
    }

    /**
     * Archives a client in the database.
     * @param client
     * @param isDeleted
     * @throws DalException 
     */
    @Override
    public void deleteClient(Client client, int isDeleted) throws DalException
    {
        clientDAO.deleteClient(client, isDeleted);
    }

    /**
     * Archives all tasks on a given project.
     * @param task
     * @param isDeleted
     * @param projectId
     * @throws DalException 
     */
    @Override
    public void deleteTaskOnProject(Task task, int isDeleted, int projectId) throws DalException
    {
        taskDAO.deleteTaskOnProject(task, isDeleted, projectId);
    }

    /**
     * Archives all projects on a given client.
     * @param project
     * @param isDeleted
     * @param clientId
     * @return
     * @throws DalException 
     */
    @Override
    public Project deleteProjectOnClient(Project project, int isDeleted, int clientId) throws DalException
    {
        return projectDAO.deleteProjectOnClient(project, isDeleted, clientId);
    }

}
