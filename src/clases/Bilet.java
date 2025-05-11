package clases;

import java.util.ArrayList;

public class Bilet {
    private int biletID;
    private String data;
    private Zi zi;
    private CategorieBilet categorieBilet;
    private Rezervare[] rezervari;

public Bilet(int biletID, String data) {
    this.biletID = biletID;
    this.data = data;
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
public void afisare() {
    System.out.println("Bilet ID: " + biletID + " data: " + data);
}

}
