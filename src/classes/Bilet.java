package classes;

public class Bilet {
    private static int last_id=0;
    private int biletID;
    private int indexr;
    private CategorieBilet categorieBilet;

    private Zi zi;
    private Rezervare[] rezervari;

    public Bilet(CategorieBilet categorieBilet) {
        this.biletID = ++this.last_id;
        rezervari = new Rezervare[50];
        indexr=0;
        Zi zi = new Zi();
        this.categorieBilet = categorieBilet;

    }

    //getters
    public int getBiletID() {
        return biletID;
    }
    public Zi getZi() {
        return zi;
    }
    public Rezervare[] getRezervari() {
        return rezervari;
    }

    //setters
    public void setBiletID(int biletID) {
        this.biletID = biletID;
    }
    public void setZi(Zi zi) {
        this.zi = zi;
    }
    public void setRezervari(Rezervare[] rezervari) {
        this.rezervari = rezervari;
    }

    //functii speciale
    public void adaugaRezervare(Rezervare rezervare) {
        rezervari[indexr] = rezervare;
        indexr++;
    }

    public CategorieBilet verifica_tip_bilet() {
        return categorieBilet;
    }
    public int nrRezervari()
    {
        int counter=0;
        for (Rezervare rezervare : rezervari)
            if (rezervare != null) counter++;
        return counter;
    }



}
