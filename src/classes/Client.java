package classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;


import static classes.InterfataTest.profit_festival;

public class Client extends Persoana {
    private static int last_id = 0;
    private int ClientID;
    private RestrictiiFilme permisiuneFilme;
    private String dataNasterii;

    private ArrayList<Bilet> bilete_disponibile;

    public Client(String nume, String prenume, String email, String telefon,  String dataNasterii) {
        super(nume, prenume, email, telefon);
        this.ClientID = ++this.last_id;
        this.permisiuneFilme = permisiuneFilme;
        this.dataNasterii = dataNasterii;
        this.bilete_disponibile = new ArrayList<Bilet>();

    }

    //getters
    public int getClientID() {
        return ClientID;
    }
    public RestrictiiFilme getPermisiuneFilme() {
        return permisiuneFilme;
    }
    public String getDataNasterii() {
        return dataNasterii;
    }
    public int getNrBilete(){
        return bilete_disponibile.size();
    }
    public ArrayList<Bilet> getBilete_disponibile() { return bilete_disponibile;}


    //functii speciale
    public ArrayList<Bilet> cumparaBilet(ArrayList<Film> filme_disponibile, ArrayList<Zi> zile_festival,
                                         int tip_bilet, ArrayList<Integer> index_zile, ArrayList<Integer> index_filme,
                                                     ArrayList<Integer> index_ecranizari, ArrayList<Integer> randuri,
                                         ArrayList<Integer> coloane, boolean doreste_rezervari) {
        ArrayList<Bilet> bilete_noi = new ArrayList<>();

        if (tip_bilet == 3) { // Bilet pentru intreg festivalul
            Bilet b = new Bilet(CategorieBilet.Bilet_festival);
            bilete_noi.add(b);

            if (doreste_rezervari) {
                for (int i = 0; i < index_zile.size(); i++) {
                    int zi_index = index_zile.get(i);
                    int ec_index = index_ecranizari.get(i);
                    int rand = randuri.get(i);
                    int coloana = coloane.get(i);

                    Zi zi = zile_festival.get(zi_index);
                    Ecranizare e = zi.get_ecranizarebyindex(ec_index);
                    e.rezervareLoc(b, rand, coloana);
                }
            }

        } else if (tip_bilet == 2) { // Bilet pentru o zi
            int zi_index = index_zile.get(0);
            Zi zi = zile_festival.get(zi_index);
            Bilet b = new Bilet(CategorieBilet.Bilet_zi);
            b.setZi(zi);
            bilete_noi.add(b);

            if (doreste_rezervari) {
                for (int i = 0; i < index_ecranizari.size(); i++) {
                    int ec_index = index_ecranizari.get(i);
                    int rand = randuri.get(i);
                    int coloana = coloane.get(i);

                    Ecranizare e = zi.get_ecranizarebyindex(ec_index);
                    e.rezervareLoc(b, rand, coloana);
                }
            }

        } else if (tip_bilet == 1) { // Bilet pentru un film
            int film_index = index_filme.get(0);
            int ec_index = index_ecranizari.get(0);
            int rand = randuri.get(0);
            int coloana = coloane.get(0);

            Film f = filme_disponibile.get(film_index);
            if (!poateViziona(f)) {
                System.out.println("Nu aveți vârsta necesară pentru acest film.");
                return new ArrayList<>();
            }
            Ecranizare e = f.get_ecranizarebyindex(ec_index);

            Bilet b = new Bilet(CategorieBilet.Bilet_film);

            // Căutăm ziua care conține ecranizarea
            for (Zi zi : zile_festival) {
                if (zi.exista_ecranizare(e.getEcranizareID())) {
                    b.setZi(zi);
                    break;
                }
            }

            e.rezervareLoc(b, rand, coloana);
            bilete_noi.add(b);
        }

        bilete_disponibile.addAll(bilete_noi);
        return bilete_noi;
    }


    private boolean areRezervariValide(Rezervare[] rezervari) {
        if (rezervari == null) return false;
        for (Rezervare r : rezervari) {
            if (r != null) return true;
        }
        return false;
    }

