/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BLL;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import timemanagement.BE.Admin;
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

    public bllManager() throws IOException {
        dalFacade = new DalManager();
    }

    @Override
    public boolean checkUserCredentials(String userLogin, String userPassword) throws bllException {
        try {
            return dalFacade.checkUserCredentials(userLogin, userPassword);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public List<User> getUser(String userLogin) throws bllException {
        try {
            return dalFacade.getUser(userLogin);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public List<Project> getAllProjects() throws bllException {
        try {
            return dalFacade.getAllProjects();
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public User getSpecificUser(String userLogin) throws bllException {
        try {
            return dalFacade.getSpecificUser(userLogin);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public boolean checkAdminCredentials(String adminLogin, String adminPassword) throws bllException {
        try {
            return dalFacade.checkAdminCredentials(adminLogin, adminPassword);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public List<Admin> getAdmin(String adminLogin) throws bllException {
        try {
            return dalFacade.getAdmin(adminLogin);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public Admin getSpecificAdmin(String adminLogin) throws bllException {
        try {
            return dalFacade.getSpecificAdmin(adminLogin);
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
    public void deleteTask(Task task) throws bllException {
        try {
            dalFacade.deleteTask(task);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public boolean createTask(String opgaveNavn, int projektId, long brugtTid, String dato, String beskrivelse, int betalt) throws bllException {
        try {
            return dalFacade.createTask(opgaveNavn, projektId, brugtTid, dato, beskrivelse, betalt);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public void addTime(long brugtTid, String opgaveNavn) throws bllException {
        try {
            dalFacade.addTime(brugtTid, opgaveNavn);
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
    public boolean createProjekt(String projektNavn, int kundeId, String startDato, long brugtTid) throws bllException {
        try {
            return dalFacade.createProject(projektNavn, kundeId, startDato, brugtTid);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public List<Task> getAllTasksByProject(int projektId) throws bllException {
        try {
            return dalFacade.getAllTasksByProject(projektId);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public boolean createUser(String userLogin, String userPassword, String adminId, long hourlyRate) throws bllException {
        try {
            return dalFacade.createUser(userLogin, userPassword, adminId, hourlyRate);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }
    
    @Override
    public boolean createUserAdmin(String userLogin, String userPassword, int adminId, long hourlyRate) throws bllException {
        try {
            return dalFacade.createUserAdmin(userLogin, userPassword, adminId, hourlyRate);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public boolean createAdmin(String adminLogin, String adminPassword) throws bllException {
        try {
            return dalFacade.createAdmin(adminLogin, adminPassword);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }
    
    @Override
    public boolean createKunde(String kundeNavn) throws bllException
    {
        try
        {
            return dalFacade.createKunde(kundeNavn);
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
    public int getAdminId(String adminLogin) throws bllException {
        try {
            return dalFacade.getAdminId(adminLogin);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public List<Admin> getAllAdmins() throws bllException
    {
        try
        {
            return dalFacade.getAllAdmins();
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
    public void addProjectTime(long brugtTid, String projektNavn) throws bllException {
        try {
            dalFacade.addProjektTime(brugtTid, projektNavn);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }
    public boolean updateTask(int brugtTid, int id) throws bllException {
        try {
            return dalFacade.updateTask(brugtTid, id);
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
    public void deleteAdmin(Admin admin) throws bllException
    {
        try
        {
            dalFacade.deleteAdmin(admin);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }
}
