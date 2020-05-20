/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BE;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;

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
    private IntegerProperty isDeleted;

    /**
     * Constructor for Kunde
     * @param id
     * @param kundeNavn 
     * @param kontaktPerson 
     * @param email 
     * @param hourlyRate 
     */
    public Kunde(int id, String kundeNavn, String kontaktPerson, String email, double hourlyRate, int isDeleted) {    
        this.id = id;
        this.kundeNavn = new SimpleStringProperty(kundeNavn);
        this.kontaktPerson = new SimpleStringProperty(kontaktPerson);
        this.email = new SimpleStringProperty(email);
        this.hourlyRate = new SimpleDoubleProperty(hourlyRate);
        this.isDeleted = new SimpleIntegerProperty(isDeleted);
    }
    
      public int getIsDeleted()
    {
        return isDeleted.get();
    }
    
    public void setIsDeleted(int isDeleted)
    {
        this.isDeleted.set(isDeleted);
    }
    
    public ObservableValue<Integer> isDeletedObservable()
    {
        return isDeleted.asObject();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getKundeNavn()
    {
        return kundeNavn.get();
    }

    public void setKundeNavn(String kundeNavn)
    {
        this.kundeNavn.set(kundeNavn);
    }
    
    public StringProperty kundeNavnProperty()
    {
        return kundeNavn;
    }

    public String getKontaktPerson()
    {
        return kontaktPerson.get();
    }

    public void setKontaktPerson(String kontaktPerson)
    {
        this.kontaktPerson.set(kontaktPerson);
    }
    
    public StringProperty kontaktPersonProperty()
    {
        return kontaktPerson;
    }

    public String getEmail()
    {
        return email.get();
    }

    public void setEmail(String email)
    {
        this.email.set(email);
    }
    
    public StringProperty emailProperty()
    {
        return email;
    }

    public double getHourlyRate()
    {
        return hourlyRate.get();
    }

    public void setHourlyRate(double hourlyRate)
    {
        this.hourlyRate.set(hourlyRate);
    }
    
    public ObservableValue<Double> hourlyRateObservable()
    {
        return hourlyRate.asObject();
    }

    @Override
    public String toString()
    {
        return getKundeNavn();
    }

}
