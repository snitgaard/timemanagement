/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BLL;

import java.util.List;
import timemanagement.BE.Admin;
import timemanagement.BE.Project;
import timemanagement.BE.Task;
import timemanagement.BE.User;
import timemanagement.DAL.DalException;

/**
 *
 * @author Mads
 */
public interface bllFacade
{

    boolean checkUserCredentials(String userLogin, String userPassword) throws bllException;

    List<User> getUser(String userLogin) throws bllException;

    User getSpecificUser(String userLogin) throws bllException;

    public List<Project> getAllProjects() throws bllException;

    boolean checkAdminCredentials(String adminLogin, String adminPassword) throws bllException;

    List<Admin> getAdmin(String adminLogin) throws bllException;

    Admin getSpecificAdmin(String adminLogin) throws bllException;

    List<Task> getAllTasks() throws bllException;

    void deleteTask(Task task) throws bllException;

    boolean createTask() throws bllException;
    
    void addTime(long brugtTid, String opgaveNavn) throws bllException;

    List<Task> getAllTasksProjektNavn() throws bllException;
    
    boolean createProjekt(String projektNavn, String kunde, String startDato) throws bllException;
    
    List<Task> getAllTasksByProject(int projektId) throws bllException;
    
    boolean createUser(String userLogin, String userPassword) throws bllException;
    
    boolean createAdmin(String adminLogin, String adminPassword) throws bllException;
}
