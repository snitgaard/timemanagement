/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.DAL;

import timemanagement.BE.*;

import java.util.List;

/**
 * @author The Cowboys
 */
public interface DalFacade
{

    /**
     * Checks if the role of a user is "Admin" or "User".
     * Also checks if the login credentials that the user put in are correct.
     * @param userLogin
     * @param userPassword
     * @param isAdmin
     * @return
     * @throws DalException 
     */
    boolean checkUserCredentials(String userLogin, String userPassword, int isAdmin) throws DalException;

    /**
     * Returns a list of all projects in the database
     * @return
     * @throws DalException 
     */
    List<Project> getAllProjects() throws DalException;

    /**
     * Returns a specific user from the database
     * @param userLogin
     * @return
     * @throws DalException 
     */
    User getSpecificUser(String userLogin) throws DalException;

    /**
     * Returns a list of all tasks in the database.
     * @return
     * @throws DalException 
     */
    List<Task> getAllTasks() throws DalException;

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
    Task createTask(String taskName, int projectId, long usedTime, String date, String description, int payed, String projectName, int isDeleted, int userId) throws DalException;

    /**
     * Adds time spent to the selected task.
     * @param usedTime
     * @param id
     * @throws DalException 
     */
    void addTime(long usedTime, int id) throws DalException;

    /**
     * Adds time spent to the selected task and rounds up to the nearest 15 minutes.
     * @param usedTime
     * @param id
     * @throws DalException 
     */
    void addRoundedTime(double usedTime, int id) throws DalException;

    /**
     * Updates the time spent on a task based on manual user input.
     * @param task
     * @return
     * @throws DalException 
     */
    boolean updateTask(Task task) throws DalException;

    /**
     * Returns a list of all tasks in the database and their project name.
     * @return
     * @throws DalException 
     */
    List<Task> getAllTasksProjectName() throws DalException;

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
    Project createProject(String projectName, int clientId, String startDate, long usedTime, int isDeleted, String clientName, double hourlyRate, int rounded) throws DalException;

    /**
     * Returns a list of all projects in the database and the name of their client.
     * @return
     * @throws DalException 
     */
    List<Project> getProjectClientName() throws DalException;

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
    User createUser(String userLogin, String userPassword, int isAdmin, String email, String fullName) throws DalException;

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
    Client createClient(String clientName, String contactPerson, String email, double hourlyRate, int isDeleted) throws DalException;

    /**
     * Returns a list of all clients in the database.
     * @return
     * @throws DalException 
     */
    List<Client> getAllClients() throws DalException;

    /**
     * Returns the id of a client based on client name.
     * @param clientName
     * @return
     * @throws DalException 
     */
    int getClientId(String clientName) throws DalException;

    /**
     * Returns a list of all users in the database.
     * @return
     * @throws DalException 
     */
    List<User> getAllUsers() throws DalException;

    /**
     * Updates time spent on a project in the database.
     * @param project
     * @throws DalException 
     */
    void updateProjectTime(Project project) throws DalException;

    /**
     * Deletes a user from the database.
     * @param user
     * @throws DalException 
     */
    void deleteUser(User user) throws DalException;

    /**
     * Returns 0 or 1 depending on if the user is an admin or not.
     * @param userLogin
     * @param userPassword
     * @return
     * @throws DalException 
     */
    int getIsAdminInt(String userLogin, String userPassword) throws DalException;

    /**
     * Edits the parameters of a task in the database.
     * @param task
     * @throws DalException 
     */
    void editTask(Task task) throws DalException;

    /**
     * Updates the role of a user in the database. Can either be an admin or user.
     * @param user
     * @param isAdmin
     * @throws DalException 
     */
    void updateUserRoles(User user, int isAdmin) throws DalException;

    /**
     * Returns all tasks on a given project.
     * @param projectId
     * @return
     * @throws DalException 
     */
    List<Task> getAllTasksOnProject(int projectId) throws DalException;

    /**
     * Archives a project in the database. 
     * @param project
     * @param isDeleted
     * @throws DalException 
     */
    void deleteProject(Project project, int isDeleted) throws DalException;

    /**
     * Archives a task in the database.
     * @param task
     * @param isDeleted
     * @throws DalException 
     */
    void deleteTask(Task task, int isDeleted) throws DalException;

    /**
     * Archives a client in the database.
     * @param client
     * @param isDeleted
     * @throws DalException 
     */
    void deleteClient(Client client, int isDeleted) throws DalException;

    /**
     * Archives all tasks on a given project.
     * @param task
     * @param isDeleted
     * @param projectId
     * @throws DalException 
     */
    void deleteTaskOnProject(Task task, int isDeleted, int projectId) throws DalException;

    /**
     * Archives all projects on a given client.
     * @param project
     * @param isDeleted
     * @param clientId
     * @return
     * @throws DalException 
     */
    Project deleteProjectOnClient(Project project, int isDeleted, int clientId) throws DalException;
}
