/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.dal.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import timemanagement.be.Project;
import timemanagement.dal.DalException;

/**
 *
 * @author The Cowboys
 */
public class ProjectDAO {
    private DatabaseConnector dbCon;

    public ProjectDAO() throws IOException 
    { 
        dbCon = new DatabaseConnector();
    }
/**
 * Creates SQL connection and gets list of all projects.
 * @return
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
                Project project = new Project(id);
                allProjects.add(project);
            }
            return allProjects;
        }
    }

    /**
     * Creates SQL Connection and deletes the selected Project.
     * @param project
     * @throws DalException 
     */
    public void deleteProject(Project project) throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            int id = project.getId();
            String sql = "DELETE FROM Project WHERE id=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows != 1) {
                throw new DalException("yes");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DalException("Could not delete Project");
        }
    }

    /**
     * Creates SQL Connetion and creates a new Project.
     * @return
     * @throws DalException 
     */
    public boolean createProject() throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            String sql = "INSERT INTO Project;";
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
            throw new DalException("Could not create project");
        }
        return false;
    }
}