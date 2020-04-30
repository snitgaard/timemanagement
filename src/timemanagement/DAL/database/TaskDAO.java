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
                Task task = new Task(id, opgaveNavn, projektId, brugtTid, dato, beskrivelse, betalt);
                allProjects.add(task);
            }
            return allProjects;
        }
    }

    /**
     * Creates SQL Connection and deletes the selected tasks.
     *
     * @param project
     * @throws DalException
     */
    public void deleteTask(Task task) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            int id = task.getId();
            String sql = "DELETE FROM Task WHERE id=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows != 1)
            {
                throw new DalException("Shit fuck, could not delete");
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException("Could not delete Task");
        }
    }

    /**
     * Creates SQL Connection and creates a new Task.
     *
     * @return
     * @throws DalException
     */
    public boolean createTask(String opgaveNavn, int projektId, long brugtTid, String dato, String beskrivelse, int betalt) throws DalException {
        try (Connection con = dbCon.getConnection()) {
            String sql = "INSERT INTO Task (opgaveNavn, projektId, brugtTid, dato, beskrivelse, betalt) VALUES (?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, opgaveNavn);
            ps.setInt(2, projektId);
            ps.setLong(3, brugtTid);
            ps.setString(4, dato);
            ps.setString(5, beskrivelse);
            ps.setInt(6, betalt);
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    return true;
                }
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException("Could not create task");
        }
        return false;
    }

    public void addTime(long brugtTid, String opgaveNavn) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "UPDATE Task SET brugtTid = brugtTid + ? WHERE opgaveNavn = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, brugtTid);
            ps.setString(2, opgaveNavn);
            ps.executeUpdate();

        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not fetch all classes");
        }
    }

    public List<Task> getAllTasksProjektNavn() throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT Task.opgaveNavn, Task.brugtTid, Task.dato, Project.projektNavn\n"
                    + "FROM Task \n"
                    + "INNER JOIN Project ON Task.projektId=Project.id;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Task> allProjects = new ArrayList<>();
            while (rs.next())
            {
                String opgaveNavn = rs.getString("opgaveNavn");
                String projektNavn = rs.getString("projektNavn");
                int brugtTid = rs.getInt("brugtTid");
                String dato = rs.getString("dato");
                Task task = new Task(opgaveNavn, projektNavn, brugtTid, dato);
                allProjects.add(task);
            }
            return allProjects;
        }
    }

    public List<Task> getAllTasksByProject(int projektId) throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Task WHERE projektId = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, projektId);
            ResultSet rs = ps.executeQuery();
            ArrayList<Task> allProjects = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String opgaveNavn = rs.getString("opgaveNavn");
                projektId = rs.getInt("projektId");
                int brugtTid = rs.getInt("brugtTid");
                String dato = rs.getString("dato");
                String beskrivelse = rs.getString("beskrivelse");
                int betalt = rs.getInt("betalt");
                Task task = new Task(id, opgaveNavn, projektId, brugtTid, dato, beskrivelse, betalt);
                allProjects.add(task);
            }
            return allProjects;
        }
    }

    public boolean updateTask(int brugtTid, int id)
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "UPDATE Task SET brugtTid = ? WHERE Id = ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, brugtTid);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            return false;
        }
    }
}
