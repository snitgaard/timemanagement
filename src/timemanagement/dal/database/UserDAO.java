/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.dal.database;

import timemanagement.dal.DalException;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import timemanagement.be.User;

/**
 *
 * @author The Cowboys
 */
public class UserDAO {
    private DatabaseConnector dbCon;

    public UserDAO() throws IOException 
    { 
        dbCon = new DatabaseConnector();
    }
/**
 * Creates SQL connection and gets list of all users.
 * @return
 * @throws SQLException 
 */
    public List<User> getAllUsers() throws SQLException {
        try ( Connection con = dbCon.getConnection()) {
            String sql = "SELECT * FROM User;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<User> allUsers = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String userLogin = rs.getString("userlogin");
                String userPassword = rs.getString("userPassword");
                User user = new User(id, userLogin, userPassword);
                allUsers.add(user);
            }
            return allUsers;
        }
    }

    /**
     * Creates SQL Connection and deletes the selected User.
     * @param user
     * @throws DalException 
     */
    public void deleteUser(User user) throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            int id = user.getId();
            String sql = "DELETE FROM User WHERE id=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows != 1) {
                throw new DalException("Could not delete User");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DalException("Could not delete User");
        }
    }

    /**
     * Creates SQL Connetion and creates a new User.
     * @return
     * @throws DalException 
     */
    public boolean createUser() throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            String sql = "INSERT INTO User;";
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
            throw new DalException("Could not create User");
        }
        return false;
    }
    
    /**
     * If called this method will create a connection between the database
     * and the program. The SQL statement will be run afterwards.
     * Checks the user credentials based on user login and password
     *
     * @param userLogin
     * @param userPassword
     * @return true if credentials match with the database and false if not
     * @throws DalException
     */
    public boolean checkUserCredentials(String userLogin, String userPassword) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {

            String sql = "SELECT * FROM Student WHERE userLogin = ? AND userPassword = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userLogin);
            ps.setString(2, userPassword);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                return true;

            }
            return false;

        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not check student credentials");
        }
    }
}
