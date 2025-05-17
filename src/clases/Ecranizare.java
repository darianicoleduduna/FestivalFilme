package clases;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

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

    //constructori
    public Ecranizare(String oraInceput, String oraFinal) {
        this.ecranizareID = ++last_id;
        this.oraInceput = oraInceput;
        this.oraFinal = oraFinal;
        this.nrLocuriLibere = 0;
        this.nrRanduri = 0;
        this.nrColoane = 0;
        bilete_cumparate = new ArrayList<>();
        supraveghetori = new ArrayList<>();
        rezervari = new ArrayList<>();

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

    public void setData(String data) {
        this.data = data;
    }

    public void setNrLocuriLibere(int nrLocuriLibere) {
        this.nrLocuriLibere = nrLocuriLibere;
    }

    public void setNrRanduri(int nrRanduri) {
        this.nrRanduri = nrRanduri;
    }

    public void setNrColoane(int nrColoane) {
        this.nrColoane = nrColoane;
    }

    public int getNrColoane() {return nrColoane;}

    public void initializare_locuri_libere() {
        locuri_libere = new int[nrRanduri][nrColoane];
        for(int i=0; i<nrRanduri; i++)
            for(int j=0; j<nrColoane; j++)
                locuri_libere[i][j] = 0;
    }

    public void afisare_locuri() {
        int width = 3; // lățimea fixă pentru fiecare element

        // Afișare antet (coloane)
        System.out.print("   "); // spațiu pentru colțul din stânga sus
        for (int j = 0; j < nrColoane; j++) {
            System.out.printf("%" + width + "d", j + 1);
        }
        System.out.println();

        // Afișare rânduri
        for (int i = 0; i < nrRanduri; i++) {
            System.out.printf("%" + width + "d", i + 1); // indice rând
            for (int j = 0; j < nrColoane; j++) {
                System.out.printf("%" + width + "s", locuri_libere[i][j]);
            }
            System.out.println();
        }
    }


    public void rezervareLoc ( Bilet bilet) {

        int rloc,cloc;
        Scanner s = new Scanner(System.in);
        System.out.println("Alegeti locul 0-dispoonibil, 1- ocupat");
        afisare_locuri();
        System.out.println("Introduceti randul dorit");
        rloc=s.nextInt();
        System.out.println("Introduceti coloana dorita");
        cloc=s.nextInt();
        locuri_libere[rloc-1][cloc -1]=1;
        nrLocuriLibere--;
        bilete_cumparate.add(bilet);
        Rezervare r=new Rezervare(rloc-1,cloc-1);
        bilet.adaugaRezervare(r);
        rezervari.add(r);


    }

        public void elibereazaLoc ( int rand, int coloana){
            if (locuri_libere[rand][coloana] == 1) {
                locuri_libere[rand][coloana] = 0;
                nrLocuriLibere++;
            }
        }

        public void adaugaSupraveghetor (Staff supraveghetor){
            supraveghetori.add(supraveghetor);
        }
}
