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

    boolean checkUserCredentials(String userLogin, String userPassword, int isAdmin) throws bllException;

    List<User> getUser(String userLogin) throws bllException;

    User getSpecificUser(String userLogin) throws bllException;

    public List<Project> getAllProjects() throws bllException;

    List<Task> getAllTasks() throws bllException;

    Task createTask(String taskName, int projectId, long usedTime, String date, String description, int betalt, String projectName, int isDeleted, int userId) throws bllException;

    void addTime(long usedTime, int id) throws bllException;

    void addRoundedTime(double usedTime, int id) throws bllException;

    List<Task> getAllTasksProjectName() throws bllException;

    Project createProject(String projectName, int clientId, String startDate, long usedTiem, int isDeleted, String clientName, double hourlyRate, int rounded) throws bllException;

    List<Project> getProjectClientName() throws bllException;

    User createUser(String userLogin, String userPassword, int isAdmin, String email, String fullName) throws bllException;

    Client createClient(String clientName, String contactPerson, String email, double hourlyRate, int isDeleted) throws bllException;

    List<Client> getAllClients() throws bllException;

    int getClientId(String clientName) throws bllException;

    List<User> getAllUsers() throws bllException;

    void updateProjectTime(Project project) throws bllException;

    boolean updateTask(Task task) throws bllException;

    void deleteUser(User user) throws bllException;

    int getIsAdminInt(String userLogin, String userPassword) throws bllException;

    void editTask(Task task) throws bllException;

    void updateUserRoles(User user, int isAdmin) throws bllException;

    List<Task> getAllTasksOnProject(int projectId) throws bllException;

    String timeFormatter(String startTime, String endTime) throws bllException;

    long timeCalculator(String startTime, String endTime) throws bllException;

    void deleteProject(Project project, int isDeleted) throws bllException;

    void deleteTask(Task task, int isDeleted) throws bllException;

    void deleteClient(Client client, int isDeleted) throws bllException;

    void deleteTaskOnProject(Task task, int isDeleted, int projectId) throws bllException;

    Project deleteProjectOnClient(Project project, int isDeleted, int clientId) throws bllException;
}
