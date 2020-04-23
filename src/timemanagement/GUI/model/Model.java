/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.gui.model;

import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import timemanagement.BE.Admin;
import timemanagement.BLL.bllManager;
import timemanagement.BE.User;
import timemanagement.BLL.bllException;

/**
 *
 * @author jigzi
 */
public class Model
{

    private bllManager bllManager;
    private ObservableList<String> allProjects;
    private ObservableList<String> allTasks;

    public Model() throws IOException
    {
        bllManager = new bllManager();
    }

    public boolean checkUserCredentials(String userLogin, String userPassword) throws ModelException
    {
        try
        {
            return bllManager.checkUserCredentials(userLogin, userPassword);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public boolean checkAdminCredentials(String adminLogin, String adminPassword) throws ModelException
    {
        try
        {
            return bllManager.checkAdminCredentials(adminLogin, adminPassword);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public List<User> getUser(String userLogin) throws ModelException
    {
        try
        {
            return bllManager.getUser(userLogin);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public ObservableList<String> getAllProjects() throws ModelException
    {
        allProjects = FXCollections.observableArrayList();
        try
        {
            allProjects.addAll(bllManager.getAllProjects());
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        return allProjects;
    }

    public User getSpecificUser(String userLogin) throws ModelException
    {
        try
        {
            return bllManager.getSpecificUser(userLogin);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public List<Admin> getAdmin(String adminLogin) throws ModelException
    {
        try
        {
            return bllManager.getAdmin(adminLogin);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }

    public Admin getSpecificAdmin(String adminLogin) throws ModelException
    {
        try
        {
            return bllManager.getSpecificAdmin(adminLogin);
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
    }
    
    public ObservableList<String> getAllTasks() throws ModelException
    {
         allTasks = FXCollections.observableArrayList();
        try
        {
            allTasks.addAll(bllManager.getAllTasks());
        } catch (bllException ex)
        {
            throw new ModelException(ex.getMessage());
        }
        return allTasks;
    }

}
