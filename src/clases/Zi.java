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

    public void adauga_ecranizare(Ecranizare ecranizare) {
        ecranizari.add(ecranizare);
    }

    boolean exista_ecranizare(int e_id){
        for(int u=0;u<ecranizari.size();u++){
            if(ecranizari.get(u).getEcranizareID() == e_id)
                return true;

        }
        return false;
    }

}
