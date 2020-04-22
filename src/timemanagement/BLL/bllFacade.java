/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BLL;

import java.util.List;
import timemanagement.BE.User;
import timemanagement.DAL.DalException;


/**
 *
 * @author Mads
 */
public interface bllFacade {
    
    boolean checkUserCredentials(String userLogin, String userPassword) throws bllException;
    
    List<User> getUser(String userLogin) throws bllException;
    
    User getSpecificUser(String userLogin) throws bllException;
    
}
