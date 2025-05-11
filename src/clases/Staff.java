package clases;

import java.util.ArrayList;

public class Staff extends Persoana{
    private int staffID;
    private int salariu;
    private FunctieStaff functie;
    private Zone zonaFestival;
    private String oraInceputLucru;
    private int nrOreLucru;
    private ArrayList<Ecranizare> ecranizari;

    public Staff(String nume, String prenume, String email, String telefon, int staffID, int salariu, FunctieStaff functie, String oraInceputLucru, int nrOreLucru, Zone zonaFestival) {
        super(nume, prenume, email, telefon);
        this.staffID = staffID;
        this.salariu = salariu;
        this.functie = functie;
        this.oraInceputLucru = oraInceputLucru;
        this.nrOreLucru = nrOreLucru;
        this.zonaFestival = zonaFestival;
        this.ecranizari = new ArrayList<>();
    }
    public boolean valideazaManager()
    {
        if (this.functie==FunctieStaff.Manager_Festival)
            return true;
        else
            return false;
    }
}
