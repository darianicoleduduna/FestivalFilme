package clases;

import java.util.ArrayList;

public class Bilet {
    private static int last_id=0;
    private int biletID;
    private String data;
    private Zi zi;
    private CategorieBilet categorieBilet;
    private Rezervare[] rezervari;
    private int indexr;

    public Bilet(String data) {
        this.biletID = ++this.last_id;
        this.data = data;
        rezervari = new Rezervare[50];
        indexr=0;

    }


    public int getBiletID() {
        return biletID;
    }

    public void setBiletID(int biletID) {
        this.biletID = biletID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void adaugaRezervare(Rezervare rezervare) {
        rezervari[indexr] = rezervare;
        indexr++;
    }

    public void afisare() {
        System.out.println("Bilet ID: " + biletID + " data: " + data);
    }

}
