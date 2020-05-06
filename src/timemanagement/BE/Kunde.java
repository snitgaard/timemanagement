/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BE;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author The Cowboys
 */
public class Kunde
{
    private int id;
    private StringProperty kundeNavn;

    /**
     * Constructor for Kunde
     * @param id
     * @param kundeNavn 
     */
    public Kunde(int id, String kundeNavn) {
        this.id = id;
        this.kundeNavn = new SimpleStringProperty(kundeNavn);
    }

    /**
     * Getter for id
     * @return Id
     */
    public int getId() {
        return id;
    }

    /**
     * setter for id
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
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
    
    public StringProperty kundeNavnProperty()
    {
        return kundeNavn;
    }
}
