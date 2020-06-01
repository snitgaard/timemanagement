/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.DAL.database;

import timemanagement.BE.Project;
import timemanagement.DAL.DalException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author The Cowboys
 */
public class ProjectDAO
{

    private DatabaseConnector dbCon;

    public ProjectDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }

    /**
     * Creates SQL connection and gets list of all projects.
     *
     * @return allProjects
     * @throws SQLException
     */
    public List<Project> getAllProjects() throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Project WHERE Project.isDeleted = 0;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Project> allProjects = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("Id");
                String projectName = rs.getString("projectName");
                int clientId = rs.getInt("clientId");
                String startDate = rs.getString("startDate");
                int usedTime = rs.getInt("usedTime");
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
     * @param isDeleted
     * @param clientName
     * @param hourlyRate
     * @param rounded
     * @return
     * @throws DalException
     */
    public Project createProject(String projectName, int clientId, String startDate,
            long usedTime, int isDeleted, String clientName, double hourlyRate, int rounded) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "INSERT INTO Project (projectName, clientId, startDate,"
                    + " usedTime, isDeleted, hourlyRate, rounded) VALUES (?,?,?,?,?,?,?);";
            
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, projectName);
            ps.setInt(2, clientId);
            ps.setString(3, startDate);
            ps.setLong(4, usedTime);
            ps.setInt(5, isDeleted);
            ps.setDouble(6, hourlyRate);
            ps.setInt(7, rounded);
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    int id = rs.getInt(1);
                    Project project = new Project(id, projectName, clientId, startDate,
                                                  usedTime, isDeleted, clientName, hourlyRate, rounded);
                    return project;
                }
            }
        } catch (SQLException ex)
        {
            throw new DalException("Could not create project");
        }
        return null;
    }

    /**
     * Returns a list of all active projects and the name of their client.
     * @return
     * @throws SQLException 
     */
    public List<Project> getProjectClientName() throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT Project.id, Project.projectName, Project.usedTime, Client.clientName, Project.startDate, Project.isDeleted, Project.hourlyRate, Project.rounded, Project.clientId\n"
                    + "FROM Project\n"
                    + "INNER JOIN Client ON Project.clientId=Client.id WHERE Project.isDeleted = 0;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Project> allProjects = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String projectName = rs.getString("projectName");
                String clientName = rs.getString("clientName");
                String startDate = rs.getString("startDate");
                long usedTime = rs.getLong("usedTime");
                int isDeleted = rs.getInt("isDeleted");
                double hourlyRate = rs.getDouble("hourlyRate");
                int rounded = rs.getInt("rounded");
                int clientId = rs.getInt("clientId");
                Project project = new Project(id, projectName, clientName, usedTime, startDate, isDeleted, hourlyRate, rounded, clientId);
                allProjects.add(project);
            }
            return allProjects;
        }
    }

    /**
     * Updates the time spent on a project 
     * by adding up the time spent on every task in the project.
     * @param project
     * @throws DalException 
     */
    public void updateProjectTime(Project project) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "UPDATE P SET P.usedTime = t.usedTime FROM Project AS p\n"
                    + "INNER JOIN(SELECT Task.projectId, SUM(Task.usedTime) usedTime\n"
                    + "		FROM Task\n"
                    + "		GROUP BY Task.projectId) t ON t.projectId = p.id\n"
                    + " WHERE projectId = (?)";


            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, project.getId());
            ps.executeUpdate();

        } catch (SQLException ex)
        {
            throw new DalException("Could not update time");
        }
    }

    /**
     * Archives a project in the database.
     * @param project
     * @param isDeleted
     * @throws DalException 
     */
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
            throw new DalException("Could not delete project");
        }
    }

    /**
     * Archives every project on a given client
     * @param project
     * @param isDeleted
     * @param clientId
     * @return
     * @throws DalException 
     */
    public Project deleteProjectOnClient(Project project, int isDeleted, int clientId) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            int id = project.getId();
            String sql = "UPDATE Project SET isDeleted = ? WHERE id =" + id + " AND clientId = ?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, isDeleted);
            ps.setInt(2, clientId);
            ps.executeUpdate();

        } catch (SQLException ex)
        {
            throw new DalException("Could not delete the project");
        }
        return project;

    }
}
