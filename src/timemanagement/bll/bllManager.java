/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BLL;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import timemanagement.be.User;
import timemanagement.bll.bllException;
import timemanagement.bll.bllFacade;
import timemanagement.dal.DalException;
import timemanagement.dal.DalFacade;
import timemanagement.dal.DalManager;

/**
 *
 * @author The Cowboys
 */
public class bllManager implements bllFacade {
    
    private final DalFacade dalFacade;

    public bllManager() throws IOException
    {
        dalFacade = new DalManager();
    }
    
    @Override
    public boolean checkUserCredentials(String userLogin, String userPassword) throws bllException
    {
        try
        {
            return dalFacade.checkUserCredentials(userLogin, userPassword);
        } catch (DalException ex)
        {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public List<User> getUser(String userLogin) throws bllException {
        try {
            return dalFacade.getUser(userLogin);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }

    @Override
    public User getSpecificUser(String userLogin) throws bllException {
        try {
            return dalFacade.getSpecificUser(userLogin);
        } catch (DalException ex) {
            throw new bllException(ex.getMessage());
        }
    }
    
}
