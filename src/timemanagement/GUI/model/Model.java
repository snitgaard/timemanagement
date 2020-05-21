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
import timemanagement.BE.Client;
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
    private ObservableList<Project> allProjectsWithClients;
    private ObservableList<User> allUsers;
    private ObservableList<Client> allClients;

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

    public void addTime(long usedTime, int id) throws ModelException
    {
        try
        {
            bllManager.addTime(usedTime, id);
        } catch (bllException ex)
        {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObservableList<Task> getAllTasksProjectName() throws ModelException
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
     * @param projectName
     * @param clientId
     * @param courseId
     * @param usedTime
     * @param startDate
     * @param studentId
     * @param attended
     * @return createAttendance method in the bllManager that returns true if a
     * row was added, false if not
     * @throws ModelException
     */
    public Project createProject(String projectName, int clientId, String startDate, long usedTime, int isDeleted, String clientName, double hourlyRate, int rounded) throws ModelException
    {
        try
        {
            Project project = bllManager.createProjekt(projectName, clientId, startDate, usedTime, isDeleted, clientName, hourlyRate, rounded);
            allProjectsWithClients.add(project);
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



    public Task createTask(String taskName, int projectId, long usedTime, String date, String description, int payed, String projectName, int isDeleted, int userId) throws ModelException
    {
        try
        {
            Task task = bllManager.createTask(taskName, projectId, usedTime, date, description, payed, projectName, isDeleted, userId);
            allTasks.add(task);
            return task;
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public int getClientId(String clientName) throws ModelException
    {
        try
        {
            return bllManager.getKundeId(clientName);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public ObservableList<Project> getProjectClientName() throws ModelException
    {
        try
        {
            allProjectsWithClients = FXCollections.observableArrayList();

            allProjectsWithClients.addAll(bllManager.getProjectKundeNavn());
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        return allProjectsWithClients;
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

    public void addRoundedTime(double usedTime, int id) throws ModelException
    {
        try
        {
            bllManager.addRoundedTime(usedTime, id);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }
    
    public boolean updateTask(Task task) throws ModelException
    {
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

    public ObservableList<Client> getAllClients() throws ModelException
    {
        try
        {
            allClients = FXCollections.observableArrayList();
            allClients.addAll(bllManager.getAllKunder());
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        return allClients;
    }

    public Client createClient(String clientName, String contactPerson, String email, double hourlyRate, int isDeleted) throws ModelException
    {
        try
        {
            Client client = bllManager.createKunde(clientName, contactPerson, email, hourlyRate, isDeleted);
            allClients.add(client);
            return client;
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public List<Task> getAllTasksOnProject(int projectId) throws ModelException
    {
        try
        {
            return bllManager.getAllTasksOnProject(projectId);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }
    
    public String timeFormatter(String usedTime, String endTime) throws ModelException
    {
        try {
            return bllManager.timeFormatter(usedTime, endTime);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }
    
    public long timeCalculator(String startTime, String endTime) throws ModelException
    {
        try {
            return bllManager.timeCalculator(startTime, endTime);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }
    
    public void deleteTask(Task task, int isDeleted) throws ModelException
    {
        try {
            allTasks.remove(task);
            bllManager.deleteTask(task, isDeleted);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }
    
    public void deleteProject(Project project, int isDeleted) throws ModelException
    {
        try {
            allProjects.remove(project);
            allProjectsWithClients.remove(project);
            bllManager.deleteProject(project, isDeleted);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }
            
    public void deleteClient(Client client, int isDeleted) throws ModelException
    {
        try {
            allClients.remove(client);
            bllManager.deleteKunde(client, isDeleted);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }
    
    public void deleteTaskOnProject(Task task, int isDeleted, int projectId) throws ModelException
    {
        try {   
            bllManager.deleteTaskOnProject(task, isDeleted, projectId);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }
    
    public Project deleteProjectOnClient(Project project, int isDeleted, int clientId) throws ModelException
    {
        try {
            allProjects.remove(project);
            allProjectsWithClients.remove(project);
            return bllManager.deleteProjectOnClient(project, isDeleted, clientId);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }
}
