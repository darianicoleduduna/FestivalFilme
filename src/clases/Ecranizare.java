package clases;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Ecranizare {
    private int ecranizareID;
    private String oraInceput;
    private String oraFinal;
    private int nrLocuriLibere;
    private int[][] locuri_libere;
    private ArrayList<Bilet>  bilete_cumparate;
    private ArrayList<Staff> supraveghetori;
    private ArrayList<Rezervare> rezervari;

    public Ecranizare( ) {

    }
    public int getEcranizareID() {
        return ecranizareID;
    }
    public void setEcranizareID(int ecranizareID) {
        this.ecranizareID = ecranizareID;
    }
    public String getOraInceput() {
        return oraInceput;
    }
    public void setOraInceput(String oraInceput) {
        this.oraInceput = oraInceput;
    }
    public String getOraFinal() {
        return oraFinal;
    }
    public void setOraFinal(String oraFinal) {
        this.oraFinal = oraFinal;
    }
    public int getNrLocuriLibere() {
        return nrLocuriLibere;
    }
    public void setNrLocuriLibere(int nrLocuriLibere) {
        this.nrLocuriLibere = nrLocuriLibere;

    }
}
