/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.gui.model;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timemanagement.BE.Kunde;
import timemanagement.BE.Project;
import timemanagement.BE.Task;
import timemanagement.BLL.bllManager;
import timemanagement.BE.User;
import timemanagement.BLL.bllException;
import timemanagement.DAL.DalException;

/**
 *
 * @author jigzi
 */
public class Model
{

    private bllManager bllManager;
    private ObservableList<Project> allProjects;
    private ObservableList<Task> allTasks;
    private ObservableList<Project> allProjectsMedKunde;
    private ObservableList<User> allUsers;
    private ObservableList<Kunde> allKunder;

    private static Model instance = new Model();

    public Model()
    {
        try
        {
            bllManager = new bllManager();
        } catch (IOException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Model getInstance()
    {
        return instance;
    }

    public boolean checkUserCredentials(String userLogin, String userPassword, int isAdmin) throws ModelException
    {
        try
        {
            return bllManager.checkUserCredentials(userLogin, userPassword, isAdmin);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public List<User> getUser(String userLogin) throws ModelException
    {
        try
        {
            return bllManager.getUser(userLogin);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public ObservableList<Project> getAllProjects() throws ModelException
    {
        allProjects = FXCollections.observableArrayList();
        try
        {
            allProjects.addAll(bllManager.getAllProjects());
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        return allProjects;
    }

    public User getSpecificUser(String userLogin) throws ModelException
    {
        try
        {
            return bllManager.getSpecificUser(userLogin);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public ObservableList<Task> getAllTasks() throws ModelException
    {
        allTasks = FXCollections.observableArrayList();
        try
        {
            allTasks.addAll(bllManager.getAllTasks());
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        return allTasks;
    }

    public void addTime(long brugtTid, String opgaveNavn) throws ModelException
    {
        try
        {
            bllManager.addTime(brugtTid, opgaveNavn);
        } catch (bllException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Task> getAllTasksProjektNavn() throws ModelException
    {
        allTasks = FXCollections.observableArrayList();
        try
        {
            allTasks.addAll(bllManager.getAllTasksProjektNavn());
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        return allTasks;
    }

    /**
     * Creates attendance from courseId, studentId and attended parameters
     *
     * @param projektNavn
     * @param kundeId
     * @param courseId
     * @param brugtTid
     * @param startDato
     * @param studentId
     * @param attended
     * @return createAttendance method in the bllManager that returns true if a
     * row was added, false if not
     * @throws ModelException
     */
    public Project createProjekt(String projektNavn, int kundeId, String startDato, long brugtTid, int isDeleted, String kundeNavn, double hourlyRate) throws ModelException
    {
        try
        {
            Project project = bllManager.createProjekt(projektNavn, kundeId, startDato, brugtTid, isDeleted, kundeNavn, hourlyRate);
            allProjectsMedKunde.add(project);
            return project;
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public void createUser(String userLogin, String userPassword, int isAdmin, String email, String fullName) throws ModelException
    {
        try
        {
            User user = bllManager.createUser(userLogin, userPassword, isAdmin, email, fullName);

            user.setAdminRights("User");
            allUsers.add(user);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }



    public Task createTask(String opgaveNavn, int projektId, long brugtTid, String dato, String beskrivelse, int betalt, String projektNavn, int isDeleted, int userId) throws ModelException
    {
        try
        {
            Task task = bllManager.createTask(opgaveNavn, projektId, brugtTid, dato, beskrivelse, betalt, projektNavn, isDeleted, userId);
            allTasks.add(task);
            return task;
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public int getKundeId(String kundeNavn) throws ModelException
    {
        try
        {
            return bllManager.getKundeId(kundeNavn);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public ObservableList<Project> getProjectKundeNavn() throws ModelException
    {
        try
        {
            allProjectsMedKunde = FXCollections.observableArrayList();

            allProjectsMedKunde.addAll(bllManager.getProjectKundeNavn());
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        return allProjectsMedKunde;
    }

    public ObservableList<User> getAllUsers() throws ModelException
    {

        try
        {
            List<User> tempUserList = bllManager.getAllUsers();
            allUsers = FXCollections.observableArrayList();

            for (User users1 : tempUserList)
            {
                if (users1.getIsAdmin() == 0)
                {
                    users1.setAdminRights("User");
                } else
                {
                    users1.setAdminRights("Admin");
                }
                allUsers.add(users1);
            }
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        return allUsers;
    }

    public void updateProjectTime(Project project) throws ModelException
    {
        try
        {
            bllManager.updateProjectTime(project);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }

    }

    public boolean updateTask(Task task) throws ModelException
    {
        try
        {
            return bllManager.updateTask(task);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public void deleteUser(User user) throws ModelException
    {
        try
        {
            bllManager.deleteUser(user);
            allUsers.remove(user);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public int getIsAdminInt(String userLogin, String userPassword) throws ModelException
    {
        try
        {
            return bllManager.getIsAdminInt(userLogin, userPassword);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public void archiveProject(Project project) throws ModelException
    {
        try
        {
            bllManager.archiveProject(project);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public void updateUserRoles(User user, int isAdmin) throws ModelException
    {
        try
        {
            bllManager.updateUserRoles(user, isAdmin);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public void editTask(Task task) throws ModelException
    {
        try
        {
            bllManager.editTask(task);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public ObservableList<Kunde> getAllKunder() throws ModelException
    {
        try
        {
            allKunder = FXCollections.observableArrayList();
            allKunder.addAll(bllManager.getAllKunder());
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        return allKunder;
    }

    public Kunde createKunde(String kundeNavn, String kontaktPerson, String email, double hourlyRate, int isDeleted) throws ModelException
    {
        try
        {
            Kunde kunde = bllManager.createKunde(kundeNavn, kontaktPerson, email, hourlyRate, isDeleted);
            allKunder.add(kunde);
            return kunde;
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public List<Task> getAllTasksOnProject(int projektId) throws ModelException
    {
        try
        {
            return bllManager.getAllTasksOnProject(projektId);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }
    
    public String timeFormatter(String startTid, String slutTid) throws ModelException
    {
        try {
            return bllManager.timeFormatter(startTid, slutTid);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }
    
    public long timeCalculator(String startTid, String slutTid) throws ModelException
    {
        try {
            return bllManager.timeCalculator(startTid, slutTid);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }
    
    public void deleteTask(Task task, int isDeleted) throws ModelException
    {
        try {
            bllManager.deleteTask(task, isDeleted);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }
    
    public void deleteProject(Project project, int isDeleted) throws ModelException
    {
        try {
            bllManager.deleteProject(project, isDeleted);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }
            
    public void deleteKunde(Kunde kunde, int isDeleted) throws ModelException
    {
        try {
            bllManager.deleteKunde(kunde, isDeleted);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }
    
    public void deleteTaskOnProject(Task task, int isDeleted, int projektId) throws ModelException
    {
        try {
            bllManager.deleteTaskOnProject(task, isDeleted, projektId);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }
    
    public void deleteProjectOnClient(Project project, int isDeleted, int kundeId) throws ModelException
    {
        try {
            bllManager.deleteProjectOnClient(project, isDeleted, kundeId);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }
}
