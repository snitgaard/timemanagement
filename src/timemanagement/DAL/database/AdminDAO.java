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
import timemanagement.BE.Admin;
import timemanagement.DAL.DalException;

/**
 *
 * @author The Cowboys
 */
public class AdminDAO {

    private DatabaseConnector dbCon;

    public AdminDAO() throws IOException {
        dbCon = new DatabaseConnector();
    }

    /**
     * Creates SQL connection and gets list of all Admins.
     *
     * @return
     * @throws SQLException
     */
    public List<Admin> getAllAdmins() throws SQLException {
        try ( Connection con = dbCon.getConnection()) {
            String sql = "SELECT * FROM Admin;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Admin> allAdmins = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String adminLogin = rs.getString("adminLogin");
                String adminPassword = rs.getString("adminPassword");
                Admin admin = new Admin(id, adminLogin, adminPassword);
                allAdmins.add(admin);
            }
            return allAdmins;
        }
    }

    /**
     * Creates SQL Connection and deletes the selected Admin.
     *
     * @param Admin
     * @throws DalException
     */
    public void deleteAdmin(Admin admin) throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            int id = admin.getId();
            String sql = "DELETE FROM Admin WHERE id=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows != 1) {
                throw new DalException("Could not delete Admin");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new DalException("Could not delete Admin");
        }
    }

    /**
     * Creates SQL Connetion and creates a new Admin.
     *
     * @return
     * @throws DalException
     */
    public boolean createAdmin() throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            String sql = "INSERT INTO admin;";
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
            throw new DalException("Could not create Admin");
        }
        return false;
    }

    /**
     * If called this method will create a connection between the database and
     * the program. The SQL statement will be run afterwards. Checks the user
     * credentials based on user login and password
     *
     * @param userLogin
     * @param userPassword
     * @return true if credentials match with the database and false if not
     * @throws DalException
     */
    public boolean checkAdminCredentials(String adminLogin, String adminPassword) throws DalException {
        try ( Connection con = dbCon.getConnection()) {

            String sql = "SELECT * FROM [Admin] WHERE adminLogin = ? AND adminPassword = ?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, adminLogin);
            ps.setString(2, adminPassword);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return true;

            }
            return false;

        } catch (SQLException ex) {
            System.out.println(ex);
            throw new DalException("Could not check admin credentials");
        }
    }

    /**
     * If called this method will create a connection between the database and
     * the program. The SQL statement will be run afterwards. Gets a list of
     * users from userEmail
     *
     * @param adminLogin
     * @return list of users called selectedUser
     * @throws DalException
     */
    public List<Admin> getAdmin(String adminLogin) throws DalException {
        try ( Connection con = dbCon.getConnection()) {

            String sql = "SELECT * FROM [Admin] WHERE adminLogin = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, adminLogin);
            ResultSet rs = ps.executeQuery();
            ArrayList<Admin> selectedAdmin = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                adminLogin = rs.getString("adminLogin");
                String adminPassword = rs.getString("adminPassword");

                Admin admin = new Admin(id, adminLogin, adminPassword);
                selectedAdmin.add(admin);
            }
            return selectedAdmin;

        } catch (SQLException ex) {
            System.out.println(ex);
            throw new DalException("Could not get user");
        }
    }

    /**
     * If called this method will create a connection between the database and
     * the program. The SQL statement will be run afterwards. Gets specific user
     * on index 0
     *
     * @param adminLogin
     * @return index 0 of getAdmin method
     * @throws DalException
     */
    public Admin getSpecificAdmin(String adminLogin) throws DalException {
        return getAdmin(adminLogin).get(0);
    }
}