    public float rambursare_bilet(Bilet biletDeAnulat, ArrayList<Zi> zile_festival){

        float rambursare=0;
        if(getOreRamase(biletDeAnulat,zile_festival)>48 )
        {

            if(biletDeAnulat.verifica_tip_bilet()==CategorieBilet.Bilet_film)
                rambursare=40;
            else if (biletDeAnulat.verifica_tip_bilet()==CategorieBilet.Bilet_zi)
                rambursare=100;
            else rambursare=250;
        }
        else if(getOreRamase(biletDeAnulat,zile_festival)>24 )
        {
            if(biletDeAnulat.verifica_tip_bilet()==CategorieBilet.Bilet_film)
                rambursare=20;
            else if (biletDeAnulat.verifica_tip_bilet()==CategorieBilet.Bilet_zi)
                rambursare=50;
            else rambursare=125;
        }
        return rambursare;
    }

    public int determinareVarsta()
    {
        Calendar azi = Calendar.getInstance();
        int azi_an = azi.get(Calendar.YEAR);
        int azi_luna = azi.get(Calendar.MONTH)+1;
        int azi_zi = azi.get(Calendar.DAY_OF_MONTH);
        String [] date = this.dataNasterii.split("\\.");
        int nastere_zi = Integer.parseInt(date[0]);
        int nastere_luna = Integer.parseInt(date[1]);
        int nastere_an = Integer.parseInt(date[2]);

        int diferenta_an = azi_an - nastere_an;
        if (nastere_luna > azi_luna)
            diferenta_an -=1;
        else if (nastere_luna == azi_luna && nastere_zi > azi_zi)
            diferenta_an -= 1;
        return diferenta_an;
    }
    public void setareRestrictiiFilme()
    {
        int varsta = determinareVarsta();
        if(varsta >= 18)
            this.permisiuneFilme = RestrictiiFilme.Interzis18ani;
        else if(varsta >= 16)
            this.permisiuneFilme = RestrictiiFilme.Restrictionat16ani;
        else if(varsta >= 13)
            this.permisiuneFilme = RestrictiiFilme.AcordParental13ani;
        else
            this.permisiuneFilme = RestrictiiFilme.AudientaGenerala;
    }
    public boolean poateViziona(Film film) {

        return this.permisiuneFilme.ordinal() >= film.getRestrictie().ordinal();
    }

    public String getDetaliiClient() {
        return  "Nume: " + getNume() + " " + getPrenume() +
                ", Email: " + getEmail() +
                ", Telefon: " + getTelefon() +
                ", Numar bilete: " + bilete_disponibile.size();
    }

