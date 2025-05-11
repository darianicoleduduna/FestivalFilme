package clases;

import java.util.ArrayList;

public class Sala {
    private int salaId;
    private String nume;
    private int nrLocuri;
    private ArrayList<Ecranizare> program_ecranizari;

    public Sala(int salaId, String nume, int nrLocuri) {
        this.salaId = salaId;
        this.nume = nume;
        this.nrLocuri = nrLocuri;

    }
    public int getSalaId() {
        return salaId;
    }
    public void setSalaId(int salaId) {
        this.salaId = salaId;
    }
    public String getNume() {
        return nume;
    }
    public void setNume(String nume) {
        this.nume = nume;
    }
    public int getNrLocuri() {
        return nrLocuri;
    }
     public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
     }

}
