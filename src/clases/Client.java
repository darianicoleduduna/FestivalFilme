package clases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

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

    public int getClientID() {
        return ClientID;
    }

    public RestrictiiFilme getPermisiuneFilme() {
        return permisiuneFilme;
    }

    public String getDataNasterii() {
        return dataNasterii;
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
            return p;
        }
        Plata p = new Plata();
        p.setStatusPlata(StatusPlata.Esuata);
        return p;
    }

}
