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
            String sql = "SELECT * FROM Task;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Task> allProjects = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("Id");
                String opgaveNavn = rs.getString("opgaveNavn");
                int projektId = rs.getInt("projektId");
                int brugtTid = rs.getInt("brugtTid");
                String dato = rs.getString("dato");
                String beskrivelse = rs.getString("beskrivelse");
                int betalt = rs.getInt("betalt");
                String projektNavn = "";
                int isDeleted = rs.getInt("isDeleted");
                int userId = rs.getInt("userId");
                Task task = new Task(id, opgaveNavn, projektId, brugtTid, dato, beskrivelse, betalt, projektNavn, isDeleted, userId);
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
    public Task createTask(String opgaveNavn, int projektId, long brugtTid, String dato, String beskrivelse, int betalt, String projektNavn, int isDeleted, int userId) throws DalException {
        try (Connection con = dbCon.getConnection()) {
            String sql = "INSERT INTO Task (opgaveNavn, projektId, brugtTid, dato, beskrivelse, betalt, isDeleted, userId) VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, opgaveNavn);
            ps.setInt(2, projektId);
            ps.setLong(3, brugtTid);
            ps.setString(4, dato);
            ps.setString(5, beskrivelse);
            ps.setInt(6, betalt);
            ps.setInt(7, isDeleted);
            ps.setInt(8, userId);
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    int id = rs.getInt(1);
                    Task task = new Task(id, opgaveNavn, projektId, brugtTid, dato, beskrivelse, betalt, projektNavn, isDeleted, userId);
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

    public void addTime(long brugtTid, int id) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "UPDATE Task SET brugtTid = brugtTid + ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, brugtTid);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not fetch all classes");
        }
    }
    
    public void addRoundedTime(long brugtTid, int id)
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "UPDATE Task SET brugtTid = brugtTid + (CEILING(?) * 15) WHERE id = ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, brugtTid);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public List<Task> getAllTasksProjektNavn() throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT Task.id, Task.opgaveNavn, Task.brugtTid, Task.dato, Task.projektId, Task.userId, Task.beskrivelse, Task.betalt, Project.projektNavn\n"
                    + "FROM Task \n"
                    + "INNER JOIN Project ON Task.projektId=Project.id;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Task> allProjects = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String opgaveNavn = rs.getString("opgaveNavn");
                String projektNavn = rs.getString("projektNavn");
                int brugtTid = rs.getInt("brugtTid");
                String dato = rs.getString("dato");
                int projektId = rs.getInt("projektId");
                int userId = rs.getInt("userId");
                String beskrivelse = rs.getString("beskrivelse");
                int betalt = rs.getInt("betalt");
                Task task = new Task(id, opgaveNavn, projektNavn, brugtTid, dato, projektId, userId, beskrivelse, betalt);
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
            ps.setString(1, task.getOpgaveNavn());
            ps.setString(2, task.getBeskrivelse());
            ps.setInt(3, task.getBetalt());
            ps.executeUpdate();
        } 
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public List<Task> getAllTasksOnProject(int projektId) throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Task WHERE Task.projektId = ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Task> allProjects = new ArrayList<>();
            ps.setInt(1, projektId);
            while (rs.next())
            {
                int id = rs.getInt("Id");
                String opgaveNavn = rs.getString("opgaveNavn");
                
                int brugtTid = rs.getInt("brugtTid");
                String dato = rs.getString("dato");
                String beskrivelse = rs.getString("beskrivelse");
                int betalt = rs.getInt("betalt");
                String projektNavn = "";
                int isDeleted = rs.getInt("isDeleted");
                int userId = rs.getInt("userId");
                Task task = new Task(id, opgaveNavn, projektId, brugtTid, dato, beskrivelse, betalt, projektNavn, isDeleted, userId);
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
            ps.setLong(1, task.getBrugtTid());
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
    
    public void deleteTaskOnProject(Task task, int isDeleted, int projektId) throws DalException
            {
        try (Connection con = dbCon.getConnection())
        {
            int id = task.getId();
            String sql = "UPDATE Task SET isDeleted = ? WHERE id =" + id + " AND projektId = ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, isDeleted);
            ps.setInt(2, projektId);
            ps.executeUpdate();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
