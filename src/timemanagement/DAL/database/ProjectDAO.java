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
            String sql = "SELECT * FROM Project;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Project> allProjects = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String projectName = rs.getString("projektNavn");
                int clientId = rs.getInt("kundeId");
                String startDato = rs.getString("startDato");
                int brugtTid = rs.getInt("brugtTid");
                int isDeleted = rs.getInt("isDeleted");
                String kundeNavn = "";
                double hourlyRate = rs.getDouble("hourlyRate");
                int rounded = rs.getInt("rounded");
                Project project = new Project(id, projectName, clientId, startDato, brugtTid, isDeleted, kundeNavn, hourlyRate, rounded);
                allProjects.add(project);
            }
            return allProjects;
        }
    }

    /**
     * Creates SQL Connection and creates a new Project.
     *
     * @param projektNavn
     * @param clientId
     * @param startDate
     * @param usedTime
     * @return
     * @throws DalException
     */
    public Project createProject(String projektNavn, int clientId, String startDate, long usedTime, int isDeleted, String clientName, double hourlyRate, int rounded) throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            String sql = "INSERT INTO Project (projektNavn, kundeId, startDato, brugtTid, isDeleted, hourlyRate, rounded) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, projektNavn);
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
                    Project project = new Project(id, projektNavn, clientId, startDate, usedTime, isDeleted, clientName, hourlyRate, rounded);
                    return project;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DalException("Could not create project");
        }
        return null;
    }

    public List<Project> getProjectKundeNavn() throws SQLException {
        try ( Connection con = dbCon.getConnection()) {
            String sql = "SELECT Project.id, Project.projektNavn, Project.brugtTid, Client.kundeNavn, Project.startDato, Project.isDeleted, Project.hourlyRate, Project.rounded\n"
                    + "FROM Project\n"
                    + "INNER JOIN Client ON Project.kundeId=Client.id;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Project> allProjects = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String projektNavn = rs.getString("projektNavn");
                String kundeNavn = rs.getString("kundeNavn");
                String startDato = rs.getString("startDato");
                long brugtTid = rs.getLong("brugtTid");
                int isDeleted = rs.getInt("isDeleted");
                double hourlyRate = rs.getDouble("hourlyRate");
                int rounded = rs.getInt("rounded");
                Project project = new Project(id, projektNavn, kundeNavn, brugtTid, startDato, isDeleted, hourlyRate, rounded);
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
            ps.setString(1, project.getProjectNavn());
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
