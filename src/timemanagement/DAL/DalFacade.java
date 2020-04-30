/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.DAL;

import java.util.List;
import timemanagement.BE.Admin;
import timemanagement.BE.Kunde;
import timemanagement.BE.Project;
import timemanagement.BE.Task;
import timemanagement.BE.User;

/**
 *
 * @author Mads
 */
public interface DalFacade {
    
    boolean checkUserCredentials(String userLogin, String userPassword) throws DalException;
    
    boolean checkAdminCredentials(String adminLogin, String adminPassword) throws DalException;
    
    List<User> getUser(String userLogin) throws DalException;
    
    List<Project> getAllProjects() throws DalException;
    
    User getSpecificUser(String userLogin) throws DalException;
    
    List<Admin> getAdmin (String adminLogin) throws DalException;
    
    Admin getSpecificAdmin (String adminLogin) throws DalException;
    
    List<Task> getAllTasks() throws DalException;
    
    void deleteTask(Task task) throws DalException;
    
    boolean createTask(String opgaveNavn, int projektId, long brugtTid, String dato, String beskrivelse, int betalt) throws DalException;
    
    void addTime(long brugtTid, String opgaveNavn) throws DalException;
    
    List<Task> getAllTasksProjektNavn() throws DalException;
    
    boolean createProject(String projektNavn, int kundeId, String startDato) throws DalException;
    
    List<Task> getAllTasksByProject(int projektId) throws DalException;
    
    List<Project> getProjectKundeNavn() throws DalException;
    
    boolean createUser(String userLogin, String userPassword, String adminId, long hourlyRate) throws DalException;
    
    boolean createUserAdmin(String userLogin, String userPassword, int adminId, long hourlyRate) throws DalException;
    
    boolean createAdmin(String adminLogin, String adminPassword) throws DalException;
    
    boolean createKunde(String kundeNavn) throws DalException;
    
    int getKundeId (String kundeNavn) throws DalException;
    
    int getAdminId (String adminLogin) throws DalException;
    
    List<Admin> getAllAdmins() throws DalException;
    
    List<User> getAllUsers() throws DalException;
    
    boolean updateTask(int brugtTid, int id) throws DalException;
}
