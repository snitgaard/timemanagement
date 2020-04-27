/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.DAL.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import timemanagement.BE.Project;
import timemanagement.BE.Task;
import timemanagement.DAL.DalException;

/**
 *
 * @author CSnit
 */
public class TaskDAO {

private DatabaseConnector dbCon;

    public TaskDAO() throws IOException 
    { 
        dbCon = new DatabaseConnector();
    }
/**
 * Creates SQL connection and gets list of all tasks.
 * @return
 * @throws SQLException 
 */
    public List<Task> getAllTasks() throws SQLException {
        try ( Connection con = dbCon.getConnection()) {
            String sql = "SELECT * FROM Task;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Task> allProjects = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String projektNavn = rs.getString("opgaveNavn");
                int projektId = rs.getInt("projektId");
                int brugtTid = rs.getInt("brugtTid");
                String dato = rs.getString("dato");
                Task task = new Task(id, projektNavn, projektNavn, brugtTid, dato);
                allProjects.add(task);
            }
            return allProjects;
        }
    }

    /**
     * Creates SQL Connection and deletes the selected tasks.
     * @param project
     * @throws DalException 
     */
    public void deleteTask(Task task) throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            int id = task.getId();
            String sql = "DELETE FROM Task WHERE id=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows != 1) {
                throw new DalException("Shit fuck, could not delete");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DalException("Could not delete Task");
        }
    }

    /**
     * Creates SQL Connetion and creates a new Task.
     * @return
     * @throws DalException 
     */
    public boolean createTask() throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            String sql = "INSERT INTO Task;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DalException("Could not create task");
        }
        return false;
    }
}
