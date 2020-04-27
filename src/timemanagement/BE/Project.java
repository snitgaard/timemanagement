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
    private String Kunde;
    private String startDato;
    private int brugtTid;

    /**
     * Constructor for Project
     * @param id
     * @param projektNavn
     * @param Kunde
     * @param startDato
     * @param brugtTid 
     */
    public Project(int id, String projektNavn, String Kunde, String startDato, int brugtTid) {
        this.id = id;
        this.projektNavn = projektNavn;
        this.Kunde = Kunde;
        this.startDato = startDato;
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
     * getter for Kunde
     * @return Kunde
     */
    public String getKunde() {
        return Kunde;
    }

    /**
     * Setter for Kunde
     * @param id
     */
    public void setKunde(String Kunde) {
        this.Kunde = Kunde;
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
