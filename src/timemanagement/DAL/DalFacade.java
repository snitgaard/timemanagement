/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.DAL;

import java.util.List;
import timemanagement.BE.Admin;
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
    
    boolean createTask() throws DalException;
    
    void addTime(long brugtTid, String opgaveNavn) throws DalException;
    
    List<Task> getAllTasksProjektNavn() throws DalException;
}
