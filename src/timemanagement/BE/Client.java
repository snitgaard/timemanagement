/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timemanagement.BE;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;

/**
 * @author The Cowboys
 */
public class Client
{
    private int id;
    private StringProperty clientName;
    private StringProperty contactPerson;
    private StringProperty email;
    private DoubleProperty hourlyRate;
    private IntegerProperty isDeleted;

    /**
     * Constructor for Kunde
     *
     * @param id
     * @param clientName
     * @param contactPerson
     * @param email
     * @param hourlyRate
     */
    public Client(int id, String clientName, String contactPerson, String email, double hourlyRate, int isDeleted)
    {
        this.id = id;
        this.clientName = new SimpleStringProperty(clientName);
        this.contactPerson = new SimpleStringProperty(contactPerson);
        this.email = new SimpleStringProperty(email);
        this.hourlyRate = new SimpleDoubleProperty(hourlyRate);
        this.isDeleted = new SimpleIntegerProperty(isDeleted);
    }

    /**
     * getter for isDeleted
     *
     * @return isDeleted
     */
    public int getIsDeleted()
    {
        return isDeleted.get();
    }

    /**
     * setter for isDeleted
     *
     * @param isDeleted
     */
    public void setIsDeleted(int isDeleted)
    {
        this.isDeleted.set(isDeleted);
    }

    /**
     * returns isDeleted as an observable Object
     *
     * @return isDeleted
     */
    public ObservableValue<Integer> isDeletedObservable()
    {
        return isDeleted.asObject();
    }

    /**
     * getter for Id
     *
     * @return id
     */
    public int getId()
    {
        return id;
    }

    /**
     * setter for id
     *
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * getter for clientName
     *
     * @return clientName
     */
    public String getClientName()
    {
        return clientName.get();
    }

    /**
     * setter for clientName
     *
     * @param clientName
     */
    public void setClientName(String clientName)
    {
        this.clientName.set(clientName);
    }

    /**
     * return clientName as StringProperty
     *
     * @return clientName
     */
    public StringProperty clientNameProperty()
    {
        return clientName;
    }

    /**
     * getter for contactPerson
     *
     * @return contactPerson
     */
    public String getContactPerson()
    {
        return contactPerson.get();
    }

    /**
     * setter for contactPerson
     *
     * @param contactPerson
     */
    public void setContactPerson(String contactPerson)
    {
        this.contactPerson.set(contactPerson);
    }

    /**
     * return contactPerson as StringProperty
     *
     * @return contactPerson
     */
    public StringProperty contactPersonProperty()
    {
        return contactPerson;
    }

    /**
     * getter for email
     *
     * @return email
     */
    public String getEmail()
    {
        return email.get();
    }

    /**
     * setter for email
     *
     * @param email
     */
    public void setEmail(String email)
    {
        this.email.set(email);
    }

    /**
     * return email as StringProperty
     *
     * @return email
     */
    public StringProperty emailProperty()
    {
        return email;
    }

    /**
     * getter for hourlyRate
     *
     * @return hourlyRate
     */
    public double getHourlyRate()
    {
        return hourlyRate.get();
    }

    /**
     * setter for hourlyRate
     *
     * @param hourlyRate
     */
    public void setHourlyRate(double hourlyRate)
    {
        this.hourlyRate.set(hourlyRate);
    }

    /**
     * return hourlyRate as an observable Object
     *
     * @return hourlyRate
     */
    public ObservableValue<Double> hourlyRateObservable()
    {
        return hourlyRate.asObject();
    }

    /**
     * toString method for Client
     *
     * @return kundenavn stringproperty
     */
    @Override
    public String toString()
    {
        return getClientName();
    }

}
