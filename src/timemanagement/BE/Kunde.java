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
public class Kunde
{
    private String kundeNavn;

    public Kunde(String kundeNavn)
    {
        this.kundeNavn = kundeNavn;
    }

    public String getKundeNavn()
    {
        return kundeNavn;
    }

    public void setKundeNavn(String kundeNavn)
    {
        this.kundeNavn = kundeNavn;
    }

}
