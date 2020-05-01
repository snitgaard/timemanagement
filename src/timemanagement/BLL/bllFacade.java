/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BLL;

import java.util.List;
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

    boolean checkUserCredentials(String userLogin, String userPassword, int isAdmin) throws bllException;

    List<User> getUser(String userLogin) throws bllException;

    User getSpecificUser(String userLogin) throws bllException;

    public List<Project> getAllProjects() throws bllException;

    List<Task> getAllTasks() throws bllException;

    void deleteTask(Task task) throws bllException;

    boolean createTask(String opgaveNavn, int projektId, long brugtTid, String dato, String beskrivelse, int betalt) throws bllException;

    void addTime(long brugtTid, String opgaveNavn) throws bllException;

    List<Task> getAllTasksProjektNavn() throws bllException;

    boolean createProjekt(String projektNavn, int kundeId, String startDato, long brugtTid) throws bllException;

    List<Task> getAllTasksByProject(int projektId) throws bllException;

    List<Project> getProjectKundeNavn() throws bllException;
    
    boolean createUser(String userLogin, String userPassword, int isAdmin, long hourlyRate) throws bllException;
    
    boolean createUserAdmin(String userLogin, String userPassword, int isAdmin, long hourlyRate) throws bllException;

    boolean createKunde(String kundeNavn) throws bllException;

    int getKundeId(String kundeNavn) throws bllException;

    List<User> getAllUsers() throws bllException;
    
    void addProjectTime(long brugtTid, String projektNavn) throws bllException;
    
    boolean updateTask(int brugtTid, int id) throws bllException;
    
    void deleteUser(User user) throws bllException;
    
    int getIsAdminInt(String userLogin, String userPassword) throws bllException;

}
