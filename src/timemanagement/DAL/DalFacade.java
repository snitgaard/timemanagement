/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.DAL;

import java.util.List;

import timemanagement.BE.Kunde;
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
    
    void deleteTask(Task task) throws DalException;
    
    Task createTask(String opgaveNavn, int projektId, long brugtTid, String dato, String beskrivelse, int betalt, String projektNavn, int ongoing, int userId) throws DalException;
    
    void addTime(long brugtTid, String opgaveNavn) throws DalException;
    
    List<Task> getAllTasksProjektNavn() throws DalException;
    
    Project createProject(String projektNavn, int kundeId, String startDato, long brugtTid, int ongoing, String kundeNavn, double hourlyRate) throws DalException;
    
    void deleteProject(Project project) throws DalException;
    
    List<Project> getProjectKundeNavn() throws DalException;
    
    User createUser(String userLogin, String userPassword, int isAdmin) throws DalException;
    
    User createUserAdmin(String userLogin, String userPassword, int isAdmin) throws DalException;

    Kunde createKunde(String kundeNavn, String kontaktPerson, String email, Double hourlyRate) throws DalException;
    
    List<Kunde> getAllKunder() throws DalException;
    
    int getKundeId (String kundeNavn) throws DalException;
    
    List<User> getAllUsers() throws DalException;
    
    void updateProjectTime(Project project) throws DalException;
    
    boolean updateTask(Task task) throws DalException;
    
    void deleteUser(User user) throws DalException;
    
    int getIsAdminInt(String userLogin, String userPassword) throws DalException;
    
    void archiveProject(Project project) throws DalException;
    
    void editTask(Task task) throws DalException;
    
    void updateUserRoles(User user) throws DalException;
    
}
