/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BE;

/**
 *
 * @author CSnit
 */
public class Task
{
    private int id;
    private String opgaveNavn;
    private String projektNavn;
    private int projektId;
    private int brugtTid;
    private String dato;
    private String beskrivelse;
    private int betalt;

    /**
     * Constructor for Task
     * @param id
     * @param opgaveNavn
     * @param projektId
     * @param brugtTid
     * @param dato 
     */
    public Task(int id, String opgaveNavn, int projektId, int brugtTid, String dato, String beskrivelse, int betalt)
    {
        this.id = id;
        this.opgaveNavn = opgaveNavn;
        this.projektId = projektId;
        this.brugtTid = brugtTid;
        this.dato = dato;
        this.beskrivelse = beskrivelse;
        this.betalt = betalt;
    }

    /**
     * Constructor for Task
     * @param opgaveNavn
     * @param projektNavn
     * @param brugtTid
     * @param dato 
     */
    public Task(String opgaveNavn, String projektNavn, int brugtTid, String dato)
    {
        this.opgaveNavn = opgaveNavn;
        this.projektNavn = projektNavn;
        this.brugtTid = brugtTid;
        this.dato = dato;
    }

    /**
     * getter for Id
     * @return Id
     */
    public int getId()
    {
        return id;
    }

    /**
     * setter for id
     * @param id 
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * getter for opgaveNavn
     * @return opgaveNavn
     */
    public String getOpgaveNavn()
    {
        return opgaveNavn;
    }

    /**
     * setter for opgaveNavn
     * @param opgaveNavn 
     */
    public void setOpgaveNavn(String opgaveNavn)
    {
        this.opgaveNavn = opgaveNavn;
    }

    /**
     * getter for projektNavn
     * @return projektNavn
     */
    public String getProjektNavn()
    {
        return projektNavn;
    }

    /**
     * setter for projektNavn
     * @param projektNavn 
     */
    public void setProjektNavn(String projektNavn)
    {
        this.projektNavn = projektNavn;
    }

    /**
     * getter for projektId
     * @return projektId
     */
    public int getProjektId()
    {
        return projektId;
    }

    /**
     * setter for projektId
     * @param projektId 
     */
    public void setProjektId(int projektId)
    {
        this.projektId = projektId;
    }


    /**
     * getter for brugtTid
     * @return brugtTid
     */
    public int getBrugtTid()
    {
        return brugtTid;
    }

    /**
     * setter for brugtTid
     * @param brugtTid 
     */
    public void setBrugtTid(int brugtTid)
    {
        this.brugtTid = brugtTid;
    }

    /**
     * getter for dato
     * @return dato
     */
    public String getDato()
    {
        return dato;
    }

    /**
     * setter for dato
     * @param dato 
     */
    public void setDato(String dato)
    {
        this.dato = dato;
    }

    /**
     * toString metode for Task
     * @return opgaveNavn
     */
    @Override
    public String toString()
    {
        return opgaveNavn;
    }

    /**
     * getter for beskrivelse
     * @return beskrivelse
     */
    public String getBeskrivelse() {
        return beskrivelse;
    }

    /**
     * setter for beskrivelse
     * @param beskrivelse 
     */
    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    /**
     * getter for betalt
     * @return betalt
     */
    public int getBetalt() {
        return betalt;
    }

    /**
     * setter for betalt
     * @param betalt 
     */
    public void setBetalt(int betalt) {
        this.betalt = betalt;
    }
    
    
    
    
}
