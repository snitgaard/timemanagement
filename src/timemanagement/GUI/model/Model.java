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
import timemanagement.BE.Project;
import timemanagement.BE.Task;
import timemanagement.BLL.bllManager;
import timemanagement.BE.User;
import timemanagement.BLL.bllException;

/**
 *
 * @author jigzi
 */
public class Model
{

    private bllManager bllManager;
    private ObservableList<Project> allProjects;
    private ObservableList<Task> allTasks;
    private ObservableList<Task> allTasksByProject;
    private ObservableList<Project> allProjectsMedKunde;
    private ObservableList<User> allUsers;

    private static Model instance = new Model();

    private Model()
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

    public ObservableList<Task> getAllTasks() throws ModelException {
        allTasks = FXCollections.observableArrayList();
        try {
            allTasks.addAll(bllManager.getAllTasks());
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        return allTasks;
    }

    public ObservableList<Task> refreshTasks() throws ModelException {
        allTasks.clear();
        try
        {
            allTasks.addAll(bllManager.getAllTasksProjektNavn());
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        return allTasks;
    }

    public void addTime(long brugtTid, String opgaveNavn) throws ModelException {
        try {
            bllManager.addTime(brugtTid, opgaveNavn);
        } catch (bllException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Task> getAllTasksProjektNavn() throws ModelException {
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
    public void createProjekt(String projektNavn, int kundeId, String startDato, long brugtTid, int ongoing, long brugtTidMinutter, String kundeNavn) throws ModelException {
        try {
            Project project = bllManager.createProjekt(projektNavn, kundeId, startDato, brugtTid, ongoing, brugtTidMinutter, kundeNavn);
            allProjectsMedKunde.add(project);
            allProjects.add(project);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }

    public ObservableList<Task> getAllTasksByProject(int projektId) throws ModelException {
        try {
            allTasksByProject = FXCollections.observableArrayList();

            allTasksByProject.addAll(bllManager.getAllTasksByProject(projektId));
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
        return allTasksByProject;
    }

    public void createUser(String userLogin, String userPassword, int isAdmin) throws ModelException {
        try {
            User user = bllManager.createUser(userLogin, userPassword, isAdmin);
            user.setAdminRights("User");
            allUsers.add(user);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }

    public void createUserAdmin(String userLogin, String userPassword, int adminId) throws ModelException {
        try {
            User user = bllManager.createUserAdmin(userLogin, userPassword, adminId);
            user.setAdminRights("Admin");
            allUsers.add(user);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }


    public boolean createTask(String opgaveNavn, int projektId, long brugtTid, String dato, String beskrivelse, int betalt) throws ModelException {
        try {
            return bllManager.createTask(opgaveNavn, projektId, brugtTid, dato, beskrivelse, betalt);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }

    public boolean createKunde(String kundeNavn) throws ModelException {
        try {
            return bllManager.createKunde(kundeNavn);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public int getKundeId(String kundeNavn) throws ModelException {
        try {
            return bllManager.getKundeId(kundeNavn);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }

    public ObservableList<Project> getProjectKundeNavn() throws ModelException {
        try {
            allProjectsMedKunde = FXCollections.observableArrayList();

            allProjectsMedKunde.addAll(bllManager.getProjectKundeNavn());
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
        return allProjectsMedKunde;
    }

    public ObservableList<Project> refreshProjects() throws ModelException {
        allProjectsMedKunde.clear();
        try
        {
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
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
        return allUsers;
    }

    public void updateProjectTime(Project project) throws ModelException {
        try {
            bllManager.updateProjectTime(project);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }

    }

    public boolean updateTask(Task task) throws ModelException {
        try {
            return bllManager.updateTask(task);
        } catch (bllException ex) {
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
    
    public int getIsAdminInt(String userLogin, String userPassword) throws ModelException {
        try {
            return bllManager.getIsAdminInt(userLogin, userPassword);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }
    
    public void archiveProject(Project project) throws ModelException
    {
        try {
            bllManager.archiveProject(project);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }
    
    public void updateUserRoles(User user) throws ModelException
    {
        try
        {
            bllManager.updateUserRoles(user);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }
    
    public boolean editTask(String opgaveNavn, String beskrivelse, int betalt, String opgaveTitel) throws ModelException
    {
        try
        {
            return bllManager.editTask(opgaveNavn, beskrivelse, betalt, opgaveTitel);
        }
        catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }
    
}
