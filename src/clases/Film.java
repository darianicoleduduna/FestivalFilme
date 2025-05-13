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
    private ArrayList<Ecranizare> ecranizari_programate;

    public Film() {
        this.filmID = ++this.last_id;
        this.nume = "";
        this.an = "";
        this.regizor = "";
        this.durata = 0;
        this.categorie = null;
        this.restrictie = null;
        this.ecranizari_programate = new ArrayList<>();

    }

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

    public int getFilmID() {
        return filmID;

    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;

    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAn() {
        return an;
    }

    public void setAn(String an) {
        this.an = an;
    }

    public String getRegizor() {
        return regizor;
    }

    public void setRegizor(String regizor) {
        this.regizor = regizor;
    }

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public CategoriiFilme getCategorie() {
        return categorie;

    }

    public void setCategorie(CategoriiFilme categorie) {
        this.categorie = categorie;

    }

    public RestrictiiFilme getRestrictie() {
        return restrictie;

    }

    public void setRestrictie(RestrictiiFilme restrictie) {
        this.restrictie = restrictie;
    }

    public void adaugaEcranizare(Ecranizare ecranizare) {
        this.ecranizari_programate.add(ecranizare);
    }

}
