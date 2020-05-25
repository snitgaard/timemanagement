/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.DAL.database;

import timemanagement.DAL.DalException;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import timemanagement.BE.User;

/**
 * @author The Cowboys
 */
public class UserDAO
{

    private DatabaseConnector dbCon;

    public UserDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }

    /**
     * Creates SQL connection and gets list of all users.
     *
     * @return
     * @throws SQLException
     */
    public List<User> getAllUsers() throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM [User];";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<User> allUsers = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("Id");
                String userLogin = rs.getString("userLogin");
                String userPassword = rs.getString("userPassword");
                int isAdmin = rs.getInt("isAdmin");
                String adminRights = "";
                String email = rs.getString("email");
                String fullName = rs.getString("fullName");

                User user = new User(id, userLogin, userPassword, isAdmin, adminRights, email, fullName);
                allUsers.add(user);
            }
            return allUsers;
        }
    }

    /**
     * Creates SQL Connection and deletes the selected User.
     *
     * @param user
     * @throws DalException
     */
    public void deleteUser(User user) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            int id = user.getId();
            String sql = "DELETE FROM [User] WHERE id=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows != 1)
            {
                throw new DalException("Could not delete User");
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException("Could not delete User");
        }
    }

    /**
     * Creates SQL Connetion and creates a new User.
     *
     * @param userLogin
     * @param userPassword
     * @param isAdmin
     * @return
     * @throws DalException
     */
    public User createUser(String userLogin, String userPassword, int isAdmin, String email, String fullName) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "INSERT INTO [User] (userLogin, userPassword, isAdmin, email, fullName) VALUES (?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userLogin);
            ps.setString(2, userPassword);
            ps.setInt(3, isAdmin);
            ps.setString(4, email);
            ps.setString(5, fullName);
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    int id = (rs.getInt(1));
                    User user = new User(id, userLogin, userPassword, isAdmin, userLogin, email, fullName);
                    return user;
                }
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException("Could not create User");
        }
        return null;
    }

    /**
     * If called this method will create a connection between the database and
     * the program.The SQL statement will be run afterwards. Checks the user
     * credentials based on user login and password
     *
     * @param userLogin
     * @param userPassword
     * @param isAdmin
     * @return true if credentials match with the database and false if not
     * @throws DalException
     */
    public boolean checkUserCredentials(String userLogin, String userPassword, int isAdmin) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {

            String sql = "SELECT * FROM [User] WHERE userLogin = ? AND userPassword = ? AND isAdmin = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userLogin);
            ps.setString(2, userPassword);
            ps.setInt(3, isAdmin);
            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                return true;

            }
            return false;

        } catch (SQLException ex)
        {
            throw new DalException("Could not check user credentials");
        }
    }

    /**
     * If called this method will create a connection between the database and
     * the program. The SQL statement will be run afterwards. Gets a list of
     * users from userEmail
     *
     * @param userLogin
     * @return list of users called selectedUser
     * @throws DalException
     */
    public List<User> getUser(String userLogin) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {

            String sql = "SELECT * FROM [User] WHERE userLogin = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userLogin);
            ResultSet rs = ps.executeQuery();
            ArrayList<User> selectedUser = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("id");
                userLogin = rs.getString("userLogin");
                String userPassword = rs.getString("userPassword");
                int isAdmin = rs.getInt("isAdmin");
                String adminRights = "";
                String email = rs.getString("fullName");
                String fullName = rs.getString("fullName");

                User user = new User(id, userLogin, userPassword, isAdmin, adminRights, email, fullName);
                selectedUser.add(user);
            }
            return selectedUser;

        } catch (SQLException ex)
        {
            throw new DalException("Could not get user");
        }
    }

    /**
     * If called this method will create a connection between the database and
     * the program. The SQL statement will be run afterwards. Gets specific user
     * on index 0
     *
     * @param userLogin
     * @return index 0 of getStudent method
     * @throws DalException
     */
    public User getSpecificUser(String userLogin) throws DalException
    {
        return getUser(userLogin).get(0);
    }

    public int getIsAdminInt(String userLogin, String userPassword) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {

            String sql = "SELECT * FROM [User] WHERE userLogin = ? AND userPassword = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userLogin);
            ps.setString(2, userPassword);
            ResultSet rs = ps.executeQuery();
            int isAdmin = 0;
            while (rs.next())
            {
                isAdmin = rs.getInt("isAdmin");

            }
            return isAdmin;

        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not get user");
        }
    }

    public void updateUserRoles(User user, int isAdmin) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            int id = user.getId();
            String sql = "UPDATE [User] SET isAdmin = ? WHERE id =" + id + ";";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, isAdmin);
            ps.executeUpdate();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }


}
