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

    public Task(int id, String opgaveNavn, int projektId, int brugtTid, String dato)
    {
        this.id = id;
        this.opgaveNavn = opgaveNavn;
        this.projektId = projektId;
        this.brugtTid = brugtTid;
        this.dato = dato;
    }

    public Task(String opgaveNavn, String projektNavn, int brugtTid, String dato)
    {
        this.opgaveNavn = opgaveNavn;
        this.projektNavn = projektNavn;
        this.brugtTid = brugtTid;
        this.dato = dato;
    }
    
    

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getOpgaveNavn()
    {
        return opgaveNavn;
    }

    public void setOpgaveNavn(String opgaveNavn)
    {
        this.opgaveNavn = opgaveNavn;
    }

    public String getProjektNavn()
    {
        return projektNavn;
    }

    public void setProjektNavn(String projektNavn)
    {
        this.projektNavn = projektNavn;
    }

    public int getProjektId()
    {
        return projektId;
    }

    public void setProjektId(int projektId)
    {
        this.projektId = projektId;
    }


    public int getBrugtTid()
    {
        return brugtTid;
    }

    public void setBrugtTid(int brugtTid)
    {
        this.brugtTid = brugtTid;
    }

    public String getDato()
    {
        return dato;
    }

    public void setDato(String dato)
    {
        this.dato = dato;
    }

    @Override
    public String toString()
    {
        return opgaveNavn;
    }
}
