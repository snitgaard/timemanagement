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
import timemanagement.BE.Admin;
import timemanagement.BE.Kunde;
import timemanagement.BE.Project;
import timemanagement.BE.Task;
import timemanagement.BLL.bllManager;
import timemanagement.BE.User;
import timemanagement.BLL.bllException;

/**
 *
 * @author jigzi
 */
public class Model {

    private bllManager bllManager;
    private ObservableList<Project> allProjects;
    private ObservableList<Task> allTasks;
    private ObservableList<Task> allTasksByProject;
    private ObservableList<Project> allProjectsMedKunde;
    private ObservableList<Admin> allAdmins;
    private ObservableList<User> allUsers;

    private static Model instance = new Model();

    private Model() {
        try {
            bllManager = new bllManager();
        } catch (IOException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Model getInstance() {
        return instance;
    }

    public boolean checkUserCredentials(String userLogin, String userPassword) throws ModelException {
        try {
            return bllManager.checkUserCredentials(userLogin, userPassword);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }

    public boolean checkAdminCredentials(String adminLogin, String adminPassword) throws ModelException {
        try {
            return bllManager.checkAdminCredentials(adminLogin, adminPassword);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }

    public List<User> getUser(String userLogin) throws ModelException {
        try {
            return bllManager.getUser(userLogin);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }

    public ObservableList<Project> getAllProjects() throws ModelException {
        allProjects = FXCollections.observableArrayList();
        try {
            allProjects.addAll(bllManager.getAllProjects());
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
        return allProjects;
    }

    public User getSpecificUser(String userLogin) throws ModelException {
        try {
            return bllManager.getSpecificUser(userLogin);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }

    public List<Admin> getAdmin(String adminLogin) throws ModelException {
        try {
            return bllManager.getAdmin(adminLogin);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }

    public Admin getSpecificAdmin(String adminLogin) throws ModelException {
        try {
            return bllManager.getSpecificAdmin(adminLogin);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }

    public ObservableList<Task> getAllTasks() throws ModelException {
        allTasks = FXCollections.observableArrayList();
        try {
            allTasks.addAll(bllManager.getAllTasks());
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
        return allTasks;
    }

    public ObservableList<Task> refreshTasks() throws ModelException {
        allTasks.clear();
        try {
            allTasks.addAll(bllManager.getAllTasks());
        } catch (bllException ex) {
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
        try {
            allTasks.addAll(bllManager.getAllTasksProjektNavn());
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
        return allTasks;
    }

    /**
     * Creates attendance from courseId, studentId and attended parameters
     *
     * @param courseId
     * @param studentId
     * @param attended
     * @return createAttendance method in the bllManager that returns true if a
     * row was added, false if not
     * @throws ModelException
     */
    public boolean createProjekt(String projektNavn, int kundeId, String startDato, long brugtTid) throws ModelException {
        try {
            return bllManager.createProjekt(projektNavn, kundeId, startDato, brugtTid);
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

    public boolean createUser(String userLogin, String userPassword, String adminId, long hourlyRate) throws ModelException {
        try {
            return bllManager.createUser(userLogin, userPassword, adminId, hourlyRate);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }

    public boolean createUserAdmin(String userLogin, String userPassword, int adminId, long hourlyRate) throws ModelException {
        try {
            return bllManager.createUserAdmin(userLogin, userPassword, adminId, hourlyRate);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }

    public int getAdminId(String adminLogin) throws ModelException {
        try {
            return bllManager.getAdminId(adminLogin);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }

    public boolean createAdmin(String adminLogin, String adminPassword) throws ModelException {
        try {
            return bllManager.createAdmin(adminLogin, adminPassword);
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
        } catch (bllException ex) {
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
        try {
            allProjectsMedKunde.addAll(bllManager.getProjectKundeNavn());
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
        return allProjectsMedKunde;
    }

    public ObservableList<Admin> getAllAdmins() throws ModelException {
        allAdmins = FXCollections.observableArrayList();
        allAdmins.clear();
        try {
            allAdmins.addAll(bllManager.getAllAdmins());
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
        return allAdmins;
    }

    public ObservableList<User> getAllUsers() throws ModelException {
        allUsers.clear();
        allUsers = FXCollections.observableArrayList();
        try {
            allUsers.addAll(bllManager.getAllUsers());
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
        return allUsers;
    }

    public void addProjectTime(long brugtTid, String projektNavn) throws ModelException {
        try {
            bllManager.addProjectTime(brugtTid, projektNavn);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }

    }

    public boolean updateTask(int brugtTid, int id) throws ModelException {
        try {
            return bllManager.updateTask(brugtTid, id);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }

}
