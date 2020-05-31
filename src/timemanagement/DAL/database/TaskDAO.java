/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.DAL.database;

import timemanagement.BE.Task;
import timemanagement.DAL.DalException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author The Cowboys
 */
public class TaskDAO
{

    private DatabaseConnector dbCon;

    public TaskDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }

    /**
     * Creates SQL connection and gets list of all tasks.
     *
     * @return
     * @throws SQLException
     */
    public List<Task> getAllTasks() throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Task WHERE isDeleted = 0;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Task> allProjects = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("Id");
                String taskName = rs.getString("taskName");
                int projectId = rs.getInt("projectId");
                int usedTime = rs.getInt("usedTime");
                String date = rs.getString("date");
                String description = rs.getString("description");
                int paid = rs.getInt("paid");
                String projectName = "";
                int isDeleted = rs.getInt("isDeleted");
                int userId = rs.getInt("userId");
                Task task = new Task(id, taskName, projectId, usedTime, date, description, paid, projectName, isDeleted, userId);
                allProjects.add(task);
            }
            return allProjects;
        }
    }

    /**
     * Creates SQL Connection and creates a new Task.
     *
     * @param taskName
     * @param projectId
     * @param usedTime
     * @param date
     * @param paid
     * @param description
     * @param projectName
     * @param isDeleted
     * @param userId
     * @return
     * @throws DalException
     */
    public Task createTask(String taskName, int projectId, long usedTime, String date, String description, int paid, String projectName, int isDeleted, int userId) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "INSERT INTO Task (taskName, projectId, usedTime, date, description, paid, isDeleted, userId) VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, taskName);
            ps.setInt(2, projectId);
            ps.setLong(3, usedTime);
            ps.setString(4, date);
            ps.setString(5, description);
            ps.setInt(6, paid);
            ps.setInt(7, isDeleted);
            ps.setInt(8, userId);
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    int id = rs.getInt(1);
                    Task task = new Task(id, taskName, projectId, usedTime, date, description, paid, projectName, isDeleted, userId);
                    return task;
                }
            }
        } catch (SQLException ex)
        {
            throw new DalException("Could not create task");
        }
        return null;
    }

    /**
     * Adds up time spent on a task
     * @param usedTime
     * @param id
     * @throws DalException 
     */
    public void addTime(long usedTime, int id) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "UPDATE Task SET usedTime = usedTime + ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, usedTime);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex)
        {
            throw new DalException("Could not fetch all classes");
        }
    }

    /**
     * Adds up time spent on a task and rounds up to nearest 15 minutes.
     * @param usedTime
     * @param id 
     * @throws DalException 
     */
    public void addRoundedTime(double usedTime, int id) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "UPDATE Task SET usedTime = usedTime + (CEILING(?/15.0) * 15.0) WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, usedTime);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex)
        {
            throw new DalException("Could not fetch add roundedTime");
        }
    }

    /**
     * Returns a list of all tasks in the database and their project name.
     * @return
     * @throws SQLException 
     */
    public List<Task> getAllTasksProjectName() throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT Task.id, Task.taskName, Task.usedTime, Task.date, Task.projectId, Task.userId, Task.description, Task.paid, Project.projectName\n"
                    + "FROM Task \n"
                    + "INNER JOIN Project ON Task.projectId=Project.id WHERE Task.isDeleted = 0;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Task> allProjects = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String taskName = rs.getString("taskName");
                String projectName = rs.getString("projectName");
                int usedTime = rs.getInt("usedTime");
                String date = rs.getString("date");
                int projectId = rs.getInt("projectId");
                int userId = rs.getInt("userId");
                String description = rs.getString("description");
                int paid = rs.getInt("paid");
                Task task = new Task(id, taskName, projectName, usedTime, date, projectId, userId, description, paid);
                allProjects.add(task);
            }
            return allProjects;
        }
    }


    /**
     * Edits the name and/or description and/or paid status of a task.
     * @param task 
     * @throws DalException 
     */
    public void editTask(Task task) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            int id = task.getId();
            String sql = "UPDATE Task SET taskName = ?, descriptionelse = ?, paid = ? WHERE id =" + id + ";";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, task.getTaskName());
            ps.setString(2, task.getDescription());
            ps.setInt(3, task.getPaid());
            ps.executeUpdate();
        } catch (SQLException ex)
        {
            throw new DalException("Could not edit task");
        }
    }

    /**
     * Returns a list of all tasks on a given project.
     * @param projectId
     * @return
     * @throws SQLException 
     */
    public List<Task> getAllTasksOnProject(int projectId) throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Task WHERE Task.projectId = ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Task> allProjects = new ArrayList<>();
            ps.setInt(1, projectId);
            while (rs.next())
            {
                int id = rs.getInt("Id");
                String taskName = rs.getString("taskName");

                int usedTime = rs.getInt("usedTime");
                String date = rs.getString("date");
                String description = rs.getString("description");
                int paid = rs.getInt("paid");
                String projectName = "";
                int isDeleted = rs.getInt("isDeleted");
                int userId = rs.getInt("userId");
                Task task = new Task(id, taskName, projectId, usedTime, date, description, paid, projectName, isDeleted, userId);
                allProjects.add(task);
            }
            return allProjects;
        }
    }

    /**
     * Updates time spent on a task based on manual user input.
     * @param task
     * @return 
     */
    public boolean updateTask(Task task) 
    {
        try (Connection con = dbCon.getConnection())
        {
            int id = task.getId();

            String sql = "UPDATE Task SET usedTime = CEILING(?) WHERE Id =" + id + ";";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, task.getUsedTime());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex)
        {
            return false;
        }
    }

    /**
     * Archives a task in the database.
     * @param task
     * @param isDeleted
     * @throws DalException 
     */
    public void deleteTask(Task task, int isDeleted) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            int id = task.getId();

            String sql = "UPDATE Task SET isDeleted = ? WHERE id =" + id + ";";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, isDeleted);
            ps.executeUpdate();
        } catch (SQLException ex)
        {
            throw new DalException("Could not delete task");
        }
    }

    /**
     * Archives every task on a given project.
     * @param task
     * @param isDeleted
     * @param projectId
     * @throws DalException 
     */
    public void deleteTaskOnProject(Task task, int isDeleted, int projectId) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            int id = task.getId();
            String sql = "UPDATE Task SET isDeleted = ? WHERE id =" + id + " AND projectId = ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, isDeleted);
            ps.setInt(2, projectId);
            ps.executeUpdate();
        } catch (SQLException ex)
        {
            throw new DalException("Could not delete task on project");
        }
    }
}
