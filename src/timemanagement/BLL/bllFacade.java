/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BLL;

import java.util.List;
import timemanagement.BE.Kunde;
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

    void deleteTask(Task task) throws bllException;

    boolean createTask(String opgaveNavn, int projektId, long brugtTid, String dato, String beskrivelse, int betalt) throws bllException;

    void addTime(long brugtTid, String opgaveNavn) throws bllException;

    List<Task> getAllTasksProjektNavn() throws bllException;

    Project createProjekt(String projektNavn, int kundeId, String startDato, long brugtTid, int ongoing, long brugtTidMinutter, String kundeNavn) throws bllException;

    List<Task> getAllTasksByProject(int projektId) throws bllException;

    List<Project> getProjectKundeNavn() throws bllException;
    
    User createUser(String userLogin, String userPassword, int isAdmin) throws bllException;
    
    User createUserAdmin(String userLogin, String userPassword, int isAdmin) throws bllException;

    boolean createKunde(String kundeNavn) throws bllException;

    List<Kunde> getAllKunder() throws bllException;
    
    int getKundeId(String kundeNavn) throws bllException;

    List<User> getAllUsers() throws bllException;
    
    void updateProjectTime(Project project) throws bllException;
    
    boolean updateTask(Task task) throws bllException;
    
    void deleteUser(User user) throws bllException;
    
    int getIsAdminInt(String userLogin, String userPassword) throws bllException;
    
    void archiveProject(Project project) throws bllException;
    
    boolean editTask(String opgaveNavn, String beskrivelse, int betalt, String opgaveTitel) throws bllException;
    
    void updateUserRoles(User user) throws bllException;
}