    public long getOreRamase(Bilet b, ArrayList<Zi> zileFestival) {
        LocalDateTime acum = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        try {
            if (b.verifica_tip_bilet() == CategorieBilet.Bilet_zi && b.getZi() != null) {
                LocalDateTime ziBilet = LocalDateTime.parse(b.getZi().getData() + " 00:00", formatter);
                return ChronoUnit.HOURS.between(acum, ziBilet);
            }

            if (b.verifica_tip_bilet() == CategorieBilet.Bilet_festival && !zileFestival.isEmpty()) {
                LocalDateTime primaZi = LocalDateTime.parse(zileFestival.get(0).getData() + " 00:00", formatter);
                return ChronoUnit.HOURS.between(acum, primaZi);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public Plata plateste(Bilet[] bilete, boolean confirmaPlata, MetodaPlata metoda) {
        float s = 0;
        for (Bilet b : bilete) {
            if (b.verifica_tip_bilet() == CategorieBilet.Bilet_zi) s += 100;
            if (b.verifica_tip_bilet() == CategorieBilet.Bilet_festival) s += 250;
            if (b.verifica_tip_bilet() == CategorieBilet.Bilet_film) s += 40;
        }

        if (!confirmaPlata) {
            Plata p = new Plata();
            p.setStatusPlata(StatusPlata.Esuata);
            return p;
        }

        String data_curenta = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Plata p = new Plata(data_curenta, s, metoda, bilete);
        System.out.println("Plata inregistrata. Procesarea platii poate dura cateva minute.");
        profit_festival += s;
        return p;
    }

    public String anuleazaBilet(ArrayList<Film> filme_disponibile, ArrayList<Zi> zile_festival, int indexBiletDeAnulat) {
        if (bilete_disponibile.isEmpty()) {
            return "Nu aveți bilete de anulat.";
        }

        if (indexBiletDeAnulat < 0 || indexBiletDeAnulat >= bilete_disponibile.size()) {
            return "Index invalid pentru biletul de anulat.";
        }

        Bilet biletDeAnulat = bilete_disponibile.get(indexBiletDeAnulat);

        // Eliberăm locurile rezervate și ștergem din ecranizări
        if (biletDeAnulat.getZi() != null && biletDeAnulat.getRezervari() != null) {
            for (Rezervare rez : biletDeAnulat.getRezervari()) {
                if (rez == null) continue; // adăugat pentru siguranță
                for (Ecranizare e : biletDeAnulat.getZi().getEcranizari()) {
                    if (e.getRezervari().contains(rez)) {
                        int r = rez.getRand();
                        int c = rez.getColoana();
                        e.elibereazaLoc(r, c);
                        e.getRezervari().remove(rez);
                        e.getBileteCumparate().remove(biletDeAnulat);
                    }
                }
            }
        }

        float ramburs = rambursare_bilet(biletDeAnulat, zile_festival);
        profit_festival -= ramburs;
        bilete_disponibile.remove(indexBiletDeAnulat);

        return "Biletul a fost anulat cu succes.";
    }

    public String anuleazaRezervareBilet(
            ArrayList<Film> filme_disponibile,
            ArrayList<Zi> zile_festival,
            ArrayList<Sala> sali_disponibile,
            int indexBilet,
            int indexRezervare) {

        // Filtrăm biletele eligibile
        ArrayList<Bilet> eligibile = new ArrayList<>();
        for (Bilet b : bilete_disponibile) {
            if ((b.verifica_tip_bilet() == CategorieBilet.Bilet_zi || b.verifica_tip_bilet() == CategorieBilet.Bilet_festival)
                    && b.getRezervari() != null && b.nrRezervari() > 0) {
                eligibile.add(b);
            }
        }

        if (eligibile.isEmpty()) {
            return "Nu există bilete de tip zi sau festival cu rezervări.";
        }

        if (indexBilet < 0 || indexBilet >= eligibile.size()) {
            return "Index invalid pentru bilet.";
        }

        Bilet b = eligibile.get(indexBilet);
        Rezervare[] rezervari = b.getRezervari();

        ArrayList<Rezervare> rezervariValide = new ArrayList<>();
        for (Rezervare r : rezervari) {
            if (r != null) rezervariValide.add(r);
        }

        if (rezervariValide.isEmpty()) {
            return "Biletul nu are rezervări active.";
        }

        if (indexRezervare < 0 || indexRezervare >= rezervariValide.size()) {
            return "Index invalid pentru rezervare.";
        }

        Rezervare deAnulat = rezervariValide.get(indexRezervare);

        // Eliberăm locul și ștergem din ecranizare
        for (Zi zi : zile_festival) {
            for (Ecranizare e : zi.getEcranizari()) {
                if (e.getRezervari().contains(deAnulat)) {
                    e.elibereazaLoc(deAnulat.getRand(), deAnulat.getColoana());
                    e.getRezervari().remove(deAnulat);
                    break;
                }
            }
        }

        // Refacem vectorul de rezervări fără rezervarea anulată
        Rezervare[] originale = b.getRezervari();
        Rezervare[] nou = new Rezervare[originale.length];
        int j = 0;
        for (Rezervare r : originale) {
            if (r != null && r.getRezervareId() != deAnulat.getRezervareId()) {
                nou[j++] = r;
            }
        }
        b.setRezervari(nou);

        return "Rezervarea a fost anulată cu succes.";
    }

    public void vizualizeazaBilete(ArrayList<Film> filme_disponibile, ArrayList<Zi> zile_festival,ArrayList<Sala> sali_disponibile) {

        for (int i = 0; i < bilete_disponibile.size(); i++) {
            Bilet b = bilete_disponibile.get(i);
            System.out.println("---------------");
            System.out.println((i + 1) + ". BILET");
            System.out.println("Tip: " + b.verifica_tip_bilet());

            if (b.getZi() != null)
                System.out.println("Zi: " + b.getZi().tostring());

            Rezervare[] rezervari = b.getRezervari();
            if (areRezervariValide(rezervari)) {
                System.out.println("Locuri rezervate:");
                for (Rezervare r : rezervari) {
                    if (r == null) continue;
                    boolean afisat = false;

                    for (Zi zi : zile_festival) {
                        for (Ecranizare e : zi.getEcranizari()) {
                            for (Rezervare rez : e.getRezervari()) {
                                if (rez != null && rez.getRezervareId() == r.getRezervareId()) {
                                    String filmStr = "";
                                    for (Film f : filme_disponibile) {
                                        if (f.exista_ecranizare(e.getEcranizareID())) {
                                            filmStr = f.tostring_film();
                                            break;
                                        }
                                    }
                                    String salaStr = "necunoscută";
                                    for (Sala sala : sali_disponibile) {
                                        if (sala.getProgramEcranizari() != null &&
                                                sala.getProgramEcranizari().contains(e)) {
                                            salaStr = sala.getNume();
                                            break;
                                        }
                                    }
                                    System.out.println("- Rand: " + (r.getRand() + 1)
                                            + ", Coloană: " + (r.getColoana() + 1)
                                            + ", Film: " + filmStr
                                            + ", Ecranizare: " + e.tostring_ecranizare()
                                            + ", Sala: " + salaStr
                                            + ", Zi: " + zi.getData());
                                    afisat = true;
                                    break;
                                }
                            }
                            if (afisat) break;
                        }
                        if (afisat) break;
                    }

                    if (!afisat) {
                        System.out.println("- Rand: " + (r.getRand() + 1) + ", Coloană: " + (r.getColoana() + 1) + " (ecranizare necunoscută)");
                    }
                }
            } else {
                System.out.println("Nu există locuri rezervate.");
            }
        }
    }


    public String adaugaRezervareInBilet(ArrayList<Film> filme_disponibile, ArrayList<Zi> zile_festival, int indexBilet, Integer indexZi, int indexEcranizare, int rand, int loc) {
        // Filtrăm biletele eligibile
        ArrayList<Bilet> bileteEligibile = new ArrayList<>();
        for (Bilet b : bilete_disponibile) {
            if (b.verifica_tip_bilet() == CategorieBilet.Bilet_zi || b.verifica_tip_bilet() == CategorieBilet.Bilet_festival) {
                bileteEligibile.add(b);
            }
        }

        if (bileteEligibile.isEmpty()) {
            return "Nu aveți bilete de tip zi sau festival.";
        }

        if (indexBilet < 0 || indexBilet >= bileteEligibile.size()) {
            return "Index invalid pentru bilet.";
        }

        Bilet b = bileteEligibile.get(indexBilet);
        Zi ziAleasa = null;

        if (b.verifica_tip_bilet() == CategorieBilet.Bilet_zi) {
            ziAleasa = b.getZi();
            if (ziAleasa == null) {
                return "Biletul de tip zi nu are zi asociată.";
            }
        } else { // Bilet_festival
            if (indexZi == null || indexZi < 0 || indexZi >= zile_festival.size()) {
                return "Index invalid pentru zi.";
            }
            ziAleasa = zile_festival.get(indexZi);
        }

        // Verificăm ecranizarea dorită
        int nrEcranizari = ziAleasa.getNrEcranizari();
        if (indexEcranizare < 0 || indexEcranizare >= nrEcranizari) {
            return "Index invalid pentru ecranizare.";
        }

        Ecranizare ecranizare = ziAleasa.get_ecranizarebyindex(indexEcranizare);

        // Se face rezervarea efectivă folosind rand și loc
        ecranizare.rezervareLoc(b, rand, loc);

        // Setăm ziua în bilet dacă este festival (bilet_zi deja are)
        if (b.verifica_tip_bilet() == CategorieBilet.Bilet_festival && b.getZi() == null) {
            b.setZi(ziAleasa);
        }

        return "Rezervarea a fost adăugată cu succes.";
    }
}
