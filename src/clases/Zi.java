package clases;

import java.util.ArrayList;

public class Zi {
    private String data;
    private ArrayList<Ecranizare> ecranizari;

    public Zi() {
        data = "";
        ecranizari = new ArrayList<>();

    }

    public Zi(String data) {
        this.data = data;
        ecranizari = new ArrayList<>();

    }

    public String getData() {
        return data;
    }

}
