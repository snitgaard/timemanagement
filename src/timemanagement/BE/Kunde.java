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
    
    /**
     * getter for isDeleted
     * @return isDeleted
     */
    public int getIsDeleted()
    {
        return isDeleted.get();
    }
    
    /**
     * setter for isDeleted
     * @param isDeleted 
     */
    public void setIsDeleted(int isDeleted)
    {
        this.isDeleted.set(isDeleted);
    }
    
    /**
     * returns isDeleted as an observable Object
     * @return isDeleted
     */
    public ObservableValue<Integer> isDeletedObservable()
    {
        return isDeleted.asObject();
    }

    /**
     * getter for Id
     * @return id
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
    
    /**
     * return kundeNavn as StringProperty
     * @return kundeNavn
     */
    public StringProperty kundeNavnProperty()
    {
        return kundeNavn;
    }

    /**
     * getter for kontaktPerson
     * @return kontaktPerson
     */
    public String getKontaktPerson()
    {
        return kontaktPerson.get();
    }

    /**
     * setter for kontaktPerson
     * @param kontaktPerson 
     */
    public void setKontaktPerson(String kontaktPerson)
    {
        this.kontaktPerson.set(kontaktPerson);
    }
    
    /**
     * return kontaktPerson as StringProperty
     * @return kontaktPerson
     */
    public StringProperty kontaktPersonProperty()
    {
        return kontaktPerson;
    }

    /**
     * getter for email
     * @return email
     */
    public String getEmail()
    {
        return email.get();
    }

    /**
     * setter for email
     * @param email 
     */
    public void setEmail(String email)
    {
        this.email.set(email);
    }
    
    /**
     * return email as StringProperty
     * @return email
     */
    public StringProperty emailProperty()
    {
        return email;
    }

    /**
     * getter for hourlyRate
     * @return hourlyRate
     */
    public double getHourlyRate()
    {
        return hourlyRate.get();
    }

    /**
     * setter for hourlyRate
     * @param hourlyRate 
     */
    public void setHourlyRate(double hourlyRate)
    {
        this.hourlyRate.set(hourlyRate);
    }
    
    /**
     * return hourlyRate as an observable Object
     * @return hourlyRate
     */
    public ObservableValue<Double> hourlyRateObservable()
    {
        return hourlyRate.asObject();
    }

    /**
     * toString method for Kunde
     * @return kundenavn stringproperty
     */
    @Override
    public String toString()
    {
        return getKundeNavn();
    }

}
