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
 * @author jigzi
 */
public class Model
{

    private static Model instance = new Model();
    private bllManager bllManager;
    private ObservableList<Project> allProjects;
    private ObservableList<Task> allTasks;
    private ObservableList<Project> allProjectsWithClients;
    private ObservableList<User> allUsers;
    private ObservableList<Client> allClients;

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

    /**
     * Returns a singleton instance of the model
     * @return 
     */
    public static Model getInstance()
    {
        return instance;
    }

    /**
     * Calls the checkUserCredentials method from the BLL layer. 
     * Checks if the user login and password is correct. 
     * Also check whether or not the user is an admin. 
     * @param userLogin
     * @param userPassword
     * @param isAdmin
     * @return
     * @throws ModelException 
     */
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
    
        /**
     * Returns a specific user
     * @param userLogin
     * @return
     * @throws ModelException 
     */
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

    /**
     * Returns all the projects in the database.
     * @return
     * @throws ModelException 
     */
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


    /**
     * Returns all the tasks in the database.
     * @return
     * @throws ModelException 
     */
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

    /**
     * Adds time to a selected task.
     * @param usedTime
     * @param id
     * @throws ModelException 
     */
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

    /**
     * Returns all tasks in the database, as well as the name of their given project.
     * @return
     * @throws ModelException 
     */
    public ObservableList<Task> getAllTasksProjectName() throws ModelException
    {
        allTasks = FXCollections.observableArrayList();
        try
        {
            allTasks.addAll(bllManager.getAllTasksProjectName());
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
            Project project = bllManager.createProject(projectName, clientId, startDate, usedTime, isDeleted, clientName, hourlyRate, rounded);
            allProjectsWithClients.add(project);
            return project;
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Creates a user with all the given parameters.
     * @param userLogin
     * @param userPassword
     * @param isAdmin
     * @param email
     * @param fullName
     * @throws ModelException 
     */
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

    /**
     * Creates a task with all the given parameters.
     * @param taskName
     * @param projectId
     * @param usedTime
     * @param date
     * @param description
     * @param paid
     * @param projectName
     * @param isDeleted
     * @param userId
     * @return
     * @throws ModelException 
     */
    public Task createTask(String taskName, int projectId, long usedTime, String date, String description, int paid, String projectName, int isDeleted, int userId) throws ModelException
    {
        try
        {
            Task task = bllManager.createTask(taskName, projectId, usedTime, date, description, paid, projectName, isDeleted, userId);
            allTasks.add(task);
            return task;
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Returns the id of a client.
     * @param clientName
     * @return
     * @throws ModelException 
     */
    public int getClientId(String clientName) throws ModelException
    {
        try
        {
            return bllManager.getClientId(clientName);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Returns a list of all projects in the database and the name of their client.
     * @return
     * @throws ModelException 
     */
    public ObservableList<Project> getProjectClientName() throws ModelException
    {
        try
        {
            allProjectsWithClients = FXCollections.observableArrayList();

            allProjectsWithClients.addAll(bllManager.getProjectClientName());
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        return allProjectsWithClients;
    }

    /**
     * Returns a list of all users in the database.
     * Sets text "Admin" or "User" to be displayed in the program.
     * @return
     * @throws ModelException 
     */
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

    /**
     * Updates time spent on a project 
     * by adding time spent on all the tasks in the project
     * @param project
     * @throws ModelException 
     */
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

    /**
     * Adds time to a task, but rounds up to the nearest 15 minutes.
     * @param usedTime
     * @param id
     * @throws ModelException 
     */
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

    /**
     * Updates the time of a given task based on user input. 
     * @param task
     * @return
     * @throws ModelException 
     */
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

    /**
     * Deletes a user from the database.
     * @param user
     * @throws ModelException 
     */
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

    /**
     * Returns 1 or 0 depending on if a user is an admin or not.
     * @param userLogin
     * @param userPassword
     * @return
     * @throws ModelException 
     */
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

    /**
     * Updates the role of an user to either "User" or "Admin"
     * @param user
     * @param isAdmin
     * @throws ModelException 
     */
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

    /**
     * Edits the title and/or description of a given task.
     * @param task
     * @throws ModelException 
     */
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

    /**
     * Returns the list of all clients in the database.
     * @return
     * @throws ModelException 
     */
    public ObservableList<Client> getAllClients() throws ModelException
    {
        try
        {
            allClients = FXCollections.observableArrayList();
            allClients.addAll(bllManager.getAllClients());
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        return allClients;
    }

    /**
     * Creates a client with the given parameters.
     * @param clientName
     * @param contactPerson
     * @param email
     * @param hourlyRate
     * @param isDeleted
     * @return
     * @throws ModelException 
     */
    public Client createClient(String clientName, String contactPerson, String email, double hourlyRate, int isDeleted) throws ModelException
    {
        try
        {
            Client client = bllManager.createClient(clientName, contactPerson, email, hourlyRate, isDeleted);
            allClients.add(client);
            return client;
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Returns all tasks on a given project. 
     * @param projectId
     * @return
     * @throws ModelException 
     */
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

    /**
     * Returns the time spent on a task in the correct format (HH:mm)
     * @param usedTime
     * @param endTime
     * @return
     * @throws ModelException 
     */
    public String timeFormatter(String usedTime, String endTime) throws ModelException
    {
        try
        {
            return bllManager.timeFormatter(usedTime, endTime);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Calculates the time spent on a task
     * @param startTime
     * @param endTime
     * @return
     * @throws ModelException 
     */
    public long timeCalculator(String startTime, String endTime) throws ModelException
    {
        try
        {
            return bllManager.timeCalculator(startTime, endTime);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Removes a task from the views in the program and archives it in the database.
     * @param task
     * @param isDeleted
     * @throws ModelException 
     */
    public void deleteTask(Task task, int isDeleted) throws ModelException
    {
        try
        {
            allTasks.remove(task);
            bllManager.deleteTask(task, isDeleted);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Removes a project from the views in the program and archives it in the database.
     * @param project
     * @param isDeleted
     * @throws ModelException 
     */
    public void deleteProject(Project project, int isDeleted) throws ModelException
    {
        try
        {
            allProjects.remove(project);
            allProjectsWithClients.remove(project);
            bllManager.deleteProject(project, isDeleted);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Removes a client from the views in the program and archives it in the database.
     * @param client
     * @param isDeleted
     * @throws ModelException 
     */
    public void deleteClient(Client client, int isDeleted) throws ModelException
    {
        try
        {
            allClients.remove(client);
            bllManager.deleteClient(client, isDeleted);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Archives all tasks on a given project.
     * @param task
     * @param isDeleted
     * @param projectId
     * @throws ModelException 
     */
    public void deleteTaskOnProject(Task task, int isDeleted, int projectId) throws ModelException
    {
        try
        {
            bllManager.deleteTaskOnProject(task, isDeleted, projectId);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    /**
     * Archives all projects on a given client.
     * @param project
     * @param isDeleted
     * @param clientId
     * @return
     * @throws ModelException 
     */
    public Project deleteProjectOnClient(Project project, int isDeleted, int clientId) throws ModelException
    {
        try
        {
            allProjects.remove(project);
            allProjectsWithClients.remove(project);
            return bllManager.deleteProjectOnClient(project, isDeleted, clientId);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }
}
