/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.be;

/**
 *
 * @author The Cowboys
 */
public class User
{
    private int id;

    /**
     * Constructor for User BE
     * @param id 
     */
    public User(int id) {
        this.id = id;
    }

    /**
     * gets Id
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
}
