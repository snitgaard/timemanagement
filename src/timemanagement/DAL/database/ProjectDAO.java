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
            String sql = "SELECT * FROM Project;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Project> allProjects = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("Id");
                String projektNavn = rs.getString("projektNavn");
                int kundeId = rs.getInt("kundeId");
                String startDato = rs.getString("startDato");
                int brugtTid = rs.getInt("brugtTid");
                int ongoing = rs.getInt("ongoing");
                int brugtTidMinutter = 0;
                Project project = new Project(id, projektNavn, kundeId, startDato, brugtTid, ongoing, brugtTidMinutter);
                allProjects.add(project);
            }
            return allProjects;
        }
    }

    /**
     * Creates SQL Connection and deletes the selected Project.
     *
     * @param project
     * @throws DalException
     */
    public void deleteProject(Project project) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            int id = project.getId();
            String sql = "DELETE FROM Project WHERE id=?;";
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
            throw new DalException("Could not delete Project");
        }
    }

    /**
     * Creates SQL Connection and creates a new Project.
     *
     * @param projektNavn
     * @param kundeId
     * @param startDato
     * @param brugtTid
     * @return
     * @throws DalException
     */
    public Project createProject(String projektNavn, int kundeId, String startDato, long brugtTid, int ongoing, long brugtTidMinutter) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "INSERT INTO Project (projektNavn, kundeId, startDato, brugtTid, ongoing) VALUES (?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, projektNavn);
            ps.setInt(2, kundeId);
            ps.setString(3, startDato);
            ps.setLong(4, brugtTid);
            ps.setInt(5, ongoing);
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    int id = rs.getInt(1);
                    Project project = new Project(id, projektNavn, kundeId, startDato, brugtTid, ongoing, brugtTidMinutter);
                    return project;
                }
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException("Could not create project");
        }
        return null;
    }

    public List<Project> getProjectKundeNavn() throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT Project.id, Project.projektNavn, Project.brugtTid, Kunde.kundeNavn, Project.startDato, Project.ongoing\n"
                    + "FROM Project\n"
                    + "INNER JOIN Kunde ON Project.kundeId=Kunde.id;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Project> allProjects = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String projektNavn = rs.getString("projektNavn");
                String kundeNavn = rs.getString("kundeNavn");
                String startDato = rs.getString("startDato");
                long brugtTid = rs.getLong("brugtTid");
                int ongoing = rs.getInt("ongoing");
                long brugtTidMinutter = 0;
                Project project = new Project(id, projektNavn, kundeNavn, brugtTid, startDato, ongoing, brugtTidMinutter);
                project.setBrugtTidMinutter(project.getBrugtTid() * 15);
                allProjects.add(project);
            }
            return allProjects;
        }
    }
    
    public void addProjectTime(long brugtTid, String projektNavn) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "UPDATE Project SET brugtTid = CEILING(brugtTid + ?) WHERE projektNavn = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, brugtTid);
            ps.setString(2, projektNavn);
            ps.executeUpdate();

        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not fetch all classes");
        }
    }
    
    public void archiveProject (Project project) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            int id = project.getId();
            String sql = "UPDATE Project SET ongoing = ? WHERE id =" + id + ";";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, project.getOngoing());
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DalException("Could not fetch all classes");
        }
    }
}
