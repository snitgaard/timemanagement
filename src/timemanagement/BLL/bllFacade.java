/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BLL;

import java.util.List;
import timemanagement.BE.Admin;
import timemanagement.BE.Task;
import timemanagement.BE.User;

/**
 *
 * @author Mads
 */
public interface bllFacade
{

    boolean checkUserCredentials(String userLogin, String userPassword) throws bllException;

    List<User> getUser(String userLogin) throws bllException;

    User getSpecificUser(String userLogin) throws bllException;

    public List<String> getAllProjects() throws bllException;

    boolean checkAdminCredentials(String adminLogin, String adminPassword) throws bllException;

    List<Admin> getAdmin(String adminLogin) throws bllException;

    Admin getSpecificAdmin(String adminLogin) throws bllException;

    List<String> getAllTasks() throws bllException;

    void deleteTask(Task task) throws bllException;

    boolean createTask() throws bllException;

}