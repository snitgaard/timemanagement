/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.dal;

import java.util.List;
import timemanagement.be.User;

/**
 *
 * @author Mads
 */
public interface DalFacade {
    
    boolean checkUserCredentials(String userLogin, String userPassword) throws DalException;
    
    List<User> getUser(String userLogin) throws DalException;
    
    User getSpecificUser(String userLogin) throws DalException;
    
}
