/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.gui.model;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import timemanagement.BLL.bllManager;
import timemanagement.be.User;
import timemanagement.bll.bllException;

/**
 *
 * @author jigzi
 */
public class Model {
    
    private bllManager bllManager;

    public Model() throws IOException {
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
    
    public List<User> getUser (String userLogin) throws ModelException
    {
        try {
            return bllManager.getUser(userLogin);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }
    
    public User getSpecificUser (String userLogin) throws ModelException
    {
        try {
            return bllManager.getSpecificUser(userLogin);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }
     
}
