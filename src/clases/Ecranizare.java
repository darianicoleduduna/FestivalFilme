package clases;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Ecranizare {
    private static int last_id=0;
    private int ecranizareID;
    private String data;
    private String oraInceput;
    private String oraFinal;
    private int nrLocuriLibere;
    private int[][] locuri_libere;
    private int nrRanduri;
    private int nrColoane;
    private ArrayList<Bilet> bilete_cumparate;
    private ArrayList<Staff> supraveghetori;
    private ArrayList<Rezervare> rezervari;

    public Ecranizare(String oraInceput, String oraFinal, String data) {
        this.ecranizareID = ++last_id;
        this.oraInceput = oraInceput;
        this.oraFinal = oraFinal;
        this.data = data;
        this.nrLocuriLibere = 0;
        this.nrRanduri = 0;
        this.nrColoane = 0;
    }

    public int getEcranizareID() {
        return ecranizareID;
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

    public String getData() { return data; }

    public void setData(String data) { this.data = data; }

    public int getNrLocuriLibere() {
        return nrLocuriLibere;
    }

    public void setNrLocuriLibere(int nrLocuriLibere) {this.nrLocuriLibere = nrLocuriLibere;}

    public void setNrRanduri(int nrRanduri) {this.nrRanduri = nrRanduri;}

    public int getRanduri() {return nrRanduri;}

    public void setNrColoane(int nrColoane) {this.nrColoane = nrColoane;}

    public int getNrColoane() {return nrColoane;}

    public void rezervareLoc (int rand, int coloana){

    }

    public void adaugaEcranizare(Film f, Sala s)
    {
        f.adaugaEcranizare(this);
        s.alocareSala(this);
    }
}
