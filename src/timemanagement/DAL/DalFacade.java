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
    
    boolean createTask(String opgaveNavn, int projektId, long brugtTid, String dato, String beskrivelse, int betalt) throws DalException;
    
    void addTime(long brugtTid, String opgaveNavn) throws DalException;
    
    List<Task> getAllTasksProjektNavn() throws DalException;
    
    Project createProject(String projektNavn, int kundeId, String startDato, long brugtTid, int ongoing, long brugtTidMinutter, String kundeNavn) throws DalException;
    
    List<Task> getAllTasksByProject(int projektId) throws DalException;
    
    List<Project> getProjectKundeNavn() throws DalException;
    
    User createUser(String userLogin, String userPassword, int isAdmin) throws DalException;
    
    User createUserAdmin(String userLogin, String userPassword, int isAdmin) throws DalException;

    boolean createKunde(String kundeNavn) throws DalException;
    
    int getKundeId (String kundeNavn) throws DalException;
    
    List<User> getAllUsers() throws DalException;
    
    void updateProjectTime() throws DalException;
    
    boolean updateTask(int brugtTid, int id) throws DalException;
    
    void deleteUser(User user) throws DalException;
    
    int getIsAdminInt(String userLogin, String userPassword) throws DalException;
    
    void archiveProject(Project project) throws DalException;
    
    boolean editTask(String opgaveNavn, String beskrivelse, int betalt, String opgaveTitel) throws DalException;
    
    void updateUserRoles(User user) throws DalException;
}
