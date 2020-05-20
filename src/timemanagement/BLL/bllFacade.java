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

    Task createTask(String opgaveNavn, int projektId, long brugtTid, String dato, String beskrivelse, int betalt, String projektNavn, int isDeleted, int userId) throws bllException;

    void addTime(long brugtTid, String opgaveNavn) throws bllException;

    List<Task> getAllTasksProjektNavn() throws bllException;

    Project createProjekt(String projektNavn, int kundeId, String startDato, long brugtTid, int isDeleted, String kundeNavn, double hourlyRate) throws bllException;
  
    List<Project> getProjectKundeNavn() throws bllException;
    
    User createUser(String userLogin, String userPassword, int isAdmin, String email, String fullName) throws bllException;

    Kunde createKunde(String kundeNavn, String kontaktPerson, String email, double hourlyRate, int isDeleted) throws bllException;

    List<Kunde> getAllKunder() throws bllException;
    
    int getKundeId(String kundeNavn) throws bllException;

    List<User> getAllUsers() throws bllException;
    
    void updateProjectTime(Project project) throws bllException;
    
    boolean updateTask(Task task) throws bllException;
    
    void deleteUser(User user) throws bllException;
    
    int getIsAdminInt(String userLogin, String userPassword) throws bllException;
    
    void archiveProject(Project project) throws bllException;
    
    void editTask(Task task) throws bllException;
    
    void updateUserRoles(User user, int isAdmin) throws bllException;
    
    List<Task> getAllTasksOnProject(int projektId) throws bllException;
    
    String timeFormatter(String startTid, String slutTid) throws bllException;
    
    long timeCalculator(String startTid, String slutTid) throws bllException;
    
    void deleteProject(Project project, int isDeleted) throws bllException;
    
    void deleteTask(Task task, int isDeleted) throws bllException;
    
    void deleteKunde(Kunde kunde, int isDeleted) throws bllException;
}
