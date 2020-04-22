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
public class Project {

    private int id;

    /**
     * Constructor for Project BE
     *
     * @param id
     */
    public Project(int id) {
        this.id = id;
    }

    /**
     * gets Id
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets Id
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

}
