package clases;

import java.util.ArrayList;

public class Film {
    private static int last_id = 0;
    private int filmID;
    private String nume;
    private String an;
    private String regizor;
    private int durata;
    private CategoriiFilme categorie;
    private RestrictiiFilme restrictie;

    //relatii
    private ArrayList<Ecranizare> ecranizari_programate;

    public Film(String nume, String an, String regizor, int durata, RestrictiiFilme restrictie, CategoriiFilme categorie) {
        this.filmID = ++this.last_id;
        this.nume = nume;
        this.an = an;
        this.regizor = regizor;
        this.durata = durata;
        this.categorie = categorie;
        this.restrictie = restrictie;
        this.ecranizari_programate = new ArrayList<>();

    }

    //getters
    public int getFilmID() {
        return filmID;

    }
    public String getNume() {

        return nume;
    }
    public String getAn() {

        return an;
    }
    public String getRegizor() {

        return regizor;
    }
    public int getDurata() {

        return durata;
    }
    public CategoriiFilme getCategorie() {
        return categorie;

    }
    public RestrictiiFilme getRestrictie() {
        return restrictie;

    }
    public Ecranizare get_ecranizarebyindex(int index){
        return this.ecranizari_programate.get(index);
    }

    //setters
    public void setFilmID(int filmID) {
        this.filmID = filmID;

    }
    public void setNume(String nume) {

        this.nume = nume;
    }
    public void setAn(String an) {

        this.an = an;
    }
    public void setRegizor(String regizor) {

        this.regizor = regizor;
    }
    public void setDurata(int durata) {

        this.durata = durata;
    }
    public void setCategorie(CategoriiFilme categorie) {
        this.categorie = categorie;

    }
    public void setRestrictie(RestrictiiFilme restrictie) {

        this.restrictie = restrictie;
    }


    //functii speciale
    public String  tostring_film()
    {
        String s;
        s=nume+ " An: "+ an  + " " + categorie + " " + restrictie + "  Regizor: " + regizor + " " +durata+ "min ";
        return s;
    }
    public void afiseaza_ecranizari(ArrayList<Zi> zile)
    {
        String data=null;
        for(int i=0; i<this.ecranizari_programate.size(); i++)
        {
            for(int j=0; j<zile.size(); j++)
                if (zile.get(j).exista_ecranizare(ecranizari_programate.get(i).getEcranizareID()))
                    data=zile.get(j).getData();
            if(data != null)
                System.out.println(i +" :" +data + " " + ecranizari_programate.get(i).tostring_ecranizare());

        }
    }
    public boolean exista_ecranizare(int eID){
        for(int u=0;u<ecranizari_programate.size();u++) {
            if (ecranizari_programate.get(u).getEcranizareID() == eID)
                return true;
        }
        return false;
    }

    public void adaugaEcranizare(Ecranizare ecranizare) {

        this.ecranizari_programate.add(ecranizare);
    }


}
