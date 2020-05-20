/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BLL;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import timemanagement.BE.Kunde;
import timemanagement.BE.Project;
import timemanagement.BE.Task;
import timemanagement.BE.User;
import timemanagement.DAL.DalException;
import timemanagement.DAL.DalFacade;
import timemanagement.DAL.DalManager;

/**
 *
 * @author The Cowboys
 */
public class bllManager implements bllFacade {

    private final DalFacade dalFacade;

    /**
     * Constructor for bllManager
     * 
     * @throws IOException 
     */
    public bllManager() throws IOException {
        dalFacade = new DalManager();
    }

    /**
     * Checks if the login and password is correct 
     * and whether or not "isAdmin" integer
     * is 0 or 1
     * 
     * @param userLogin
     * @param userPassword
     * @param isAdmin
     * @return checkUserCredentials method from the dalFacade
     * @throws bllException 
     */
    @Override
    public boolean checkUserCredentials(String userLogin, String userPassword, int isAdmin) throws bllException {
        try {
            return dalFacade.checkUserCredentials(userLogin, userPassword, isAdmin);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Gets a list of a User from the dalFacade
     *
     * @param userLogin
     * @return getUser method from the DalFacade
     * @throws bllException
     */
    @Override
    public List<User> getUser(String userLogin) throws bllException {
        try {
            return dalFacade.getUser(userLogin);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Gets a list of all Projects from the dalFacade
     *
     * @return getAllProjects method from the DalFacade
     * @throws bllException
     */
    @Override
    public List<Project> getAllProjects() throws bllException {
        try {
            return dalFacade.getAllProjects();
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * This method gets the specific user down depending on the userLogin
     * 
     * @param userLogin
     * @return getSpecificUser method from the dalFacade
     * @throws bllException 
     */
    @Override
    public User getSpecificUser(String userLogin) throws bllException {
        try {
            return dalFacade.getSpecificUser(userLogin);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public List<Task> getAllTasks() throws bllException {
        try {
            return dalFacade.getAllTasks();
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public Task createTask(String opgaveNavn, int projektId, long brugtTid, String dato, String beskrivelse, int betalt, String projektNavn, int isDeleted, int userId) throws bllException {
        try {
            return dalFacade.createTask(opgaveNavn, projektId, brugtTid, dato, beskrivelse, betalt, projektNavn, isDeleted, userId);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public void addTime(long brugtTid, int id) throws bllException {
        try {
            dalFacade.addTime(brugtTid, id);
        } catch (DalException ex) {
            Logger.getLogger(bllManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Task> getAllTasksProjektNavn() throws bllException {
        try {
            return dalFacade.getAllTasksProjektNavn();
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public Project createProjekt(String projektNavn, int kundeId, String startDato, long brugtTid, int isDeleted, String kundeNavn, double hourlyRate, int rounded) throws bllException {
        try {
            return dalFacade.createProject(projektNavn, kundeId, startDato, brugtTid, ongoing, kundeNavn, hourlyRate, rounded);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public User createUser(String userLogin, String userPassword, int isAdmin, String email, String fullName) throws bllException {
        try {
            return dalFacade.createUser(userLogin, userPassword, isAdmin, email, fullName);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public int getKundeId(String kundeNavn) throws bllException
    {
        try {
            return dalFacade.getKundeId(kundeNavn);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public List<Project> getProjectKundeNavn() throws bllException
    {
        try
        {
            return dalFacade.getProjectKundeNavn();
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() throws bllException
    {
        try
        {
            return dalFacade.getAllUsers();
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public void updateProjectTime(Project project) throws bllException {
        try {
            dalFacade.updateProjectTime(project);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }
    @Override
    public void addRoundedTime(long brugtTid, int id) throws bllException {
        try {
            dalFacade.addRoundedTime(brugtTid, id);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public void deleteUser(User user) throws bllException
    {
        try
        {
            dalFacade.deleteUser(user);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }
    
    @Override
    public int getIsAdminInt(String userLogin, String userPassword) throws bllException {
        try {
            return dalFacade.getIsAdminInt(userLogin, userPassword);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public void archiveProject(Project project) throws bllException {
        try {
            dalFacade.archiveProject(project);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }
            
    public void updateUserRoles(User user, int isAdmin) throws bllException
    {
        try
        {
            dalFacade.updateUserRoles(user, isAdmin);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public void editTask(Task task) throws bllException
    {
        try
        {
            dalFacade.editTask(task);
        }
        catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public List<Kunde> getAllKunder() throws bllException
    {
        try
        {
            return dalFacade.getAllKunder();
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public Kunde createKunde(String kundeNavn, String kontaktPerson, String email, double hourlyRate, int isDeleted) throws bllException
    {
        try
        {
            return dalFacade.createKunde(kundeNavn, kontaktPerson, email, hourlyRate, isDeleted);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public List<Task> getAllTasksOnProject(int projektId) throws bllException {
        try {
            return dalFacade.getAllTasksOnProject(projektId);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public String timeFormatter(String startTid, String slutTid) throws bllException {
 
       
            
            LocalTime start = LocalTime.parse(startTid);
            LocalTime slut = LocalTime.parse(slutTid);
            
            if (start.isBefore(slut))
            {
                long difference = Duration.between(start, slut).toMillis();
                System.out.println(difference + "ER DET MON HER?");
                long input = difference / 1000;
                long hours = (input - input % 3600) / 3600;
                long minutes = (input % 3600 - input % 3600 % 60) / 60;
                
                NumberFormat formatter = new DecimalFormat("00");
                   
                String h = formatter.format(hours); 
                String m = formatter.format(minutes);
                
                return h + ":" + m;
                
            }
            
            else if (start.equals(slut))
            {
                return "00:00"; //Ohterwise returns 24:00
            }
            
            else
            {
                long differenceTwo = Duration.ofHours(24).minus(Duration.between(slut, start)).toMillis();
                long input = differenceTwo / 1000;
                long hours = (input - input % 3600) / 3600;
                long minutes = (input % 3600 - input % 3600 % 60) / 60;
                
                NumberFormat formatter = new DecimalFormat("00");
                   
                String h = formatter.format(hours); 
                String m = formatter.format(minutes);
                
                return h + ":" + m;
                           
            }
            
            
            
        
            

    }

    @Override
    public long timeCalculator(String startTid, String slutTid) throws bllException {
        
        
            LocalTime start = LocalTime.parse(startTid);
            LocalTime slut = LocalTime.parse(slutTid);
            
            if (start.isBefore(slut))
            {
                long duration = Duration.between(start, slut).toMillis();
                
                
                

            long input = duration / 1000;
            long minuteTime = input / 60;
            
            if (minuteTime == 0)
            {
                minuteTime = 1;
            }
                
                return minuteTime;
                
            }
            
            else if (start.equals(slut))
            {
                long minuteTime = 0;
                return minuteTime;
            }
            
            else
            {
                long durationAfterMidnight = Duration.ofHours(24).minus(Duration.between(slut, start)).toMillis();
                
                

            long input = durationAfterMidnight / 1000;
            long minuteTime = input / 60;
            
            if (minuteTime == 0)
            {
                minuteTime = 1;
            }
                
                return minuteTime;
            }
            
            
            
        
    }

    @Override
    public boolean updateTask(Task task) throws bllException {
        try {
            return dalFacade.updateTask(task);
    public void deleteProject(Project project, int isDeleted) throws bllException {
        try {
            dalFacade.deleteProject(project, isDeleted);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public void deleteTask(Task task, int isDeleted) throws bllException {
        try {
            dalFacade.deleteTask(task, isDeleted);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public void deleteKunde(Kunde kunde, int isDeleted) throws bllException {
        try {
            dalFacade.deleteKunde(kunde, isDeleted);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public void deleteTaskOnProject(Task task, int isDeleted, int projektId) throws bllException {
        try {
            dalFacade.deleteTaskOnProject(task, isDeleted, projektId);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public void deleteProjectOnClient(Project project, int isDeleted, int projektId) throws bllException {
        try {
            dalFacade.deleteProjectOnClient(project, isDeleted, projektId);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }
}
