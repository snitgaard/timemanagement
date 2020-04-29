/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BE;

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

    /**
     * Constructor for Project
     * @param id
     * @param projektNavn
     * @param kundeId
     * @param startDato
     * @param brugtTid 
     */
    public Project(int id, String projektNavn, int kundeId, String startDato, int brugtTid) {
        this.id = id;
        this.projektNavn = projektNavn;
        this.kundeId = kundeId;
        this.startDato = startDato;
        this.brugtTid = brugtTid;
    }

    public Project(String projektNavn, String kundeNavn, int brugtTid)
    {
        this.projektNavn = projektNavn;
        this.kundeNavn = kundeNavn;
        this.brugtTid = brugtTid;
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

    public String getKundeNavn()
    {
        return kundeNavn;
    }

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

    /**
     * toString method for Project
     * @return projektNavn
     */
    @Override
    public String toString() {
        return projektNavn;
    }
    
    

}
