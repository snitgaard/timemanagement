/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.DAL.database;

import timemanagement.BE.Client;
import timemanagement.DAL.DalException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
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
     * Creates SQL connection and gets list of all clients.
     *
     * @return
     * @throws SQLException
     */
    public List<Client> getAllClients() throws SQLException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Client WHERE isDeleted = 0;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Client> allClients = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("Id");
                String clientName = rs.getString("clientName");
                String contactPerson = rs.getString("contactPerson");
                String email = rs.getString("email");
                double hourlyRate = rs.getInt("hourlyRate");
                int isDeleted = rs.getInt("isDeleted");
                Client client = new Client(id, clientName, contactPerson, email, hourlyRate, isDeleted);
                allClients.add(client);
            }
            return allClients;
        }
    }

    /**
     * Creates SQL Connetion and creates a new Client.
     *
     * @param clientName
     * @param contactPerson
     * @param email
     * @param isDeleted
     * @param hourlyRate
     * @return
     * @throws DalException
     */
    public Client createClient(String clientName, String contactPerson, String email, double hourlyRate, int isDeleted) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "INSERT INTO Client (clientName, contactPerson, email, hourlyRate, isDeleted) VALUES (?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, clientName);
            ps.setString(2, contactPerson);
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
                    Client client = new Client(id, clientName, contactPerson, email, hourlyRate, isDeleted);
                    return client;
                }
            }

        } catch (SQLException ex)
        {
            throw new DalException("Could not create Client");
        }
        return null;
    }

    /**
     * If called this method will create a connection between the database and
     * the program. The SQL statement will be run afterwards. using the name of
     * a client, this method will get the id.
     *
     * @param clientName
     * @return clientId
     * @throws DalException
     */
    public int getClientId(String clientName) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {

            String sql = "SELECT * FROM Client WHERE clientName = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, clientName);
            ResultSet rs = ps.executeQuery();
            int clientId = 0;
            while (rs.next())
            {
                clientId = rs.getInt("id");

            }
            return clientId;

        } catch (SQLException ex)
        {
            throw new DalException("Could not get user");
        }
    }

    /**
     * Archives a client in the database.
     * @param client
     * @param isDeleted
     * @throws DalException 
     */
    public void deleteClient(Client client, int isDeleted) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            int id = client.getId();
            String sql = "UPDATE Client SET isDeleted = ? WHERE id =" + id + ";";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, isDeleted);
            ps.executeUpdate();
        } catch (SQLException ex)
        {
            throw new DalException("Could not delete user");
        }
    }
}
