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
public class Project
{

    private int id;
    private StringProperty projectName;
    private IntegerProperty clientId;
    private StringProperty clientName;
    private StringProperty startDate;
    private LongProperty usedTime;
    private IntegerProperty isDeleted;
    private DoubleProperty hourlyRate;
    private IntegerProperty rounded;

    /**
     * Constructor for Project
     *
     * @param id
     * @param projectNavn
     * @param clientId
     * @param startDate
     * @param usedTime
     * @param isDeleted
     * @param ClientName
     * @param hourlyRate
     * @param rounded
     */
    public Project(int id, String projectNavn, int clientId, String startDate, long usedTime, int isDeleted, String ClientName, double hourlyRate, int rounded)
    {
        this.id = id;
        this.projectName = new SimpleStringProperty(projectNavn);
        this.clientId = new SimpleIntegerProperty(clientId);
        this.startDate = new SimpleStringProperty(startDate);
        this.usedTime = new SimpleLongProperty(usedTime);
        this.isDeleted = new SimpleIntegerProperty(isDeleted);
        this.clientName = new SimpleStringProperty(ClientName);
        this.hourlyRate = new SimpleDoubleProperty(hourlyRate);
        this.rounded = new SimpleIntegerProperty(rounded);
    }

    /**
     * Constructor for Project
     *
     * @param id
     * @param projectName
     * @param clientName
     * @param usedTime
     * @param startDate
     * @param isDeleted
     * @param clientId
     * @param hourlyRate
     * @param rounded
     */
    public Project(int id, String projectName, String clientName, long usedTime, String startDate, int isDeleted, double hourlyRate, int rounded, int clientId)
    {
        this.id = id;
        this.projectName = new SimpleStringProperty(projectName);
        this.clientName = new SimpleStringProperty(clientName);
        this.usedTime = new SimpleLongProperty(usedTime);
        this.startDate = new SimpleStringProperty(startDate);
        this.isDeleted = new SimpleIntegerProperty(isDeleted);
        this.hourlyRate = new SimpleDoubleProperty(hourlyRate);
        this.rounded = new SimpleIntegerProperty(rounded);
        this.clientId = new SimpleIntegerProperty(clientId);
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
     * Setter for Id
     *
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Getter for projectName
     *
     * @return projectName
     */
    public String getProjectName()
    {
        return projectName.get();
    }

    /**
     * Setter for projectName
     *
     * @param projectName
     */
    public void setProjectName(String projectName)
    {
        this.projectName.set(projectName);
    }

    /**
     * returns projectName as StringProperty
     *
     * @return projectName
     */
    public StringProperty projectNameProperty()
    {
        return projectName;
    }

    /**
     * getter for clientId
     *
     * @return clientId
     */
    public int getClientId()
    {
        return clientId.get();
    }

    /**
     * Setter for clientId
     *
     * @param clientId
     */
    public void setClientId(int clientId)
    {
        this.clientId.set(clientId);
    }

    /**
     * returns clientId as an observable Object
     *
     * @return clientId
     */
    public ObservableValue<Integer> clientIdObservable()
    {
        return clientId.asObject();
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
     * returns clientName as StringProperty
     *
     * @return clientName
     */
    public StringProperty clientNameProperty()
    {
        return clientName;
    }

    /**
     * getter for startDate
     *
     * @return startDate
     */
    public String getStartDate()
    {
        return startDate.get();
    }

    /**
     * setter for startDate
     *
     * @param startDate
     */
    public void setStartDate(String startDate)
    {
        this.startDate.set(startDate);
    }

    /**
     * returns startDate as StringProperty
     *
     * @return startDate
     */
    public StringProperty startDateProperty()
    {
        return startDate;
    }

    /**
     * getter for usedTime
     *
     * @return usedTime
     */
    public long getUsedTime()
    {
        return usedTime.get();
    }

    /**
     * setter for usedTime
     *
     * @param usedTime
     */
    public void setUsedTime(long usedTime)
    {
        this.usedTime.set(usedTime);
    }

    /**
     * returns usedTime as an observable Object
     *
     * @return usedTime
     */
    public ObservableValue<Long> usedTimeObservable()
    {
        return usedTime.asObject();
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
     *
     * @return isDeleted
     */
    public ObservableValue<Integer> isDeletedObservable()
    {
        return isDeleted.asObject();
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
     * returns hourlyRate as an observable Object
     *
     * @return hourlyRate
     */
    public ObservableValue<Double> hourlyRateObservable()
    {
        return hourlyRate.asObject();
    }

    /**
     * getter for rounded
     * @return rounded
     */
    public int getRounded()
    {
        return rounded.get();
    }

    /**
     * setter for rounded
     * @param rounded 
     */
    public void setRounded(int rounded)
    {
        this.rounded.set(rounded);
    }

    /**
     * returns rounded as an observable Object
     *
     * @return rounded
     */
    public ObservableValue<Integer> roundedObservable()
    {
        return rounded.asObject();
    }

    /**
     * toString method for Project
     *
     * @return projectName
     */
    @Override
    public String toString()
    {
        return getProjectName();
    }

}
