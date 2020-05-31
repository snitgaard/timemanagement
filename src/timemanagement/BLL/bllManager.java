/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BLL;

import timemanagement.BE.*;
import timemanagement.DAL.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author The Cowboys
 */
public class bllManager implements bllFacade
{

    private final DalFacade dalFacade;

    /**
     * Constructor for bllManager
     *
     * @throws IOException
     */
    public bllManager() throws IOException
    {
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
    public boolean checkUserCredentials(String userLogin, String userPassword, int isAdmin) throws bllException
    {
        try
        {
            return dalFacade.checkUserCredentials(userLogin, userPassword, isAdmin);
        } catch (DalException ex)
        {
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
    public List<Project> getAllProjects() throws bllException
    {
        try
        {
            return dalFacade.getAllProjects();
        } catch (DalException ex)
        {
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
    public User getSpecificUser(String userLogin) throws bllException
    {
        try
        {
            return dalFacade.getSpecificUser(userLogin);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Returns a list of all tasks in the database.
     * @return
     * @throws bllException 
     */
    @Override
    public List<Task> getAllTasks() throws bllException
    {
        try
        {
            return dalFacade.getAllTasks();
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Creates a task in the database with the given parameters.
     * @param taskName
     * @param proejctId
     * @param usedTime
     * @param date
     * @param description
     * @param payed
     * @param projectName
     * @param isDeleted
     * @param userId
     * @return
     * @throws bllException 
     */
    @Override
    public Task createTask(String taskName, int proejctId, long usedTime, String date, String description, int paid, String projectName, int isDeleted, int userId) throws bllException
    {
        try
        {
            return dalFacade.createTask(taskName, proejctId, usedTime, date, description, paid, projectName, isDeleted, userId);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Adds time spent to the selected task.
     * @param usedTime
     * @param id
     * @throws bllException 
     */
    @Override
    public void addTime(long usedTime, int id) throws bllException
    {
        try
        {
            dalFacade.addTime(usedTime, id);
        } catch (DalException ex)
        {
            Logger.getLogger(bllManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a list of all tasks in the database and their project name.
     * @return
     * @throws DalException 
     */
    @Override
    public List<Task> getAllTasksProjectName() throws bllException
    {
        try
        {
            return dalFacade.getAllTasksProjectName();
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Creates a project in the database with the given parameters.
     * @param projectName
     * @param clientId
     * @param startDate
     * @param usedTime
     * @param isDeleted
     * @param clientName
     * @param hourlyRate
     * @param rounded
     * @return
     * @throws DalException 
     */
    @Override
    public Project createProject(String projectName, int clientId, String startDate,
                                long usedTime, int isDeleted, String clientName, double hourlyRate, int rounded) throws bllException
    {
        try
        {
            return dalFacade.createProject(projectName, clientId, startDate, usedTime, isDeleted, clientName, hourlyRate, rounded);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Creates a user in the database with the given parameters
     * @param userLogin
     * @param userPassword
     * @param isAdmin
     * @param email
     * @param fullName
     * @return
     * @throws DalException 
     */
    @Override
    public User createUser(String userLogin, String userPassword, int isAdmin, String email, String fullName) throws bllException
    {
        try
        {
            return dalFacade.createUser(userLogin, userPassword, isAdmin, email, fullName);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Returns the id of a client based on client name.
     * @param clientName
     * @return
     * @throws DalException 
     */
    @Override
    public int getClientId(String clientName) throws bllException
    {
        try
        {
            return dalFacade.getClientId(clientName);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Returns a list of all projects in the database and the name of their client.
     * @return
     * @throws DalException 
     */
    @Override
    public List<Project> getProjectClientName() throws bllException
    {
        try
        {
            return dalFacade.getProjectClientName();
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Returns a list of all users in the database.
     * @return
     * @throws DalException 
     */
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

    /**
     * Updates time spent on a project in the database.
     * @param project
     * @throws DalException 
     */
    @Override
    public void updateProjectTime(Project project) throws bllException
    {
        try
        {
            dalFacade.updateProjectTime(project);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Adds time spent to the selected task and rounds up to the nearest 15 minutes.
     * @param usedTime
     * @param id
     * @throws DalException 
     */
    @Override
    public void addRoundedTime(double usedTime, int id) throws bllException
    {
        try
        {
            dalFacade.addRoundedTime(usedTime, id);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Deletes a user from the database.
     * @param user
     * @throws DalException 
     */
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

    /**
     * Returns 0 or 1 depending on if the user is an admin or not.
     * @param userLogin
     * @param userPassword
     * @return
     * @throws DalException 
     */
    @Override
    public int getIsAdminInt(String userLogin, String userPassword) throws bllException
    {
        try
        {
            return dalFacade.getIsAdminInt(userLogin, userPassword);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }
    
    /**
     * Updates the role of a user in the database. Can either be an admin or user.
     * @param user
     * @param isAdmin
     * @throws DalException 
     */
    @Override
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

    /**
     * Edits the parameters of a task in the database.
     * @param task
     * @throws DalException 
     */
    @Override
    public void editTask(Task task) throws bllException
    {
        try
        {
            dalFacade.editTask(task);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Returns a list of all clients in the database.
     * @return
     * @throws DalException 
     */
    @Override
    public List<Client> getAllClients() throws bllException
    {
        try
        {
            return dalFacade.getAllClients();
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Creates a client in the database with the given parameters.
     * @param clientName
     * @param contactPerson
     * @param email
     * @param hourlyRate
     * @param isDeleted
     * @return
     * @throws DalException 
     */
    @Override
    public Client createClient(String clientName, String contactPerson, String email, double hourlyRate, int isDeleted) throws bllException
    {
        try
        {
            return dalFacade.createClient(clientName, contactPerson, email, hourlyRate, isDeleted);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Returns all tasks on a given project.
     * @param projectId
     * @return
     * @throws DalException 
     */
    @Override
    public List<Task> getAllTasksOnProject(int projectId) throws bllException
    {
        try
        {
            return dalFacade.getAllTasksOnProject(projectId);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Converts startTime and endTime from String to LocalTime.
     * Calculates the difference between endTime and startTime and converts it to hours and minutes.
     * Then formats the hours and minutes to a string that is displayed in the program.
     * If the time goes past midnight it takes the difference between 24 and 
     * the difference between endTime and startTime.
     * @param startTime
     * @param endTime
     * @return
     * @throws bllException 
     */
    @Override
    public String timeFormatter(String startTime, String endTime) throws bllException
    {


        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);

        if (start.isBefore(end))
        {
            long difference = Duration.between(start, end).toMillis();
            long input = difference / 1000;
            long hours = (input - input % 3600) / 3600;
            long minutes = (input % 3600 - input % 3600 % 60) / 60;

            NumberFormat formatter = new DecimalFormat("00");

            String h = formatter.format(hours);
            String m = formatter.format(minutes);

            return h + ":" + m;

        } else if (start.equals(end))
        {
            return "00:00"; //Otherwise returns 24:00
        } else
        {
            long differenceTwo = Duration.ofHours(24).minus(Duration.between(end, start)).toMillis();
            long input = differenceTwo / 1000;
            long hours = (input - input % 3600) / 3600;
            long minutes = (input % 3600 - input % 3600 % 60) / 60;

            NumberFormat formatter = new DecimalFormat("00");

            String h = formatter.format(hours);
            String m = formatter.format(minutes);

            return h + ":" + m;
        }
    }

    /**
     * Calculates the time spent on a task and converts it to minutes.
     * Returns 0 if startTime and endTime are equal.
     * If time goes past midnight, returns the difference between 24 and
     * the difference between endTime and startTime.
     * @param startTime
     * @param endTime
     * @return
     * @throws bllException 
     */
    @Override
    public long timeCalculator(String startTime, String endTime) throws bllException
    {


        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);

        if (start.isBefore(end))
        {
            long duration = Duration.between(start, end).toMillis();


            long input = duration / 1000;
            long minuteTime = input / 60;

            if (minuteTime == 0)
            {
                minuteTime = 1;
            }

            return minuteTime;

        } else if (start.equals(end))
        {
            long minuteTime = 0;
            return minuteTime;
        } else
        {
            long durationAfterMidnight = Duration.ofHours(24).minus(Duration.between(end, start)).toMillis();


            long input = durationAfterMidnight / 1000;
            long minuteTime = input / 60;

            if (minuteTime == 0)
            {
                minuteTime = 1;
            }

            return minuteTime;
        }


    }

    /**
     * Updates the time spent on a task based on manual user input.
     * @param task
     * @return
     * @throws DalException 
     */
    @Override
    public boolean updateTask(Task task) throws bllException
    {
        try
        {
            return dalFacade.updateTask(task);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Archives a project in the database. 
     * @param project
     * @param isDeleted
     * @throws DalException 
     */
    @Override
    public void deleteProject(Project project, int isDeleted) throws bllException
    {
        try
        {
            dalFacade.deleteProject(project, isDeleted);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Archives a task in the database.
     * @param task
     * @param isDeleted
     * @throws DalException 
     */
    @Override
    public void deleteTask(Task task, int isDeleted) throws bllException
    {
        try
        {
            dalFacade.deleteTask(task, isDeleted);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Archives a client in the database.
     * @param client
     * @param isDeleted
     * @throws DalException 
     */
    @Override
    public void deleteClient(Client client, int isDeleted) throws bllException
    {
        try
        {
            dalFacade.deleteClient(client, isDeleted);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Archives all tasks on a given project.
     * @param task
     * @param isDeleted
     * @param projectId
     * @throws DalException 
     */
    @Override
    public void deleteTaskOnProject(Task task, int isDeleted, int projectId) throws bllException
    {
        try
        {
            dalFacade.deleteTaskOnProject(task, isDeleted, projectId);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    /**
     * Archives all projects on a given client.
     * @param project
     * @param isDeleted
     * @param clientId
     * @return
     * @throws DalException 
     */
    @Override
    public Project deleteProjectOnClient(Project project, int isDeleted, int clientId) throws bllException
    {
        try
        {
            return dalFacade.deleteProjectOnClient(project, isDeleted, clientId);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }
}
