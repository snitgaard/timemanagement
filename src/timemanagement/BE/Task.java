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
 * @author The Cowboys
 */
public class Task
{
    private int id;
    private StringProperty taskName;
    private StringProperty projectName;
    private IntegerProperty projectId;
    private LongProperty usedTime;
    private StringProperty date;
    private StringProperty description;
    private IntegerProperty payed;
    private IntegerProperty isDeleted;
    private IntegerProperty userId;

    /**
     * Constructor for Task
     *
     * @param id
     * @param taskName
     * @param projectId
     * @param usedTime
     * @param date
     */
    public Task(int id, String taskName, int projectId, long usedTime, String date, String description, int payed, String projectName, int isDeleted, int userId)
    {
        this.id = id;
        this.taskName = new SimpleStringProperty(taskName);
        this.projectId = new SimpleIntegerProperty(projectId);
        this.usedTime = new SimpleLongProperty(usedTime);
        this.date = new SimpleStringProperty(date);
        this.description = new SimpleStringProperty(description);
        this.payed = new SimpleIntegerProperty(payed);
        this.projectName = new SimpleStringProperty(projectName);
        this.isDeleted = new SimpleIntegerProperty(isDeleted);
        this.userId = new SimpleIntegerProperty(userId);
    }

    /**
     * Constructor for Task
     *
     * @param id
     * @param taskName
     * @param projectName
     * @param usedTime
     * @param date
     */
    public Task(int id, String taskName, String projectName, long usedTime, String date, int projektId, int userId, String beskrivelse, int betalt)
    {
        this.id = id;
        this.taskName = new SimpleStringProperty(taskName);
        this.projectName = new SimpleStringProperty(projectName);
        this.usedTime = new SimpleLongProperty(usedTime);
        this.date = new SimpleStringProperty(date);
        this.projectId = new SimpleIntegerProperty(projektId);
        this.userId = new SimpleIntegerProperty(userId);
        this.description = new SimpleStringProperty(beskrivelse);
        this.payed = new SimpleIntegerProperty(betalt);
    }

    /**
     * getter for Id
     *
     * @return Id
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
     * getter for opgaveNavn
     *
     * @return opgaveNavn
     */
    public String getTaskName()
    {
        return taskName.get();
    }

    /**
     * setter for opgaveNavn
     *
     * @param taskName
     */
    public void setTaskName(String taskName)
    {
        this.taskName.set(taskName);
    }

    public StringProperty taskNameProperty()
    {
        return taskName;
    }

    /**
     * getter for projectName
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

    public StringProperty projectNameProperty()
    {
        return projectName;
    }

    /**
     * getter for projektId
     *
     * @return projektId
     */
    public int getProjectId()
    {
        return projectId.get();
    }

    /**
     * setter for projectId
     *
     * @param projectId
     */
    public void setProjectId(int projectId)
    {
        this.projectId.set(projectId);
    }

    public ObservableValue<Integer> projectIdObservable()
    {
        return projectId.asObject();
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
     * setter for brugtTid
     *
     * @param usedTime
     */
    public void setUsedTime(long usedTime)
    {
        this.usedTime.set(usedTime);
    }

    public ObservableValue<Long> usedTimeObservableValue()
    {
        return usedTime.asObject();
    }

    /**
     * getter for date
     *
     * @return date
     */
    public String getDate()
    {
        return date.get();
    }

    /**
     * setter for date
     *
     * @param date
     */
    public void setDato(String date)
    {
        this.date.set(date);
    }

    public StringProperty dateProperty()
    {
        return date;
    }

    /**
     * toString metode for Task
     *
     * @return taskName
     */
    @Override
    public String toString()
    {
        return getTaskName();
    }

    /**
     * getter for description
     *
     * @return description
     */
    public String getDescription()
    {
        return description.get();
    }

    /**
     * setter for description
     *
     * @param description
     */
    public void setDescription(String description)
    {
        this.description.set(description);
    }

    public StringProperty descriptionProperty()
    {
        return description;
    }

    /**
     * getter for payed
     *
     * @return payed
     */
    public int getPayed()
    {
        return payed.get();
    }

    /**
     * setter for betalt
     *
     * @param payed
     */
    public void setPayed(int payed)
    {
        this.payed.set(payed);
    }

    public ObservableValue<Integer> payedObservable()
    {
        return payed.asObject();
    }

    public int getsDeleted()
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

    public int getUserId()
    {
        return userId.get();
    }

    public void setUserId(int userId)
    {
        this.userId.set(userId);
    }

    public ObservableValue<Integer> userIdObservableValue()
    {
        return userId.asObject();
    }


}
