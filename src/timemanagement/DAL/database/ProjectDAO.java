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
import java.util.logging.Level;
import java.util.logging.Logger;
import timemanagement.BE.Project;
import timemanagement.DAL.DalException;

/**
 *
 * @author The Cowboys
 */
public class ProjectDAO {

    private DatabaseConnector dbCon;

    public ProjectDAO() throws IOException {
        dbCon = new DatabaseConnector();
    }

    /**
     * Creates SQL connection and gets list of all projects.
     *
     * @return allProjects
     * @throws SQLException
     */
    public List<Project> getAllProjects() throws SQLException {
        try ( Connection con = dbCon.getConnection()) {
            String sql = "SELECT * FROM Project WHERE Project.isDeleted = 0;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Project> allProjects = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String projectName = rs.getString("projektNavn");
                int clientId = rs.getInt("kundeId");
                String startDate = rs.getString("startDato");
                int usedTime = rs.getInt("brugtTid");
                int isDeleted = rs.getInt("isDeleted");
                String clientName = "";
                double hourlyRate = rs.getDouble("hourlyRate");
                int rounded = rs.getInt("rounded");
                Project project = new Project(id, projectName, clientId, startDate, usedTime, isDeleted, clientName, hourlyRate, rounded);
                allProjects.add(project);
            }
            return allProjects;
        }
    }

    /**
     * Creates SQL Connection and creates a new Project.
     *
     * @param projectName
     * @param clientId
     * @param startDate
     * @param usedTime
     * @return
     * @throws DalException
     */
    public Project createProject(String projectName, int clientId, String startDate, long usedTime, int isDeleted, String clientName, double hourlyRate, int rounded) throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            String sql = "INSERT INTO Project (projektNavn, kundeId, startDato, brugtTid, isDeleted, hourlyRate, rounded) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, projectName);
            ps.setInt(2, clientId);
            ps.setString(3, startDate);
            ps.setLong(4, usedTime);
            ps.setInt(5, isDeleted);
            ps.setDouble(6, hourlyRate);
            ps.setInt(7, rounded);
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    Project project = new Project(id, projectName, clientId, startDate, usedTime, isDeleted, clientName, hourlyRate, rounded);
                    return project;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DalException("Could not create project");
        }
        return null;
    }

    public List<Project> getProjectClientName() throws SQLException {
        try ( Connection con = dbCon.getConnection()) {
            String sql = "SELECT Project.id, Project.projektNavn, Project.brugtTid, Client.kundeNavn, Project.startDato, Project.isDeleted, Project.hourlyRate, Project.rounded, Project.kundeId\n"
                    + "FROM Project\n"
                    + "INNER JOIN Client ON Project.kundeId=Client.id WHERE Project.isDeleted = 0;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Project> allProjects = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String projectName = rs.getString("projektNavn");
                String clientName = rs.getString("kundeNavn");
                String startDate = rs.getString("startDato");
                long usedTime = rs.getLong("brugtTid");
                int isDeleted = rs.getInt("isDeleted");
                double hourlyRate = rs.getDouble("hourlyRate");
                int rounded = rs.getInt("rounded");
                int kundeId = rs.getInt("kundeId");
                Project project = new Project(id, projectName, clientName, usedTime, startDate, isDeleted, hourlyRate, rounded, kundeId);
                allProjects.add(project);
            }
            return allProjects;
        }
    }

    public void updateProjectTime(Project project) throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            String sql = "UPDATE\n"
                    + "	P \n"
                    + "SET \n"
                    + "	P.brugtTid = t.brugtTid\n"
                    + "FROM\n"
                    + "	Project AS p\n"
                    + "INNER JOIN\n"
                    + "	(\n"
                    + "		SELECT Task.projektId, SUM(Task.brugtTid) brugtTid\n"
                    + "		FROM Task\n"
                    + "		GROUP BY Task.projektId\n"
                    + "	) t\n"
                    + "	ON t.projektId = p.id\n"
                    + " WHERE projektNavn = (?)";
            
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, project.getProjectName());
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex);
            throw new DalException("Could not fetch all classes");
        }
    }

    public void archiveProject(Project project) throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            int id = project.getId();
            String sql = "UPDATE Project SET isDeleted = ? WHERE id =" + id + ";";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, project.getIsDeleted());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DalException("Could not fetch all classes");
        }
    }
    
    public void deleteProject(Project project, int isDeleted) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            int id = project.getId();
            String sql = "UPDATE Project SET isDeleted = ? WHERE id =" + id + ";";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, isDeleted);
            ps.executeUpdate();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public Project deleteProjectOnClient(Project project, int isDeleted, int clientId) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            int id = project.getId();
            String sql = "UPDATE Project SET isDeleted = ? WHERE id =" + id + " AND kundeId = ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, isDeleted);
            ps.setInt(2, clientId);
            ps.executeUpdate();

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return project;

    }
}
