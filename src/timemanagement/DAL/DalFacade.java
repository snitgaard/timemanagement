/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.DAL;

import java.util.List;

import timemanagement.BE.Client;
import timemanagement.BE.Project;
import timemanagement.BE.Task;
import timemanagement.BE.User;

/**
 *
 * @author Mads
 */
public interface DalFacade {
    
    boolean checkUserCredentials(String userLogin, String userPassword, int isAdmin) throws DalException;

    
    List<User> getUser(String userLogin) throws DalException;
    
    List<Project> getAllProjects() throws DalException;
    
    User getSpecificUser(String userLogin) throws DalException;

    List<Task> getAllTasks() throws DalException;
    
    Task createTask(String taskName, int projectId, long usedTime, String date, String description, int payed, String projectName, int isDeleted, int userId) throws DalException;
    
    void addTime(long usedTime, int id) throws DalException;
    
    void addRoundedTime(double usedTime, int id) throws DalException;
    
    boolean updateTask(Task task) throws DalException;
    
    List<Task> getAllTasksProjectName() throws DalException;
    
    Project createProject(String projectName, int clientId, String startDate, long usedTime, int isDeleted, String clientName, double hourlyRate, int rounded) throws DalException;

    
    List<Project> getProjectClientName() throws DalException;
    
    User createUser(String userLogin, String userPassword, int isAdmin, String email, String fullName) throws DalException;

    Client createClient(String clientName, String contactPerson, String email, double hourlyRate, int isDeleted) throws DalException;
    
    List<Client> getAllClients() throws DalException;
    
    int getClientId (String clientName) throws DalException;
    
    List<User> getAllUsers() throws DalException;
    
    void updateProjectTime(Project project) throws DalException;
    
    void deleteUser(User user) throws DalException;
    
    int getIsAdminInt(String userLogin, String userPassword) throws DalException;
    
    void archiveProject(Project project) throws DalException;
    
    void editTask(Task task) throws DalException;
    
    void updateUserRoles(User user, int isAdmin) throws DalException;
    
    List<Task> getAllTasksOnProject(int projectId) throws DalException;
    
    void deleteProject(Project project, int isDeleted) throws DalException;
    
    void deleteTask(Task task, int isDeleted) throws DalException;
    
    void deleteClient(Client client, int isDeleted) throws DalException;
    
    void deleteTaskOnProject(Task task, int isDeleted, int projectId) throws DalException;
    
    Project deleteProjectOnClient(Project project, int isDeleted, int clientId) throws DalException;
}
