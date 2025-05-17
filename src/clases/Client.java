package clases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.time.temporal.ChronoUnit;


import static clases.InterfataTest.profit_festival;

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

    public Client() {
        super();
        this.ClientID = 0;
        this.permisiuneFilme = null;
        this.dataNasterii = null;
        this.bilete_disponibile = new ArrayList<Bilet>();

    }

    private boolean areRezervariValide(Rezervare[] rezervari) {
        if (rezervari == null) return false;
        for (Rezervare r : rezervari) {
            if (r != null) return true;
        }
        return false;
    }
    public int getClientID() {
        return ClientID;
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

    public ArrayList<Bilet> cumparaBilet( ArrayList<Film> filme_disponibile, ArrayList<Zi> zile_festival)
    {
        ArrayList<Bilet> bilete_noi = new ArrayList<>();
        int tip_bilet,index;
        Scanner s = new Scanner(System.in);
        do {


            System.out.println("Alegeti tipul biletului:");
            System.out.println("1-Bilet pentru un film.");
            System.out.println("2-Bilet pentru o zi din festival.");
            System.out.println("3-Bilet pentru intreg festivalul.");
            tip_bilet=s.nextInt();
            s.nextLine();

        }
        while(tip_bilet>3 || tip_bilet<1);



        if(tip_bilet == 3) //festival
        {
            Bilet b = new Bilet(null, CategorieBilet.Bilet_festival);
            bilete_noi.add(b);
            System.out.println("Doriti sa rezervati locuri pentru diverse ecranizari? d/n");
            char raspuns = s.nextLine().charAt(0);

            while (raspuns == 'd'){
                System.out.println("Alegeti una dintre zilele festivalului:");
                for (int i = 0; i < zile_festival.size(); i++)
                    System.out.println((i+1) +": "+ zile_festival.get(i).tostring());
                int zidorita = s.nextInt() - 1;
                s.nextLine();
                Zi zi_temp = zile_festival.get(zidorita);
                zi_temp.afiseaza_ecranizari_pe_zi(filme_disponibile,this.permisiuneFilme);
                System.out.println("Alegeti nr ecranizarii dorite: ");
                int ind = s.nextInt() - 1;
                s.nextLine();
                zi_temp.get_ecranizarebyindex(ind).rezervareLoc(b);
                System.out.println("Doresti sa iti rezervi loc la alta ecranizare? d/n");
                raspuns = s.nextLine().charAt(0);

            }
        }
        else if(tip_bilet == 2) //bilet de tip zi
        {
            Bilet b = new Bilet( null, CategorieBilet.Bilet_zi);
            System.out.println("Alegeti una dintre zilele festivalului:");
            for (int i = 0; i < zile_festival.size(); i++)
                System.out.println((i+1) +": "+ zile_festival.get(i).tostring());
            int zidorita = s.nextInt() - 1;
            s.nextLine();
            b.setZi(zile_festival.get(zidorita));
            bilete_noi.add(b);
            System.out.println("Doresti sa iti rezervi loc la ecranizari? d/n");
            char raspuns = s.nextLine().charAt(0);
            while( raspuns == 'd' ) {
                b.getZi().afiseaza_ecranizari_pe_zi(filme_disponibile,this.permisiuneFilme);
                System.out.println("Alegeti nr ecranizarii dorite: ");
                int ind = s.nextInt() - 1;
                s.nextLine();
                b.getZi().get_ecranizarebyindex(ind).rezervareLoc(b);

                System.out.println("Doresti sa iti rezervi loc la alta ecranizare? d/n");
                raspuns = s.nextLine().charAt(0);
            }

        }
        else { //bilet de tip film
            int filmdorit;
            Bilet b = new Bilet(null, CategorieBilet.Bilet_film);


            System.out.println("Filmele disponibile sunt :");
            for (int i=0; i<filme_disponibile.size(); i++)
                if(poateViziona(filme_disponibile.get(i))) {
                    System.out.println((i + 1) + ":" + filme_disponibile.get(i).tostring_film());
                }
            System.out.println("Introduceti nr pt filmul dorit");
            filmdorit=s.nextInt() - 1;
            s.nextLine();
            filme_disponibile.get(filmdorit).afiseaza_ecranizari(zile_festival);
            System.out.println("Introduceti nr ecranizarii dorite: ");

            index = s.nextInt();
            s.nextLine();
            for (int i = 0; i < zile_festival.size(); i++)
                if (zile_festival.get(i).exista_ecranizare(filme_disponibile.get(filmdorit).get_ecranizarebyindex(index).getEcranizareID()))
                    b.setZi(zile_festival.get(i));
            filme_disponibile.get(filmdorit).get_ecranizarebyindex(index).rezervareLoc(b);
            bilete_noi.add(b);
        }
        bilete_disponibile.addAll(bilete_noi);
        return bilete_noi;
    }

    public Plata plateste(Bilet[] bilete){
        Scanner scanner = new Scanner(System.in);
        float s = 0;
        for (Bilet b : bilete) {
            if(b.verifica_tip_bilet() == CategorieBilet.Bilet_zi) s = s + 100;
            if(b.verifica_tip_bilet() == CategorieBilet.Bilet_festival) s = s + 250;
            if(b.verifica_tip_bilet() == CategorieBilet.Bilet_film) s = s + 40;
        }

        System.out.println("Aveti de plata " + s + " lei. Continuati? d/n");
        char x = scanner.next().charAt(0);
        scanner.nextLine();
        int temp;
        if(x == 'd'){
            String data_curenta;
            MetodaPlata metodaP=null;
            do{
                System.out.println("Cum doriti sa efectuati plata? 1- Cash, 2- Card, 3- Transfer");
                temp = scanner.nextInt();
                scanner.nextLine();
            }
            while(temp <1 || temp >3);

            if(temp == 1) metodaP = MetodaPlata.Cash;
            if(temp == 2) metodaP = MetodaPlata.Card;
            if(temp == 3) metodaP = MetodaPlata.Transfer;

            data_curenta = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Plata p = new Plata(data_curenta, s, metodaP, bilete);
            System.out.println("Plata inregistrata. Procesarea platii poate dura cateva minute. ");
            profit_festival=profit_festival+s;
            return p;
        }
        Plata p = new Plata();
        p.setStatusPlata(StatusPlata.Esuata);
        return p;
    }

    public void anuleazaBilet(ArrayList<Film> filme_disponibile, ArrayList<Zi> zile_festival) {
        if (bilete_disponibile.isEmpty()) {
            System.out.println("Nu aveți bilete de anulat.");
            return;
        }

        Scanner s = new Scanner(System.in);
        System.out.println("Selectați biletul de anulat:");
        for (int i = 0; i < bilete_disponibile.size(); i++) {
            Bilet b = bilete_disponibile.get(i);
            String info = "Tip: " + b.verifica_tip_bilet();

            if (b.getZi() != null)
                info += ", Zi: " + b.getZi().tostring();

            if (b.verifica_tip_bilet() == CategorieBilet.Bilet_film && b.getZi() != null) {
                for (Ecranizare e : b.getZi().getEcranizari()) {
                    if (e.getBileteCumparate().contains(b)) {
                        for (Film f : filme_disponibile) {
                            if (f.exista_ecranizare(e.getEcranizareID())) {
                                info += ", Film: " + f.tostring_film() + ", Ecranizare: " + e.tostring_ecranizare();
                                break;
                            }
                        }
                    }
                }
            }

            System.out.println((i + 1) + ": " + info);
        }

        int opt = s.nextInt() - 1;
        s.nextLine();
        if (opt < 0 || opt >= bilete_disponibile.size()) {
            System.out.println("Index invalid.");
            return;
        }

        Bilet biletDeAnulat = bilete_disponibile.get(opt);


        // Eliberăm locurile rezervate și ștergem din ecranizări
        if (biletDeAnulat.getZi() != null && biletDeAnulat.getRezervari() != null) {
            for (Rezervare rez : biletDeAnulat.getRezervari()) {
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
        float ramburs=rambursare_bilet(biletDeAnulat,zile_festival);
        profit_festival-=ramburs;
        bilete_disponibile.remove(opt);
        System.out.println("Biletul a fost anulat cu succes.");
    }

    public void anuleazaRezervareBilet(ArrayList<Film> filme_disponibile, ArrayList<Zi> zile_festival, ArrayList<Sala> sali_disponibile) {
        if (bilete_disponibile.isEmpty()) {
            System.out.println("Nu aveți bilete.");
            return;
        }

        Scanner s = new Scanner(System.in);
        System.out.println("Selectați biletul din care doriți să anulați o rezervare:");

        ArrayList<Bilet> eligibile = new ArrayList<>();
        for (Bilet b : bilete_disponibile) {
            if ((b.verifica_tip_bilet() == CategorieBilet.Bilet_zi || b.verifica_tip_bilet() == CategorieBilet.Bilet_festival)
                    && b.getRezervari() != null && b.getRezervari().length > 0) {
                eligibile.add(b);
            }
        }

        if (eligibile.isEmpty()) {
            System.out.println("Nu există bilete de tip zi sau festival cu rezervări.");
            return;
        }

        for (int i = 0; i < eligibile.size(); i++) {
            System.out.println((i + 1) + ": Tip - " + eligibile.get(i).verifica_tip_bilet());
        }

        int bIndex = s.nextInt() - 1;
        s.nextLine();
        if (bIndex < 0 || bIndex >= eligibile.size()) {
            System.out.println("Index invalid.");
            return;
        }

        Bilet b = eligibile.get(bIndex);
        Rezervare[] rezervari = b.getRezervari();

        ArrayList<Rezervare> rezervariValide = new ArrayList<>();
        for (Rezervare r : rezervari) {
            if (r != null) rezervariValide.add(r);
        }

        if (rezervariValide.isEmpty()) {
            System.out.println("Biletul nu are rezervări active.");
            return;
        }

        System.out.println("Selectați rezervarea de anulat:");
        for (int i = 0; i < rezervariValide.size(); i++) {
            Rezervare rez = rezervariValide.get(i);

            String filmStr = "necunoscut";
            String salaStr = "necunoscut";
            String ecranizareStr = "necunoscut";
            String ziStr = "necunoscut";

            for (Zi zi : zile_festival) {
                for (Ecranizare e : zi.getEcranizari()) {
                    if (e.getRezervari().contains(rez)) {
                        ziStr = zi.getData();
                        ecranizareStr = e.tostring_ecranizare();

                        for (Film f : filme_disponibile) {
                            if (f.exista_ecranizare(e.getEcranizareID())) {
                                filmStr = f.tostring_film();
                                break;
                            }
                        }

                        for (Sala sala : sali_disponibile) {
                            if (sala.getProgramEcranizari().contains(e)) {
                                salaStr = sala.getNume();
                                break;
                            }
                        }
                        break;
                    }
                }
            }

            System.out.println((i + 1) + ": Rand " + (rez.getRand() + 1) + ", Coloană " + (rez.getColoana() + 1)
                    + ", Film: " + filmStr + ", Sala: " + salaStr + ", Zi: " + ziStr + ", Ecranizare: " + ecranizareStr);
        }

        int rIndex = s.nextInt() - 1;
        s.nextLine();
        if (rIndex < 0 || rIndex >= rezervariValide.size()) {
            System.out.println("Index invalid.");
            return;
        }

        Rezervare deAnulat = rezervariValide.get(rIndex);

        // Eliberăm locul și ștergem din ecranizare
        for (Zi zi : zile_festival) {
            for (Ecranizare e : zi.getEcranizari()) {
                if (e.getRezervari().contains(deAnulat)) {
                    e.elibereazaLoc(deAnulat.getRand(), deAnulat.getColoana());
                    e.getRezervari().remove(deAnulat);
                    // Nu scoatem biletul din bilete_cumparate
                    break;
                }
            }
        }

        // Ștergem rezervarea din bilet
        Rezervare[] originale = b.getRezervari();
        Rezervare[] nou = new Rezervare[originale.length];
        int j = 0;
        for (Rezervare r : originale) {
            if (r != null && r.getRezervareId() != deAnulat.getRezervareId()) {
                nou[j++] = r;
            }
        }
        b.setRezervari(nou);

        System.out.println("Rezervarea a fost anulată cu succes.");
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

    public void adaugaRezervareInBilet(ArrayList<Film> filme_disponibile, ArrayList<Zi> zile_festival) {
        Scanner s = new Scanner(System.in);

        // Filtrăm biletele eligibile
        ArrayList<Bilet> bileteEligibile = new ArrayList<>();
        for (Bilet b : bilete_disponibile) {
            if (b.verifica_tip_bilet() == CategorieBilet.Bilet_zi || b.verifica_tip_bilet() == CategorieBilet.Bilet_festival) {
                bileteEligibile.add(b);
            }
        }

        if (bileteEligibile.isEmpty()) {
            System.out.println("Nu aveți bilete de tip zi sau festival.");
            return;
        }

        // Alege biletul
        System.out.println("Selectați biletul pentru care doriți să adăugați o rezervare:");
        for (int i = 0; i < bileteEligibile.size(); i++) {
            Bilet b = bileteEligibile.get(i);
            System.out.print((i + 1) + ": Tip - " + b.verifica_tip_bilet());
            if (b.getZi() != null) System.out.print(", Zi: " + b.getZi().tostring());
            System.out.println();
        }

        int bIndex = s.nextInt() - 1;
        s.nextLine();
        if (bIndex < 0 || bIndex >= bileteEligibile.size()) {
            System.out.println("Index invalid.");
            return;
        }

        Bilet b = bileteEligibile.get(bIndex);

        Zi ziAleasa = null;

        if (b.verifica_tip_bilet() == CategorieBilet.Bilet_zi) {
            ziAleasa = b.getZi();
            if (ziAleasa == null) {
                System.out.println("Biletul de tip zi nu are zi asociată.");
                return;
            }
        } else { // Bilet_festival
            // Alege o zi din festival
            System.out.println("Selectați ziua pentru care doriți să adăugați o rezervare:");
            for (int i = 0; i < zile_festival.size(); i++) {
                System.out.println((i + 1) + ": " + zile_festival.get(i).tostring());
            }
            int ziIndex = s.nextInt() - 1;
            s.nextLine();
            if (ziIndex < 0 || ziIndex >= zile_festival.size()) {
                System.out.println("Index invalid.");
                return;
            }
            ziAleasa = zile_festival.get(ziIndex);
        }

        // Afișează ecranizările disponibile în ziua aleasă
        ziAleasa.afiseaza_ecranizari_pe_zi(filme_disponibile, this.permisiuneFilme);

        System.out.println("Selectați numărul ecranizării dorite:");
        int eIndex = s.nextInt() - 1;
        s.nextLine();
        if (eIndex < 0 || eIndex >= ziAleasa.getNrEcranizari()) {
            System.out.println("Index invalid pentru ecranizare.");
            return;
        }

        Ecranizare ecranizare = ziAleasa.get_ecranizarebyindex(eIndex);

        // Se face rezervarea efectivă
        ecranizare.rezervareLoc(b);

        // Setăm ziua în bilet dacă este festival (bilet_zi deja are)
        if (b.verifica_tip_bilet() == CategorieBilet.Bilet_festival && b.getZi() == null) {
            b.setZi(ziAleasa);
        }

        System.out.println("Rezervarea a fost adăugată cu succes.");
    }

}
