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


    public boolean anuleazaEcranizare(Ecranizare ecranizare) {
        if (!ecranizari.contains(ecranizare)) {
            System.out.println("Ecranizarea nu există în această zi.");
            return false;
        }
        ArrayList<Rezervare> rezervari = new ArrayList<>(ecranizare.getRezervari());

        for (Staff s : ecranizare.getSupraveghetori()) {
            s.getEcranizari().remove(ecranizare);
        }

        for (Rezervare rez : rezervari) {
            int r = rez.getRand();
            int c = rez.getColoana();


            ecranizare.elibereazaLoc(r, c);

            ecranizare.getRezervari().remove(rez);

            for (Bilet b : ecranizare.getBileteCumparate()) {
                Rezervare[] rezBilet = b.getRezervari();
                if (rezBilet != null && rezBilet.length > 0) {
                    ArrayList<Rezervare> filtrate = new ArrayList<>();
                    for (Rezervare rB : rezBilet) {
                        if (rB != null && !rB.equals(rez)) {
                            filtrate.add(rB);
                        }
                    }
                    b.setRezervari(filtrate.toArray(new Rezervare[0]));
                }
            }
        }

    public Ecranizare get_ecranizarebyindex(int index){
        return this.ecranizari.get(index);
    }
}