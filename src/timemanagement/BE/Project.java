/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author The Cowboys
 */
public class Project {

    private int id;
    private StringProperty projektNavn;
    private IntegerProperty kundeId;
    private StringProperty kundeNavn;
    private StringProperty startDato;
    private LongProperty brugtTid;
    private IntegerProperty ongoing;
    private LongProperty brugtTidMinutter;

    /**
     * Constructor for Project
     * @param id
     * @param projektNavn
     * @param kundeId
     * @param startDato
     * @param brugtTid 
     */
    public Project(int id, String projektNavn, int kundeId, String startDato, long brugtTid, int ongoing, long brugtTidMinutter, String kundeNavn) {
        this.id = id;
        this.projektNavn = new SimpleStringProperty(projektNavn);
        this.kundeId = new SimpleIntegerProperty(kundeId);
        this.startDato = new SimpleStringProperty(startDato);
        this.brugtTid = new SimpleLongProperty(brugtTid);
        this.ongoing = new SimpleIntegerProperty(ongoing);
        this.brugtTidMinutter = new SimpleLongProperty(brugtTidMinutter);
        this.kundeNavn = new SimpleStringProperty(kundeNavn);
    }

    

    /**
     * Constructor for Project
     * @param projektNavn
     * @param kundeNavn
     * @param brugtTid 
     */
    public Project(int id, String projektNavn, String kundeNavn, long brugtTid, String startDato, int ongoing, long brugtTidMinutter)
    {
        this.id = id;
        this.projektNavn = new SimpleStringProperty(projektNavn);
        this.kundeNavn = new SimpleStringProperty(kundeNavn);
        this.brugtTid = new SimpleLongProperty(brugtTid);
        this.startDato = new SimpleStringProperty(startDato);
        this.ongoing = new SimpleIntegerProperty(ongoing);
        this.brugtTidMinutter = new SimpleLongProperty(brugtTidMinutter);
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
     * getter for kundeId
     * @return kundeId
     */
    public int getKundeId() {
        return kundeId.get();
    }

    /**
     * Setter for kundeId
     * @param kundeId
     */
    public void setKundeId(int kundeId) {
        this.kundeId.set(kundeId);
    }
    
    public ObservableValue<Integer> kundeIdObservable()
    {
        return kundeId.asObject();
    }

    /**
     * getter for kundeNavn
     * @return kundeNavn
     */
    public String getKundeNavn()
    {
        return kundeNavn.get();
    }

    /**
     * setter for kundeNavn
     * @param kundeNavn 
     */
    public void setKundeNavn(String kundeNavn)
    {
        this.kundeNavn.set(kundeNavn);
    }
    
    public StringProperty kundeNavnProperty() {
        return kundeNavn;
    }

    
    /**
     * getter for startDato
     * @return startDato
     */
    public String getStartDato() {
        return startDato.get();
    }

    /**
     * setter for startDato
     * @param startDato 
     */
    public void setStartDato(String startDato) {
        this.startDato.set(startDato);
    }
    
    public StringProperty startDatoProperty() {
        return startDato;
    }

    /**
     * getter for brugtTid
     * @return brugtTid
     */
    public long getBrugtTid() {
        return brugtTid.get();
    }

    /**
     * setter for brugtTid
     * @param brugtTid 
     */
    public void setBrugtTid(long brugtTid) {
        this.brugtTid.set(brugtTid);
    }
    
    public ObservableValue<Long> brugtTidObservable()
    {
        return brugtTid.asObject();
    }
    
    public int getOngoing() {
        return ongoing.get();
    }

    public void setOngoing(int ongoing) {
        this.ongoing.set(ongoing);
    }
    
    public ObservableValue<Integer> ongoingObservable()
    {
        return ongoing.asObject();
    }

    public long getBrugtTidMinutter() {
        return brugtTidMinutter.get();
    }
    
    /**
     * getter for brugtTidMinutter
     * @return brugtTidMinutter
     */
    public ObservableValue<Long> brugtTidMinutter() {
        return brugtTidMinutter.asObject();
    }

    /**
     * setter for brugtTidMinutter
     * @param brugtTidMinutter 
     */
    public void setBrugtTidMinutter(long brugtTidMinutter) {
        this.brugtTidMinutter.set(brugtTidMinutter);
    }

    /**
     * toString method for Project
     * @return projektNavn
     */
    @Override
    public String toString() {
        return getProjektNavn();
    }
    
}
