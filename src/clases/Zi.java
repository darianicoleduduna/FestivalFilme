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

    public String tostring() {
        return data;
    }

    public boolean poateViziona(Film film,RestrictiiFilme permisiuneFilme) {

        return permisiuneFilme.ordinal() >= film.getRestrictie().ordinal();
    }
    public int getNrEcranizari() {
        return ecranizari.size();
    }

    public void adauga_ecranizare(Ecranizare ecranizare) {
        ecranizari.add(ecranizare);
    }

    boolean exista_ecranizare(int e_id) {
        for (int u = 0; u < ecranizari.size(); u++) {
            if (ecranizari.get(u).getEcranizareID() == e_id)
                return true;

        }
        return false;
    }

    public void afiseaza_ecranizari_pe_zi(ArrayList<Film> filme, RestrictiiFilme perm) {
        for (int i = 0; i < this.ecranizari.size(); i++) {
            for (int j = 0; j < filme.size(); j++) {
                if (filme.get(j).exista_ecranizare(ecranizari.get(i).getEcranizareID()))
                { if(poateViziona(filme.get(j),perm))
                        System.out.println((i + 1) + " :" + ecranizari.get(i).tostring_ecranizare() + filme.get(j).tostring_film());
                  break;
                }
            }
        }
    }
    public Ecranizare get_ecranizarebyindex(int index){
        return this.ecranizari.get(index);
    }
}