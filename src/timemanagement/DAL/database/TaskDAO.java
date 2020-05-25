/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.DAL.database;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import timemanagement.BE.Project;
import timemanagement.BE.Task;
import timemanagement.DAL.DalException;

/**
 *
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
                String taskName = rs.getString("opgaveNavn");
                int projectId = rs.getInt("projektId");
                int usedTime = rs.getInt("brugtTid");
                String date = rs.getString("dato");
                String description = rs.getString("beskrivelse");
                int payed = rs.getInt("betalt");
                String projectName = "";
                int isDeleted = rs.getInt("isDeleted");
                int userId = rs.getInt("userId");
                Task task = new Task(id, taskName, projectId, usedTime, date, description, payed, projectName, isDeleted, userId);
                allProjects.add(task);
            }
            return allProjects;
        }
    }

    /**
     * Creates SQL Connection and creates a new Task.
     *
     * @return
     * @throws DalException
     */
    public Task createTask(String taskName, int projectId, long usedTime, String date, String description, int payed, String projectName, int isDeleted, int userId) throws DalException {
        try (Connection con = dbCon.getConnection()) {
            String sql = "INSERT INTO Task (opgaveNavn, projektId, brugtTid, dato, beskrivelse, betalt, isDeleted, userId) VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, taskName);
            ps.setInt(2, projectId);
            ps.setLong(3, usedTime);
            ps.setString(4, date);
            ps.setString(5, description);
            ps.setInt(6, payed);
            ps.setInt(7, isDeleted);
            ps.setInt(8, userId);
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    int id = rs.getInt(1);
                    Task task = new Task(id, taskName, projectId, usedTime, date, description, payed, projectName, isDeleted, userId);
                    return task;
                }
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException("Could not create task");
        }
        return null;
    }

    public void addTime(long usedTime, int id) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "UPDATE Task SET brugtTid = brugtTid + ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, usedTime);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex)
        {
            throw new DalException("Could not fetch all classes");
        }
    }
    
    public void addRoundedTime(double usedTime, int id)
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "UPDATE Task SET brugtTid = brugtTid + (CEILING(?/15.0) * 15.0) WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, usedTime);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public List<Task> getAllTasksProjectName() throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT Task.id, Task.opgaveNavn, Task.brugtTid, Task.dato, Task.projektId, Task.userId, Task.beskrivelse, Task.betalt, Project.projektNavn\n"
                    + "FROM Task \n"
                    + "INNER JOIN Project ON Task.projektId=Project.id WHERE Task.isDeleted = 0;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Task> allProjects = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String taskName = rs.getString("opgaveNavn");
                String projectName = rs.getString("projektNavn");
                int usedTime = rs.getInt("brugtTid");
                String date = rs.getString("dato");
                int projectId = rs.getInt("projektId");
                int userId = rs.getInt("userId");
                String description = rs.getString("beskrivelse");
                int payed = rs.getInt("betalt");
                Task task = new Task(id, taskName, projectName, usedTime, date, projectId, userId, description, payed);
                allProjects.add(task);
            }
            return allProjects;
        }
    }

    
    
    public void editTask(Task task)
    {
        try (Connection con = dbCon.getConnection())
        {
            int id = task.getId();
            String sql = "UPDATE Task SET opgaveNavn = ?, beskrivelse = ?, betalt = ? WHERE id =" + id + ";";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, task.getTaskName());
            ps.setString(2, task.getDescription());
            ps.setInt(3, task.getPayed());
            ps.executeUpdate();
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public List<Task> getAllTasksOnProject(int projectId) throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Task WHERE Task.projektId = ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Task> allProjects = new ArrayList<>();
            ps.setInt(1, projectId);
            while (rs.next())
            {
                int id = rs.getInt("Id");
                String taskName = rs.getString("opgaveNavn");
                
                int usedTime = rs.getInt("brugtTid");
                String date = rs.getString("dato");
                String description = rs.getString("beskrivelse");
                int payed = rs.getInt("betalt");
                String projectName = "";
                int isDeleted = rs.getInt("isDeleted");
                int userId = rs.getInt("userId");
                Task task = new Task(id, taskName, projectId, usedTime, date, description, payed, projectName, isDeleted, userId);
                allProjects.add(task);
            }
            return allProjects;
        }
    }
    
     public boolean updateTask(Task task)
     {
     try (Connection con = dbCon.getConnection())
        {
            int id = task.getId();

            String sql = "UPDATE Task SET brugtTid = CEILING(?) WHERE Id =" + id + ";";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, task.getUsedTime());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
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
            ex.printStackTrace();
        }
    }
    
    public void deleteTaskOnProject(Task task, int isDeleted, int projectId) throws DalException
            {
        try (Connection con = dbCon.getConnection())
        {
            int id = task.getId();
            String sql = "UPDATE Task SET isDeleted = ? WHERE id =" + id + " AND projektId = ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, isDeleted);
            ps.setInt(2, projectId);
            ps.executeUpdate();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
