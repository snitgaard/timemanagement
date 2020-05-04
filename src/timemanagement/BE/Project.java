/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author The Cowboys
 */
public class Project {

    private int id;
    private String projektNavn;
    private int kundeId;
    private String kundeNavn;
    private String startDato;
    private int brugtTid;
    private int ongoing;
    private IntegerProperty brugtTidMinutter;

    /**
     * Constructor for Project
     * @param id
     * @param projektNavn
     * @param kundeId
     * @param startDato
     * @param brugtTid 
     */
    public Project(int id, String projektNavn, int kundeId, String startDato, int brugtTid, int ongoing, int brugtTidMinutter) {
        this.id = id;
        this.projektNavn = projektNavn;
        this.kundeId = kundeId;
        this.startDato = startDato;
        this.brugtTid = brugtTid;
        this.ongoing = ongoing;
        this.brugtTidMinutter = new SimpleIntegerProperty(brugtTidMinutter);
    }

    

    /**
     * Constructor for Project
     * @param projektNavn
     * @param kundeNavn
     * @param brugtTid 
     */
    public Project(String projektNavn, String kundeNavn, int brugtTid, int ongoing)
    {
        this.projektNavn = projektNavn;
        this.kundeNavn = kundeNavn;
        this.brugtTid = brugtTid;
        this.ongoing = ongoing;
    }
    
    /**
     * getter for Id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for Id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for projektNavn
     * @return projektNavn
     */
    public String getProjektNavn() {
        return projektNavn;
    }

    /**
     * Setter for projektNavn
     * @param projektNavn 
     */
    public void setProjektNavn(String projektNavn) {
        this.projektNavn = projektNavn;
    }
    
    /**
     * getter for kundeId
     * @return kundeId
     */
    public int getKundeId() {
        return kundeId;
    }

    /**
     * Setter for kundeId
     * @param kundeId
     */
    public void setKundeId(int kundeId) {
        this.kundeId = kundeId;
    }

    /**
     * getter for kundeNavn
     * @return kundeNavn
     */
    public String getKundeNavn()
    {
        return kundeNavn;
    }

    /**
     * setter for kundeNavn
     * @param kundeNavn 
     */
    public void setKundeNavn(String kundeNavn)
    {
        this.kundeNavn = kundeNavn;
    }

    
    /**
     * getter for startDato
     * @return startDato
     */
    public String getStartDato() {
        return startDato;
    }

    /**
     * setter for startDato
     * @param startDato 
     */
    public void setStartDato(String startDato) {
        this.startDato = startDato;
    }

    /**
     * getter for brugtTid
     * @return brugtTid
     */
    public int getBrugtTid() {
        return brugtTid;
    }

    /**
     * setter for brugtTid
     * @param brugtTid 
     */
    public void setBrugtTid(int brugtTid) {
        this.brugtTid = brugtTid;
    }
    
    public int getOngoing() {
        return ongoing;
    }

    public void setOngoing(int ongoing) {
        this.ongoing = ongoing;
    }

    public int getBrugtTidMinutter() {
        return brugtTidMinutter.get();
    }
    
    /**
     * getter for brugtTidMinutter
     * @return brugtTidMinutter
     */
    public ObservableValue<Integer> brugtTidMinutter() {
        return brugtTidMinutter.asObject();
    }

    /**
     * setter for brugtTidMinutter
     * @param brugtTidMinutter 
     */
    public void setBrugtTidMinutter(int brugtTidMinutter) {
        this.brugtTidMinutter.set(brugtTidMinutter);
    }

    /**
     * toString method for Project
     * @return projektNavn
     */
    @Override
    public String toString() {
        return projektNavn;
    }
    
    

}
