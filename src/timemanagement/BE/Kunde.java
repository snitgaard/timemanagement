/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BE;

import javafx.beans.property.DoubleProperty;
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
    private StringProperty kontaktPerson;
    private StringProperty email;
    private DoubleProperty hourlyRate;

    /**
     * Constructor for Kunde
     * @param id
     * @param kundeNavn 
     */
    public Kunde(int id, StringProperty kundeNavn, StringProperty kontaktPerson, StringProperty email, DoubleProperty hourlyRate) {    
        this.id = id;
        this.kundeNavn = kundeNavn;
        this.kontaktPerson = kontaktPerson;
        this.email = email;
        this.hourlyRate = hourlyRate;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public StringProperty getKundeNavn()
    {
        return kundeNavn;
    }

    public void setKundeNavn(StringProperty kundeNavn)
    {
        this.kundeNavn = kundeNavn;
    }

    public StringProperty getKontaktPerson()
    {
        return kontaktPerson;
    }

    public void setKontaktPerson(StringProperty kontaktPerson)
    {
        this.kontaktPerson = kontaktPerson;
    }

    public StringProperty getEmail()
    {
        return email;
    }

    public void setEmail(StringProperty email)
    {
        this.email = email;
    }

    public DoubleProperty getHourlyRate()
    {
        return hourlyRate;
    }

    public void setHourlyRate(DoubleProperty hourlyRate)
    {
        this.hourlyRate = hourlyRate;
    }
    
    
    
    
}
