package clases;

import java.util.ArrayList;

public class Bilet {
    private static int last_id=0;
    private int biletID;
    private Zi zi;
    private CategorieBilet categorieBilet;
    private Rezervare[] rezervari;
    private int indexr;

    public Bilet(String data, CategorieBilet categorieBilet) {
        this.biletID = ++this.last_id;
        rezervari = new Rezervare[50];
        indexr=0;
        Zi zi = new Zi();
        this.categorieBilet = categorieBilet;

    }


    public int getBiletID() {
        return biletID;
    }

    public void setBiletID(int biletID) {
        this.biletID = biletID;
    }

    public void adaugaRezervare(Rezervare rezervare) {
        rezervari[indexr] = rezervare;
        indexr++;
    }
    public void setZi(Zi zi) {
        this.zi = zi;
    }
    public void afisare() {
        System.out.println("Bilet ID: " + biletID + " data: " + zi.getData());
    }

    public CategorieBilet verifica_tip_bilet() {
        return categorieBilet;
    }

    public Zi getZi() {
        return zi;
    }

}
