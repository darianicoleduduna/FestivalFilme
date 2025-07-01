package classes;

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

    //getters
    public String getData() {
        return data;
    }
    public int getNrEcranizari() {
        return ecranizari.size();
    }
    public ArrayList<Ecranizare> getEcranizari() {
            return ecranizari;
        }
    public Ecranizare get_ecranizarebyindex(int index){
            return this.ecranizari.get(index);
        }

    //functii speciala
    public String tostring() {
        return this.data;
    }
    public boolean poateViziona(Film film,RestrictiiFilme permisiuneFilme) {

        return permisiuneFilme.ordinal() >= film.getRestrictie().ordinal();
    }
    public void adauga_ecranizare(Ecranizare ecranizare) {
        ecranizari.add(ecranizare);
    }
    public void afiseaza_ecranizari_pe_zi(ArrayList<Film> filme, RestrictiiFilme perm) {
            for (int i = 0; i < this.ecranizari.size(); i++) {
                for (int j = 0; j < filme.size(); j++) {
                    if (filme.get(j).exista_ecranizare(ecranizari.get(i).getEcranizareID()))
                    { if(poateViziona(filme.get(j),perm))
                            System.out.println((i + 1) + " :" + ecranizari.get(i).tostring_ecranizare()+ "  " + filme.get(j).tostring_film());
                      break;
                    }
                }
            }
        }
    public boolean exista_ecranizare(int e_id) {
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

        ecranizare.getBileteCumparate().clear();

        ecranizari.remove(ecranizare);

        System.out.println("Ecranizarea a fost anulată. Toate rezervările și locurile ocupate au fost eliberate.");
        return true;
    }






}