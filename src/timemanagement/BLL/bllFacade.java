/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BLL;

import java.util.List;
import timemanagement.BE.Client;
import timemanagement.BE.Project;
import timemanagement.BE.Task;
import timemanagement.BE.User;
import timemanagement.DAL.DalException;

/**
 *
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

    List<Task> getAllTasksProjektNavn() throws bllException;

    Project createProjekt(String projectName, int clientId, String startDate, long usedTiem, int isDeleted, String clientName, double hourlyRate, int rounded) throws bllException;

    List<Project> getProjectKundeNavn() throws bllException;
    
    User createUser(String userLogin, String userPassword, int isAdmin, String email, String fullName) throws bllException;

    Client createKunde(String clientNAme, String contactPerson, String email, double hourlyRate, int isDeleted) throws bllException;

    List<Client> getAllKunder() throws bllException;
    
    int getKundeId(String clientName) throws bllException;

    List<User> getAllUsers() throws bllException;
    
    void updateProjectTime(Project project) throws bllException;
    
    boolean updateTask(Task task) throws bllException;
    
    void deleteUser(User user) throws bllException;
    
    int getIsAdminInt(String userLogin, String userPassword) throws bllException;
    
    void archiveProject(Project project) throws bllException;
    
    void editTask(Task task) throws bllException;
    
    void updateUserRoles(User user, int isAdmin) throws bllException;
    
    List<Task> getAllTasksOnProject(int projektId) throws bllException;
    
    String timeFormatter(String startTime, String endTime) throws bllException;
    
    long timeCalculator(String startTime, String endTime) throws bllException;
    
    void deleteProject(Project project, int isDeleted) throws bllException;
    
    void deleteTask(Task task, int isDeleted) throws bllException;
    
    void deleteKunde(Client client, int isDeleted) throws bllException;
    
    void deleteTaskOnProject(Task task, int isDeleted, int projectId) throws bllException;
    
    Project deleteProjectOnClient(Project project, int isDeleted, int clientId) throws bllException;
}
