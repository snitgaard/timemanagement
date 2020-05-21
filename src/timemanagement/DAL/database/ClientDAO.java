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
import timemanagement.BE.Client;
import timemanagement.DAL.DalException;

/**
 *
 * @author The Cowboys
 */
public class ClientDAO
{

    private DatabaseConnector dbCon;

    public ClientDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }

    /**
     * Creates SQL connection and gets list of all kunder.
     *
     * @return
     * @throws SQLException
     */
    public List<Client> getAllClients() throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Kunde;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Client> allKunder = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("Id");
                String kundeNavn = rs.getString("kundeNavn");
                String kontaktPerson = rs.getString("kontaktPerson");
                String email = rs.getString("email");
                double hourlyRate = rs.getInt("hourlyRate");
                int isDeleted = rs.getInt("isDeleted");
                Client kunde = new Client(id, kundeNavn, kontaktPerson, email, hourlyRate, isDeleted);
                allKunder.add(kunde);
            }
            return allKunder;
        }
    }

    /**
     * Creates SQL Connetion and creates a new Client.
     *
     * @return
     * @throws DalException
     */
    public Client createClient(String kundeNavn, String kontaktPerson, String email, double hourlyRate, int isDeleted) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "INSERT INTO Kunde (kundeNavn, kontaktPerson, email, hourlyRate, isDeleted) VALUES (?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, kundeNavn);
            ps.setString(2, kontaktPerson);
            ps.setString(3, email);
            ps.setDouble(4, hourlyRate);
            ps.setInt(5, isDeleted);
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    int id = rs.getInt(1);
                    Client kunde = new Client(id, kundeNavn, kontaktPerson, email, hourlyRate, isDeleted);
                    return kunde;
                }
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException("Could not create Client");
        }
        return null;
    }

    /**
     * If called this method will create a connection between the database and
     * the program. The SQL statement will be run afterwards. using the name of
     * a customer, this method will get the id.
     *
     * @param kundeNavn
     * @return kundeId
     * @throws DalException
     */
    public int getClientId(String kundeNavn) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {

            String sql = "SELECT * FROM Kunde WHERE kundeNavn = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, kundeNavn);
            ResultSet rs = ps.executeQuery();
            int kundeId = 0;
            while (rs.next())
            {
                kundeId = rs.getInt("id");

            }
            return kundeId;

        } catch (SQLException ex)
        {
            System.out.println(ex);
            throw new DalException("Could not get user");
        }
    }

     public void deleteClient(Client kunde, int isDeleted) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            int id = kunde.getId();
            String sql = "UPDATE Kunde SET isDeleted = ? WHERE id =" + id + ";";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, isDeleted);
            ps.executeUpdate();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
