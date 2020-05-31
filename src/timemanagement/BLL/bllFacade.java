/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BLL;

import timemanagement.BE.*;

import java.util.List;

/**
 * @author The Cowboys
 */
public interface bllFacade
{

    /**
     * Checks if the role of a user is "Admin" or "User".Also checks if the login credentials that the user put in are correct.
     * @param userLogin
     * @param userPassword
     * @param isAdmin
     * @return 
     * @throws timemanagement.BLL.bllException 
     */
    boolean checkUserCredentials(String userLogin, String userPassword, int isAdmin) throws bllException;

    /**
     * Returns a specific user from the database
     * @param userLogin
     * @return 
     * @throws timemanagement.BLL.bllException 
     */
    User getSpecificUser(String userLogin) throws bllException;

    /**
     * Returns a list of all projects in the database
     * @return 
     * @throws timemanagement.BLL.bllException 
     */
    List<Project> getAllProjects() throws bllException;

    /**
     * Returns a list of all tasks in the database.
     * @return 
     * @throws timemanagement.BLL.bllException 
     */
    List<Task> getAllTasks() throws bllException;

    /**
     * Creates a task in the database with the given parameters.
     * @param taskName
     * @param projectId
     * @param usedTime
     * @param date
     * @param description
     * @param betalt
     * @param projectName
     * @param isDeleted
     * @param userId
     * @return 
     * @throws timemanagement.BLL.bllException 
     */
    Task createTask(String taskName, int projectId, long usedTime, String date, String description, int betalt, String projectName, int isDeleted, int userId) throws bllException;

    /**
     * Adds time spent to the selected task.
     * @param usedTime
     * @param id 
     * @throws timemanagement.BLL.bllException 
     */
    void addTime(long usedTime, int id) throws bllException;

    /**
     * Adds time spent to the selected task and rounds up to the nearest 15 minutes.
     * @param usedTime
     * @param id 
     * @throws timemanagement.BLL.bllException 
     */
    void addRoundedTime(double usedTime, int id) throws bllException;

    /**
     * Returns a list of all tasks in the database and their project name.
     * @return 
     * @throws timemanagement.BLL.bllException 
     */
    List<Task> getAllTasksProjectName() throws bllException;

    /**
     * Creates a project in the database with the given parameters.
     * @param projectName
     * @param clientId
     * @param startDate
     * @param usedTiem
     * @param isDeleted
     * @param clientName
     * @param hourlyRate
     * @param rounded
     * @return 
     * @throws timemanagement.BLL.bllException 
     */
    Project createProject(String projectName, int clientId, String startDate, long usedTiem, int isDeleted, String clientName, double hourlyRate, int rounded) throws bllException;

    /**
     * Returns a list of all projects in the database and the name of their client.
     * @return 
     * @throws timemanagement.BLL.bllException 
     */
    List<Project> getProjectClientName() throws bllException;

     /**
     * Creates a user in the database with the given parameters
     * @param userLogin
     * @param userPassword
     * @param isAdmin
     * @param email
     * @param fullName
     * @return 
     * @throws timemanagement.BLL.bllException 
     */
    User createUser(String userLogin, String userPassword, int isAdmin, String email, String fullName) throws bllException;

    /**
     * Creates a client in the database with the given parameters.
     * @param clientName
     * @param contactPerson
     * @param email
     * @param hourlyRate
     * @param isDeleted
     * @return 
     * @throws timemanagement.BLL.bllException 
     */
    Client createClient(String clientName, String contactPerson, String email, double hourlyRate, int isDeleted) throws bllException;

    /**
     * Returns a list of all clients in the database.
     * @return 
     * @throws timemanagement.BLL.bllException 
     */
    List<Client> getAllClients() throws bllException;

    /**
     * Returns the id of a client based on client name.
     * @param clientName
     * @return 
     * @throws timemanagement.BLL.bllException 
     */
    int getClientId(String clientName) throws bllException;

    /**
     * Returns a list of all users in the database.
     * @return 
     * @throws timemanagement.BLL.bllException 
     */
    List<User> getAllUsers() throws bllException;

     /**
     * Updates time spent on a project in the database.
     * @param project 
     * @throws timemanagement.BLL.bllException 
     */
    void updateProjectTime(Project project) throws bllException;

    /**
     * Updates the time spent on a task based on manual user input.
     * @param task
     * @return 
     * @throws timemanagement.BLL.bllException 
     */
    boolean updateTask(Task task) throws bllException;

    /**
     * Deletes a user from the database.
     * @param user
     * @throws timemanagement.BLL.bllException 
     */
    void deleteUser(User user) throws bllException;

    /**
     * Returns 0 or 1 depending on if the user is an admin or not.
     * @param userLogin
     * @param userPassword
     * @return
     * @throws timemanagement.BLL.bllException 
     */
    int getIsAdminInt(String userLogin, String userPassword) throws bllException;

    /**
     * Edits the parameters of a task in the database.
     * @param task
     * @throws timemanagement.BLL.bllException 
     */
    void editTask(Task task) throws bllException;

    /**
     * Updates the role of a user in the database.Can either be an admin or user.
     * @param user
     * @param isAdmin
     * @throws timemanagement.BLL.bllException 
     */
    void updateUserRoles(User user, int isAdmin) throws bllException;

    /**
     * Returns all tasks on a given project.
     * @param projectId
     * @return
     * @throws timemanagement.BLL.bllException 
     */
    List<Task> getAllTasksOnProject(int projectId) throws bllException;

    /**
     * Formats the used time that is shown in the time logger to (HH:mm)
     * @param startTime
     * @param endTime
     * @return
     * @throws bllException 
     */
    String timeFormatter(String startTime, String endTime) throws bllException;

    /**
     * Calculates the time spent on a task.
     * @param startTime
     * @param endTime
     * @return
     * @throws bllException 
     */
    long timeCalculator(String startTime, String endTime) throws bllException;

    /**
     * Archives a project in the database. 
     * @param project
     * @param isDeleted
     * @throws timemanagement.BLL.bllException 
     */
    void deleteProject(Project project, int isDeleted) throws bllException;

    /**
     * Archives a task in the database.
     * @param task
     * @param isDeleted
     * @throws timemanagement.BLL.bllException 
     */
    void deleteTask(Task task, int isDeleted) throws bllException;

    /**
     * Archives a client in the database.
     * @param client
     * @param isDeleted
     * @throws timemanagement.BLL.bllException 
     */
    void deleteClient(Client client, int isDeleted) throws bllException;

    /**
     * Archives all tasks on a given project.
     * @param task
     * @param isDeleted
     * @param projectId 
     * @throws timemanagement.BLL.bllException 
     */
    void deleteTaskOnProject(Task task, int isDeleted, int projectId) throws bllException;

    /**
     * Archives all projects on a given client.
     * @param project
     * @param isDeleted
     * @param clientId
     * @return 
     * @throws timemanagement.BLL.bllException 
     */
    Project deleteProjectOnClient(Project project, int isDeleted, int clientId) throws bllException;
}
