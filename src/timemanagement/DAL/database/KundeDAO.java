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
import timemanagement.BE.Kunde;
import timemanagement.DAL.DalException;

/**
 *
 * @author The Cowboys
 */
public class KundeDAO {
    private DatabaseConnector dbCon;

    public KundeDAO() throws IOException {
        dbCon = new DatabaseConnector();
    }

    /**
     * Creates SQL connection and gets list of all kunder.
     *
     * @return
     * @throws SQLException
     */
    public List<Kunde> getAllKunder() throws SQLException {
        try ( Connection con = dbCon.getConnection()) {
            String sql = "SELECT * FROM Kunde;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Kunde> allKunder = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String kundeNavn = rs.getString("kundeNavn");
                Kunde kunde = new Kunde(id, kundeNavn);
                allKunder.add(kunde);
            }
            return allKunder;
        }
    }

    /**
     * Creates SQL Connection and deletes the selected Kunde.
     *
     * @param user
     * @throws DalException
     */
    public void deleteKunde(Kunde kunde) throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            int id = kunde.getId();
            String sql = "DELETE FROM Kunde WHERE id=?;";
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
     * Creates SQL Connetion and creates a new Kunde.
     *
     * @return
     * @throws DalException
     */
    public boolean createKunde(String kundeNavn) throws DalException {
        try ( Connection con = dbCon.getConnection()) {
            String sql = "INSERT INTO Kunde (kundeNavn) VALUES (?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, kundeNavn);
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
     * If called this method will create a connection between the database and
     * the program. The SQL statement will be run afterwards. Gets a list of
     * users from userEmail
     *
     * @param kundeNavn
     * @return list of users called selectedUser
     * @throws DalException
     */
    public int getKundeId(String kundeNavn) throws DalException {
        try ( Connection con = dbCon.getConnection()) {

            String sql = "SELECT * FROM Kunde WHERE kundeNavn = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, kundeNavn);
            ResultSet rs = ps.executeQuery();
            int kundeId = 0;
            while (rs.next()) {
                kundeId = rs.getInt("id");

            }
            return kundeId;

        } catch (SQLException ex) {
            System.out.println(ex);
            throw new DalException("Could not get user");
        }
    }
    
}
