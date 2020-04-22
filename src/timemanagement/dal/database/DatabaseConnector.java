/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.dal.database;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 * DatabaseConnector Class
 *
 * @author The Cowboys
 */
public class DatabaseConnector
{
    private SQLServerDataSource dataSource;

    /**
     * This method reads a file in our root folder, the file consists of credentials needed to connect to the database.
     * The data gathered in the DBSettings file is then inserted and then connects to the database
     *
     * @throws IOException
     */
    DatabaseConnector() throws IOException
    {
        Properties props = new Properties();
        props.load(new FileReader("DBSettings.txt"));
        dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName(props.getProperty("database"));
        dataSource.setUser(props.getProperty("user"));
        dataSource.setPassword(props.getProperty("password"));
        dataSource.setServerName(props.getProperty("server"));
    }

    /*
     *This method returns the data created in the constructor above
     */
    Connection getConnection() throws SQLServerException
    {
        return dataSource.getConnection();
    }
}