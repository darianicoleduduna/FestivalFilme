package clases;

import java.util.ArrayList;

public class Sala {
    private static int last_id=0;
    private int salaId;
    private String nume;
    private int nrLocuri;
    private int nrRanduri;
    private int nrColoane;
    private ArrayList<Ecranizare> program_ecranizari;

    public Sala(String nume, int nrRanduri, int nrColoane) {
        this.salaId = ++this.last_id;
        this.nume = nume;
        this.nrRanduri = nrRanduri;
        this.nrColoane = nrColoane;
        this.nrLocuri = nrRanduri*nrColoane;
        program_ecranizari = new ArrayList<Ecranizare>();
    }

    public int getSalaId() {
        return salaId;
    }

    public void setSalaId(int salaId) {
        this.salaId = salaId;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getNrLocuri() {
        return nrLocuri;
    }

    public void setNrLocuri(int nrLocuri) {
        this.nrLocuri = nrLocuri;
    }
    public void setNrRanduri(int nrRanduri) {   this.nrRanduri = nrRanduri; }
    public int getNrColoane() {
        return nrColoane;
    }
    public void setNrColoane(int nrColoane) {
        this.nrColoane = nrColoane;
    }

    // ora1 > ora2 -> 1;
    // ora1 < ora2 -> -1;
    // ora1 = ora2 -> 0
    public int comparareOre(String ora1, String ora2){
        String [] ora1_desp = ora1.split(":");
        String [] ora2_desp = ora2.split(":");
        if (ora1.equals(ora2))
            return 0;
        if (Integer.parseInt(ora1_desp[0]) > Integer.parseInt(ora2_desp[0]))
            return 1;
        else if (Integer.parseInt(ora1_desp[0]) < Integer.parseInt(ora2_desp[0]))
            return -1;
        else
        if (Integer.parseInt(ora1_desp[1]) > Integer.parseInt(ora2_desp[1]))
            return 1;
        else if (Integer.parseInt(ora1_desp[1]) < Integer.parseInt(ora2_desp[1]))
            return -1;
        else
            return 0;
    }


    public boolean alocareSala(Ecranizare ecranizare) {
        for(int i=0; i<program_ecranizari.size(); i++)
        {
            Ecranizare e = program_ecranizari.get(i);
            if (e.getData().equals(ecranizare.getData())) {
                String start1 = e.getOraInceput();
                String end1 = e.getOraFinal();
                String start2 = ecranizare.getOraInceput();
                String end2 = ecranizare.getOraFinal();

                boolean seSuprapun = !(comparareOre(end1, start2) <= 0 || comparareOre(end2, start1) <= 0);

                if (seSuprapun) {
                    System.out.println("Nu se poate aloca sala pentru aceasta ecranizare");
                    return seSuprapun;
                }

            }

        }
        ecranizare.setNrRanduri(nrRanduri);
        ecranizare.setNrColoane(nrColoane);
        ecranizare.setNrLocuriLibere(nrLocuri);
        ecranizare.initializare_locuri_libere();
        program_ecranizari.add(ecranizare);
        return false;
    }
     

}
