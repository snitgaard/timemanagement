/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author The Cowboys
 */
public class Task
{
    private int id;
    private StringProperty opgaveNavn;
    private StringProperty projektNavn;
    private IntegerProperty projektId;
    private IntegerProperty brugtTid;
    private StringProperty dato;
    private StringProperty beskrivelse;
    private IntegerProperty betalt;

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
        this.opgaveNavn = new SimpleStringProperty(opgaveNavn);
        this.projektId = new SimpleIntegerProperty(projektId);
        this.brugtTid = new SimpleIntegerProperty(brugtTid);
        this.dato = new SimpleStringProperty(dato);
        this.beskrivelse = new SimpleStringProperty(beskrivelse);
        this.betalt = new SimpleIntegerProperty(betalt);
    }

    /**
     * Constructor for Task
     * @param id
     * @param opgaveNavn
     * @param projektNavn
     * @param brugtTid
     * @param dato 
     */
    public Task(int id, String opgaveNavn, String projektNavn,  int brugtTid, String dato, int projektId)
    {
        this.id = id;
        this.opgaveNavn = new SimpleStringProperty(opgaveNavn);
        this.projektNavn = new SimpleStringProperty(projektNavn);
        this.brugtTid = new SimpleIntegerProperty(brugtTid);
        this.dato = new SimpleStringProperty(dato);
        this.projektId = new SimpleIntegerProperty(projektId);
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
        return opgaveNavn.get();
    }

    /**
     * setter for opgaveNavn
     * @param opgaveNavn 
     */
    public void setOpgaveNavn(String opgaveNavn)
    {
        this.opgaveNavn.set(opgaveNavn);
    }
    
    public StringProperty opgaveNavnProperty()
    {
        return opgaveNavn;
    }

    /**
     * getter for projektNavn
     * @return projektNavn
     */
    
    public String getProjektNavn() {
        return projektNavn.get();
    }

    /**
     * Setter for projektNavn
     * @param projektNavn 
     */
    public void setProjektNavn(String projektNavn) {
        this.projektNavn.set(projektNavn);
    }
    
    public StringProperty projektNavnProperty() {
        return projektNavn;
    }

    /**
     * getter for projektId
     * @return projektId
     */
    public int getProjektId()
    {
        return projektId.get();
    }

    /**
     * setter for projektId
     * @param projektId 
     */
    public void setProjektId(int projektId)
    {
        this.projektId.set(projektId);
    }
    
    public ObservableValue<Integer> projektIdObservable()
    {
        return projektId.asObject();
    }


    /**
     * getter for brugtTid
     * @return brugtTid
     */
    public int getBrugtTid()
    {
        return brugtTid.get();
    }

    /**
     * setter for brugtTid
     * @param brugtTid 
     */
    public void setBrugtTid(int brugtTid)
    {
        this.brugtTid.set(brugtTid);
    }
    
    public ObservableValue<Integer> brugtTidObservableValue()
    {
        return brugtTid.asObject();
    }

    /**
     * getter for dato
     * @return dato
     */
    public String getDato()
    {
        return dato.get();
    }

    /**
     * setter for dato
     * @param dato 
     */
    public void setDato(String dato)
    {
        this.dato.set(dato);
    }
    
    public StringProperty datoProperty()
    {
        return dato;
    }

    /**
     * toString metode for Task
     * @return opgaveNavn
     */
    @Override
    public String toString()
    {
        return getOpgaveNavn();
    }

    /**
     * getter for beskrivelse
     * @return beskrivelse
     */
    public String getBeskrivelse() {
        return beskrivelse.get();
    }

    /**
     * setter for beskrivelse
     * @param beskrivelse 
     */
    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse.set(beskrivelse);
    }
    
    public StringProperty beskrivelseProperty()
    {
        return beskrivelse;
    }

    /**
     * getter for betalt
     * @return betalt
     */
    public int getBetalt() {
        return betalt.get();
    }

    /**
     * setter for betalt
     * @param betalt 
     */
    public void setBetalt(int betalt) {
        this.betalt.set(betalt);
    }
    
    public ObservableValue<Integer> betaltObservable()
    {
        return betalt.asObject();
    }
    
    
    
    
}
