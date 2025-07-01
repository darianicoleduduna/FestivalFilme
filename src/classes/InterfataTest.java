package classes;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class InterfataTest {

    public static float profit_festival=0;

    public static String calculeazaOraFinala(String oraInceput, int durataMinute) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("[H:mm][HH:mm]"); // pentru parsare flexibilă
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm"); // pentru afișare clară

        LocalTime inceput = LocalTime.parse(oraInceput, inputFormatter);
        LocalTime finala = inceput.plusMinutes(durataMinute);
        return finala.format(outputFormatter);
    }

    public static void main(String[] args) {

        ArrayList<Zi> zile = new ArrayList<Zi>();
        Zi zi1= new Zi("17.04.2025");
        Zi zi2= new Zi("18.04.2025");
        Zi zi3= new Zi("19.04.2025");
        System.out.println("FESTIVALUL DE FILME - Diversity Art and Vision (DAV Studios)");


        Film [] films = new Film[15];
        films[0] = new Film("The Shining", "1980", "Stanley Kubrick", 146, RestrictiiFilme.Interzis18ani, CategoriiFilme.Horror);
        films[1] = new Film("Harry Potter and the Chamber of Secrets", "2002", "Chris Columbus", 161, RestrictiiFilme.AcordParental13ani, CategoriiFilme.Fantasy);
        films[2] = new Film("Inception", "2010", "Christopher Nolan", 148, RestrictiiFilme.Restrictionat16ani, CategoriiFilme.Thriller);
        films[3] = new Film("Flow", "2025", "Gints Zilbalodis", 80, RestrictiiFilme.AudientaGenerala, CategoriiFilme.Animatie);
        films[4] = new Film("Harry Potter and the Philosopher's Stone", "2001", "Chris Columbus", 152, RestrictiiFilme.AudientaGenerala, CategoriiFilme.Fantasy);
        films[5] = new Film("The Notebook", "2004", "Nick Cassavetes", 123, RestrictiiFilme.AcordParental13ani, CategoriiFilme.Dragoste);
        films[6] = new Film("Avengers: Endgame", "2019", "Anthony & Joe Russo", 181, RestrictiiFilme.Restrictionat16ani, CategoriiFilme.Actiune);
        films[7] = new Film("Frozen", "2013", "Chris Buck & Jennifer Lee", 102, RestrictiiFilme.AudientaGenerala, CategoriiFilme.Animatie);
        films[8] = new Film("The Matrix", "1999", "The Wachowskis", 136, RestrictiiFilme.Restrictionat16ani, CategoriiFilme.Science_Fiction);
        films[9] = new Film("The Lion King", "1994", "Roger Allers & Rob Minkoff", 88, RestrictiiFilme.AudientaGenerala, CategoriiFilme.Animatie);
        films[10] = new Film("Les Misérables", "2012", "Tom Hooper", 158, RestrictiiFilme.AcordParental13ani, CategoriiFilme.Musical);
        films[11] = new Film("The Conjuring", "2013", "James Wan", 112, RestrictiiFilme.Interzis18ani, CategoriiFilme.Horror);
        films[12] = new Film("A Beautiful Mind", "2001", "Ron Howard", 135, RestrictiiFilme.AcordParental13ani, CategoriiFilme.Drama);
        films[13] = new Film("Mamma Mia!", "2008", "Phyllida Lloyd", 108, RestrictiiFilme.AudientaGenerala, CategoriiFilme.Musical);
        films[14] = new Film("Sherlock Holmes", "2009", "Guy Ritchie", 128, RestrictiiFilme.AcordParental13ani, CategoriiFilme.Mister);

        Sala [] sals = new Sala[5];
        sals[0] = new Sala("Sala1", 10, 20);
        sals[1] = new Sala("Sala2", 25, 30);
        sals[2] = new Sala("Sala3", 15, 20);
        sals[3] = new Sala("Sala4", 15, 20);
        sals[4] = new Sala("Sala5", 10, 20);

        Ecranizare e0_1 = new Ecranizare("09:00", "11:26");
        e0_1.adaugaEcranizare(films[0], sals[0], zi1);

        Ecranizare e0_2 = new Ecranizare("12:00", "14:26");
        e0_2.adaugaEcranizare(films[0], sals[1], zi2);

        Ecranizare e1_1 = new Ecranizare("09:00", "11:41");
        e1_1.adaugaEcranizare(films[1], sals[1], zi1);

        Ecranizare e1_2 = new Ecranizare("15:00", "17:41");
        e1_2.adaugaEcranizare(films[1], sals[2], zi2);

        Ecranizare e2_1 = new Ecranizare("12:00", "14:28");
        e2_1.adaugaEcranizare(films[2], sals[2], zi1);

        Ecranizare e2_2 = new Ecranizare("09:00", "11:28");
        e2_2.adaugaEcranizare(films[2], sals[3], zi2);

        Ecranizare e3_1 = new Ecranizare("15:00", "16:20");
        e3_1.adaugaEcranizare(films[3], sals[0], zi1);

        Ecranizare e3_2 = new Ecranizare("12:00", "13:20");
        e3_2.adaugaEcranizare(films[3], sals[3], zi2);

        Ecranizare e4_1 = new Ecranizare("17:00", "19:32");
        e4_1.adaugaEcranizare(films[4], sals[1], zi1);

        Ecranizare e4_2 = new Ecranizare("20:00", "22:32");
        e4_2.adaugaEcranizare(films[4], sals[0], zi2);

        Ecranizare e5_1 = new Ecranizare("09:00", "11:03");
        e5_1.adaugaEcranizare(films[5], sals[4], zi1);

        Ecranizare e5_2 = new Ecranizare("22:00", "00:03");
        e5_2.adaugaEcranizare(films[5], sals[2], zi2);

        Ecranizare e6_1 = new Ecranizare("09:00", "12:01");
        e6_1.adaugaEcranizare(films[6], sals[3], zi1);

        Ecranizare e6_2 = new Ecranizare("14:00", "17:01");
        e6_2.adaugaEcranizare(films[6], sals[4], zi2);

        Ecranizare e7_1 = new Ecranizare("12:30", "14:12");
        e7_1.adaugaEcranizare(films[7], sals[0], zi2);

        Ecranizare e7_2 = new Ecranizare("15:00", "16:42");
        e7_2.adaugaEcranizare(films[7], sals[3], zi3);

        Ecranizare e8_1 = new Ecranizare("12:00", "14:16");
        e8_1.adaugaEcranizare(films[8], sals[4], zi1);

        Ecranizare e8_2 = new Ecranizare("17:30", "19:46");
        e8_2.adaugaEcranizare(films[8], sals[0], zi3);

        Ecranizare e9_1 = new Ecranizare("15:00", "16:28");
        e9_1.adaugaEcranizare(films[9], sals[1], zi2);

        Ecranizare e9_2 = new Ecranizare("09:00", "10:28");
        e9_2.adaugaEcranizare(films[9], sals[2], zi3);

        Ecranizare e10_1 = new Ecranizare("11:30", "14:08");
        e10_1.adaugaEcranizare(films[10], sals[1], zi3);

        Ecranizare e10_2 = new Ecranizare("17:30", "20:08");
        e10_2.adaugaEcranizare(films[10], sals[4], zi1);

        Ecranizare e11_1 = new Ecranizare("09:00", "10:52");
        e11_1.adaugaEcranizare(films[11], sals[1], zi2);

        Ecranizare e11_2 = new Ecranizare("12:00", "13:52");
        e11_2.adaugaEcranizare(films[11], sals[2], zi3);

        Ecranizare e12_1 = new Ecranizare("11:00", "13:15");
        e12_1.adaugaEcranizare(films[12], sals[0], zi3);

        Ecranizare e12_2 = new Ecranizare("14:30", "16:45");
        e12_2.adaugaEcranizare(films[12], sals[3], zi2);

        Ecranizare e13_1 = new Ecranizare("17:00", "18:48");
        e13_1.adaugaEcranizare(films[13], sals[2], zi1);

        Ecranizare e13_2 = new Ecranizare("15:00", "16:48");
        e13_2.adaugaEcranizare(films[13], sals[1], zi3);

        Ecranizare e14_1 = new Ecranizare("09:00", "11:08");
        e14_1.adaugaEcranizare(films[14], sals[3], zi3);

        Ecranizare e14_2 = new Ecranizare("14:30", "16:38");
        e14_2.adaugaEcranizare(films[14], sals[0], zi2);


        Client c1 = new Client("Duduna", "Daria", "c", "0745682515", "17.04.2003");
        Client c2 = new Client("Enache", "Vlad", "a", "0745682123", "02.07.2003");
        c1.setareRestrictiiFilme();


        Sala s1 = new Sala("Daria", 17, 12);
        zile.add(zi1);
        zile.add(zi2);
        zile.add(zi3);

        ArrayList<Client> clienti = new ArrayList<Client>();
        ArrayList<Staff> staff = new ArrayList<Staff>();
        ArrayList<Bilet> bilete = new ArrayList<Bilet>();
        ArrayList<Ecranizare> ecranizari = new ArrayList<Ecranizare>();
        ArrayList<Film> filme = new ArrayList<Film>();
        ArrayList<Sala> sali= new ArrayList<>();
        sali.add(sals[0]);
        sali.add(sals[1]);
        sali.add(sals[2]);
        sali.add(sals[3]);
        sali.add(sals[4]);

        filme.add(films[0]);
        filme.add(films[1]);
        filme.add(films[2]);
        filme.add(films[3]);
        filme.add(films[4]);
        filme.add(films[5]);
        filme.add(films[6]);
        filme.add(films[7]);
        filme.add(films[8]);
        filme.add(films[9]);
        filme.add(films[10]);
        filme.add(films[11]);
        filme.add(films[12]);
        filme.add(films[13]);
        filme.add(films[14]);
        Client vlad = new Client("vlad", "rares", "vlad@gmail.com", "0745022251", "13.03.2012");
        vlad.setareRestrictiiFilme();
        clienti.add(vlad);

        staff.add(new Staff("Popescu", "Cristina", "pop.cris@gmail.com", "0745612389", 2000, FunctieStaff.Angajat_FoodCourt, 0, Zone.Zona_Food_Court, 8));
        staff.add(new Staff("Coaja", "Andreea", "coaja@gmail.com", "0745612239", 3000, FunctieStaff.Bilete_si_acces, 0, Zone.Zona_Acces, 8));
        staff.add(new Staff("Balsam", "Vlad", "balsam@gmail.com", "0744612389", 5000, FunctieStaff.Proiectioner, 0, Zone.Zona_Proiectie_Aer_Liber, 4));
        Manager m =new Manager("Marin", "Ioana", "manager@gmail.com", "0745123456", 7000, FunctieStaff.Manager_Festival, 0, Zone.Birou_de_Informatii, 6);
        staff.add(new Staff("Ionescu", "Radu", "radu.ionescu@gmail.com", "0745234567", 6500, FunctieStaff.Director, 0, Zone.Birou_de_Informatii, 7));
        staff.add(new Staff("Georgescu", "Ana", "ana.georgescu@gmail.com", "0745345678", 4000, FunctieStaff.Coordonator_Sala, 1, Zone.Zona_Sali, 8));
        staff.add(new Staff("Popa", "Mihai", "mihai.popa@gmail.com", "0745456789", 2800, FunctieStaff.Echipa_de_Ospitalitate, 0, Zone.Zona_Food_Court, 6));
        staff.add(new Staff("Dumitru", "Elena", "elena.dumitru@gmail.com", "0745567890", 2500, FunctieStaff.Paza, 0, Zone.Zona_Proiectie_Aer_Liber, 10));
        staff.add(new Staff("Iliescu", "Maria", "maria.iliescu@gmail.com", "0745678901", 2200, FunctieStaff.Voluntar, 0, Zone.Zona_Acces, 5));
        staff.add(new Staff("Barbu", "Florin", "florin.barbu@gmail.com", "0745789012", 3200, FunctieStaff.Fotograf_Videograf, 0, Zone.Zona_Proiectie_Aer_Liber, 7));
        staff.add(new Staff("Pascu", "Diana", "diana.pascu@gmail.com", "0745890123", 4500, FunctieStaff.EchipaIT, 0, Zone.Birou_de_Informatii, 9));
        staff.add(new Staff("Savu", "Teodor", "teodor.savu@gmail.com", "0745901234", 2700, FunctieStaff.Supraveghetor_Sala, 3, Zone.Zona_Sali, 6));
        ArrayList<Plata> plati = new ArrayList<Plata>();
        ArrayList<Rezervare> rezervari = new ArrayList<Rezervare>();
        Client logat=null;
        Staff logatStaff=null;
        Manager logatManager=null;
        ArrayList<Bilet> bilete_temp = new ArrayList<>();

        char optiune;
        String text;
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            System.out.println("Alegeti o optiune: ");
            System.out.println("c - optiuni de client");
            System.out.println("s - optiuni de staff");
            System.out.println("m - optiuni de manager");
            System.out.println("e - iesire");
            optiune = scanner.next().charAt(0);
            scanner.nextLine();
            if(optiune == 'c')
            {
                System.out.println("Alegeti o optiune de client: ");
                System.out.println("a - adauga client");
                System.out.println("l - logare");
                System.out.println("d - vizualizare detalii cont");
                System.out.println("c - cumpara bilet");
                System.out.println("v - vizualizeaza bilet");
                System.out.println("m - modifica bilet");
                System.out.println("p - plateste");
                System.out.println("e - iesire");
                optiune = scanner.next().charAt(0);
                scanner.nextLine();
                if(optiune == 'a')
                {
                    String nume, prenume, email, telefon, dataNasterii;

                    while (true) {
                        System.out.println("Alegeti un nume: ");
                        nume = scanner.nextLine().trim();
                        if (!nume.isEmpty()) {
                            nume = capitalize(nume);
                            break;
                        }
                        System.out.println("Numele nu poate fi gol.");
                    }
                    while (true) {
                        System.out.println("Alegeti un prenume: ");
                        prenume = scanner.nextLine().trim();
                        if (!prenume.isEmpty()) {
                            prenume = capitalize(prenume);
                            break;
                        }
                        System.out.println("Prenumele nu poate fi gol.");
                    }
                    while (true) {
                        System.out.println("Alegeti un email: ");
                        email = scanner.nextLine().trim();
                        if (Pattern.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", email)) {
                            break;
                        }
                        System.out.println("Email invalid. Introduceti un email valid (ex: exemplu@email.com).");
                    }
                    while (true) {
                        System.out.println("Alegeti un telefon: ");
                        telefon = scanner.nextLine().trim();
                        if (Pattern.matches("^07\\d{8}$", telefon)) {
                            break;
                        }
                        System.out.println("Telefon invalid. Trebuie sa inceapa cu 07 si sa aiba 10 cifre.");
                    }
                    while (true) {
                        System.out.println("Alegeti o data a nasterii (dd.mm.yyyy): ");
                        dataNasterii = scanner.nextLine().trim();
                        if (Pattern.matches("^\\d{2}\\.\\d{2}\\.\\d{4}$", dataNasterii)) {
                            break;
                        }
                        System.out.println("Data nasterii invalida. Format corect: dd.mm.yyyy");
                    }
                    Client c = new Client(nume, prenume, email, telefon, dataNasterii);
                    c.setareRestrictiiFilme();
                    clienti.add(c);
                    System.out.println("Client creat cu succes: " + c.getNume() + " " + c.getPrenume());
                }
                else if(optiune=='l')
                {
                    String email;
                    boolean ok=false;
                    System.out.println("Introduceti emailul: ");
                    email= scanner.nextLine();
                    for (Client c : clienti) {
                        if (c.getEmail().equals(email)) {
                            logat=c;
                            ok=true;
                        }
                    }
                    if (!ok) {
                        System.out.println("Emailul invalid. Introduceti emailul valid sau creati-va cont de client");
                    }
                }
                /*else if (optiune == 'c')
                {
                    if(logat != null) {
                        ArrayList<Bilet> bilete_client = logat.cumparaBilet(filme, zile);
                        if (bilete_client != null) {
                            bilete_temp.addAll(bilete_client);
                        }
                    }
                    else
                        System.out.println("Trebuie sa va logati. Alegeti l.");
                }*/
                else if (optiune == 'c') {
                    if (logat != null) {
                        Scanner s = new Scanner(System.in);
                        ArrayList<Integer> index_zile = new ArrayList<>();
                        ArrayList<Integer> index_filme = new ArrayList<>();
                        ArrayList<Integer> index_ecranizari = new ArrayList<>();
                        ArrayList<Integer> randuri = new ArrayList<>();
                        ArrayList<Integer> coloane = new ArrayList<>();
                        boolean doreste_rezervari = false;

                        System.out.println("Alegeti tipul biletului:");
                        System.out.println("1 - Bilet pentru un film");
                        System.out.println("2 - Bilet pentru o zi din festival");
                        System.out.println("3 - Bilet pentru intreg festivalul");

                        int tip_bilet;
                        do {
                            System.out.print("Optiunea dvs: ");
                            tip_bilet = s.nextInt();
                            s.nextLine();
                        } while (tip_bilet < 1 || tip_bilet > 3);

                        if (tip_bilet == 3) {
                            System.out.print("Doriti sa rezervati locuri pentru diverse ecranizari? (d/n): ");
                            char rasp = s.nextLine().charAt(0);
                            doreste_rezervari = (rasp == 'd');

                            while (rasp =='d') {
                                System.out.println("Alegeti o zi:");
                                for (int i = 0; i < zile.size(); i++)
                                    System.out.println((i + 1) + ": " + zile.get(i).tostring());
                                int zi_index = s.nextInt() - 1;
                                s.nextLine();
                                Zi zi_temp = zile.get(zi_index);
                                zi_temp.afiseaza_ecranizari_pe_zi(filme, logat.getPermisiuneFilme());

                                System.out.print("Alegeti nr ecranizarii dorite: ");
                                int ec_index = s.nextInt() - 1;
                                s.nextLine();
                                zi_temp.get_ecranizarebyindex(ec_index).afisare_locuri();
                                System.out.print("Introduceti randul dorit: ");
                                int rand = s.nextInt();
                                s.nextLine();

                                System.out.print("Introduceti coloana dorita: ");
                                int coloana = s.nextInt();
                                s.nextLine();

                                index_zile.add(zi_index);
                                index_ecranizari.add(ec_index);
                                randuri.add(rand);
                                coloane.add(coloana);

                                System.out.print("Doriti sa mai rezervati? (d/n): ");
                                rasp = s.nextLine().charAt(0);
                            }

                        } else if (tip_bilet == 2) {
                            System.out.println("Alegeti o zi:");
                            for (int i = 0; i < zile.size(); i++)
                                System.out.println((i + 1) + ": " + zile.get(i).tostring());
                            int zi_index = s.nextInt() - 1;
                            s.nextLine();
                            index_zile.add(zi_index);
                            Zi zi_temp = zile.get(zi_index);

                            System.out.print("Doriti sa rezervati locuri la ecranizari? (d/n): ");
                            char rasp = s.nextLine().charAt(0);
                            doreste_rezervari = (rasp == 'd');

                            while (rasp=='d') {
                                zi_temp.afiseaza_ecranizari_pe_zi(filme, logat.getPermisiuneFilme());

                                System.out.print("Alegeti nr ecranizarii dorite: ");
                                int ec_index = s.nextInt() - 1;
                                s.nextLine();
                                zi_temp.get_ecranizarebyindex(ec_index).afisare_locuri();
                                System.out.print("Introduceti randul dorit: ");
                                int rand = s.nextInt();
                                s.nextLine();

                                System.out.print("Introduceti coloana dorita: ");
                                int coloana = s.nextInt();
                                s.nextLine();

                                index_ecranizari.add(ec_index);
                                randuri.add(rand);
                                coloane.add(coloana);

                                System.out.print("Doriti sa mai rezervati? (d/n): ");
                                rasp = s.nextLine().charAt(0);
                            }

                        } else if (tip_bilet == 1) {
                            System.out.println("Filmele disponibile sunt:");
                            for (int i = 0; i < filme.size(); i++)
                                if (logat.poateViziona(filme.get(i)))
                                    System.out.println((i + 1) + ": " + filme.get(i).tostring_film());

                            System.out.print("Introduceti nr filmului dorit: ");
                            int film_index = s.nextInt() - 1;
                            s.nextLine();
                            index_filme.add(film_index);

                            filme.get(film_index).afiseaza_ecranizari(zile);

                            System.out.print("Introduceti nr ecranizarii dorite: ");
                            int ec_index = s.nextInt();
                            s.nextLine();
                            index_ecranizari.add(ec_index);

                            System.out.print("Introduceti randul dorit: ");
                            int rand = s.nextInt();
                            s.nextLine();

                            System.out.print("Introduceti coloana dorita: ");
                            int coloana = s.nextInt();
                            s.nextLine();

                            randuri.add(rand);
                            coloane.add(coloana);

                            // determinăm și ziua în care se află ecranizarea
                            for (int i = 0; i < zile.size(); i++) {
                                if (zile.get(i).exista_ecranizare(filme.get(film_index).get_ecranizarebyindex(ec_index).getEcranizareID())) {
                                    index_zile.add(i);
                                    break;
                                }
                            }
                        }

                        ArrayList<Bilet> bilete_client = logat.cumparaBilet(
                                filme,
                                zile,
                                tip_bilet,
                                index_zile,
                                index_filme,
                                index_ecranizari,
                                randuri,
                                coloane,
                                doreste_rezervari
                        );

                        if (bilete_client != null)
                            bilete_temp.addAll(bilete_client);

                    } else {
                        System.out.println("Trebuie sa va logati. Alegeti l.");
                    }
                }


                /*else if (optiune == 'p')
                {
                    if (logat != null && !bilete_temp.isEmpty()) {
                        Bilet[] bileteArray = new Bilet[bilete_temp.size()];
                        bilete_temp.toArray(bileteArray);
                        Plata plata = logat.plateste(bileteArray);
                        if (plata != null && (plata.getStatusPlata() == StatusPlata.Procesata || plata.getStatusPlata() == StatusPlata.In_asteptare)) {
                            bilete.addAll(bilete_temp);
                            bilete_temp.clear();
                            plati.add(plata);
                        }
                    } else if (logat == null) {
                        System.out.println("Trebuie sa va logati. Alegeti l.");
                    } else {
                        System.out.println("Nu aveti bilete de platit.");
                    }
                }*/
                else if (optiune == 'p') {
                    if (logat != null && !bilete_temp.isEmpty()) {
                        Scanner s = new Scanner(System.in);

                        Bilet[] bileteArray = new Bilet[bilete_temp.size()];
                        bilete_temp.toArray(bileteArray);

                        float total = 0;
                        for (Bilet b : bileteArray) {
                            if (b.verifica_tip_bilet() == CategorieBilet.Bilet_zi) total += 100;
                            if (b.verifica_tip_bilet() == CategorieBilet.Bilet_festival) total += 250;
                            if (b.verifica_tip_bilet() == CategorieBilet.Bilet_film) total += 40;
                        }

                        System.out.println("Aveti de plata " + total + " lei. Continuati? (d/n): ");
                        char rasp = s.nextLine().charAt(0);
                        boolean confirma = (rasp == 'd');

                        MetodaPlata metoda = null;
                        if (confirma) {
                            int optiunePlata;
                            do {
                                System.out.println("Cum doriti sa efectuati plata? 1 - Cash, 2 - Card, 3 - Transfer:");
                                optiunePlata = s.nextInt();
                                s.nextLine();
                            } while (optiunePlata < 1 || optiunePlata > 3);

                            if (optiunePlata == 1) metoda = MetodaPlata.Cash;
                            else if (optiunePlata == 2) metoda = MetodaPlata.Card;
                            else if (optiunePlata == 3) metoda = MetodaPlata.Transfer;
                        }

                        Plata plata = logat.plateste(bileteArray, confirma, metoda);
                        if (plata != null && (plata.getStatusPlata() == StatusPlata.Procesata || plata.getStatusPlata() == StatusPlata.In_asteptare)) {
                            bilete.addAll(bilete_temp);
                            bilete_temp.clear();
                            plati.add(plata);
                        }

                    } else if (logat == null) {
                        System.out.println("Trebuie sa va logati. Alegeti l.");
                    } else {
                        System.out.println("Nu aveti bilete de platit.");
                    }
                }
                //afisam detaliile aferente contului
                else if (optiune == 'd')
                {
                    if(logat != null) {
                        System.out.println(logat.getDetaliiClient());
                    }
                    else
                        System.out.println("Trebuie sa va logati. Alegeti l.");
                }


                //modifica bilet
                else if (optiune == 'm')
                {
                    if(logat != null)
                        if (logat.getNrBilete()>0)  {
                            System.out.println("Alegeti cum vreti sa modificati biletul:");
                            System.out.println("r - rezerva loc");
                            System.out.println("a - anuleaza bilet");
                            System.out.println("c - anuleaza o rezervare din bilet");
                            optiune = scanner.next().charAt(0);
                            scanner.nextLine();
                            if(optiune == 'r')
                            {
                                //adauga rezervare intr-un bilet deja existent
                                logat.adaugaRezervareInBilet(filme,zile);

                            }
                            if (optiune == 'a')
                            {
                                //anuleaza un bilet
                                logat.anuleazaBilet(filme, zile);
                            }
                            if(optiune == 'c')
                            {
                                //anuleaza o rezervare dintr-un bilet de tip zi sau festival
                                logat.anuleazaRezervareBilet(filme,zile,sali);
                            }
                        }
                        else System.out.println("Nu aveti niciun bilet in cont. Pentru a cumpara apasati c.");
                    else System.out.println("Trebuie sa va logati. Alegeti l.");

                }
                else if(optiune == 'v'){
                    if(logat != null) {
                        logat.vizualizeazaBilete(filme,zile,sali);
                    }
                    else
                        System.out.println("Trebuie sa va logati. Alegeti l.");
                }
                else if(optiune == 'e')

                    break;
            }
            //optiunile de administrare
            else if(optiune == 'a')
            {

            }
            //optiuni de staf
            else if(optiune == 's')
            {
                System.out.println("Alegeti o optiune: ");
                System.out.println("l - logare");
                System.out.println("p - vizualizati profitul festivalului");
                System.out.println("o - adauga ore de lucru");
                System.out.println("b - aplica pentru bonus");
                System.out.println("v - vizualizeaza program");
                System.out.println("e - iesire");
                optiune = scanner.next().charAt(0);
                scanner.nextLine();
                if(optiune == 'l')
                {
                    String email;
                    boolean ok=false;
                    System.out.println("Introduceti emailul asociat contului de staff: ");
                    email= scanner.nextLine();
                    for (Staff ang : staff) {
                        if (ang.getEmail().equals(email)) {
                            logatStaff=ang;
                            ok=true;
                        }
                    }
                    if (!ok) {
                        System.out.println("Emailul invalid. Introduceti emailul valid sau creati-va cont de client");
                    }
                }
                else if(optiune == 'p')
                {
                    if(logatStaff != null)
                        logatStaff.vizualizareProfitFestival();
                    else System.out.println("trebuie sa va logati");
                }
                else if(optiune=='o')
                {
                    int ore=0;
                    if(logatStaff != null)
                    {
                        System.out.println("Introduceti nr de ore lucrate");
                        ore=scanner.nextInt();
                        scanner.nextLine();

                        logatStaff.adaugaOredeLucru(ore);
                    }
                    else System.out.println("Trebuie sa va logati");
                }
                else if(optiune=='v')
                {
                    if(logatStaff != null)
                        logatStaff.vizualizareProgram(sali);
                    else System.out.println("Trebuie sa va logati");
                }
                else if(optiune == 'b')
                {
                    if(logatStaff != null)
                        logatStaff.aplicaPentruBonus(m);
                    else System.out.println("Trebuie sa va logati");
                }
                else if(optiune=='e')
                {
                    break;
                }

            }
            else if(optiune == 'm')
            {
                System.out.println("Alegeti o optiune: ");
                System.out.println("l - logare");
                System.out.println("m - modifica salariu angajat");
                System.out.println("a - adauga o ecranizare");
                System.out.println("c - anuleaza o ecranizare");
                System.out.println("f - asigneaza alta functie pentru un angajat");
                System.out.println("z - asigneaza alta zona pentru un angajat");
                System.out.println("p - promoveaza un angajat");
                System.out.println("s - adauga supraveghetor pentru o ecranizare");
                System.out.println("e - iesire");
                optiune = scanner.next().charAt(0);
                scanner.nextLine();

                if(optiune == 'l'){
                    String email;
                    boolean ok=false;
                    System.out.println("Introduceti emailul asociat contului de manager: ");
                    email= scanner.nextLine();
                    if (m.getEmail().equals(email)) {
                        logatManager=m;
                        ok=true;
                    }
                    if (!ok) {
                        System.out.println("Emailul invalid. Introduceti emailul valid sau creati-va cont de client");
                    }
                }

                if(optiune == 'm'){
                    if(logatManager != null) {
                        Staff a;
                        int s = 0;
                        a = m.alegeAngajat(staff, sali);
                        System.out.println("Salariul actual al angajatului este " + a.getSalariu() + " lei.");
                        System.out.println("Scrieti noul salariu.");
                        s = scanner.nextInt();
                        scanner.nextLine();
                        m.modificareSalariuAngajat(a, s);
                        System.out.println("Salariul a fost modificat cu succes. Angajatul " + a.getNume() + " are acum salariul de " + a.getSalariu() + " lei.");
                    }
                    else System.out.println("Trebuie sa va logati");
                }

                if(optiune == 'a'){
                    if(logatManager != null) {
                        System.out.println("Alegeti filmul pentru care doriti sa adaugati o ecranizare.");
                        for (int i=0; i<filme.size(); i++)
                            System.out.println((i + 1) + ":" + filme.get(i).tostring_film());

                        System.out.println("Introduceti nr pt filmul dorit");
                        int filmdorit=scanner.nextInt() - 1;
                        scanner.nextLine();
                        System.out.println("Acestea sunt ecranizarile deja disponibile.");
                        filme.get(filmdorit).afiseaza_ecranizari(zile);
                        System.out.println("Alegeti o zi pentru noua ecranizare.");
                        for (int i = 0; i < zile.size(); i++)
                            System.out.println((i+1) +": "+ zile.get(i).tostring());
                        int zidorita = scanner.nextInt() - 1;
                        scanner.nextLine();

                        for (int i=0;i<sali.size();i++)
                            System.out.println((i+1)+" "+sali.get(i).getNume());
                        int sala_aleasa=scanner.nextInt() - 1;
                        scanner.nextLine();
                        int durata;
                        System.out.println("introduceti ora de inceput pentru ecranizare");
                        String ora_inceput=scanner.nextLine();
                        durata=filme.get(filmdorit).getDurata();
                        String ora_final=calculeazaOraFinala(ora_inceput,durata);
                        Ecranizare ecranizare_noua= new Ecranizare(ora_inceput,ora_final);
                        ecranizare_noua.adaugaEcranizare(filme.get(filmdorit),sali.get(sala_aleasa),zile.get(zidorita));
                        logatManager.adaugaEcranizare(ecranizare_noua,ecranizari);
                    }
                    else System.out.println("Trebuie sa va logati");

                }
                else if(optiune =='c')
                {
                    if (logatManager != null) {
                        System.out.println("Alegeti ziua in care vreti sa anulati ecranizarea");
                        for (int i = 0; i < zile.size(); i++)
                            System.out.println((i+1) +": "+ zile.get(i).tostring());
                        int zidorita = scanner.nextInt() - 1;
                        scanner.nextLine();
                        System.out.println("Alegeti ecranizarea pe care vreti sa o anulati");
                        zile.get(zidorita).afiseaza_ecranizari_pe_zi(filme,RestrictiiFilme.Interzis18ani);
                        int ecranizare_dorita = scanner.nextInt()-1;
                        scanner.nextLine();
                        logatManager.anuleazaEcranizare(zile.get(zidorita),ecranizari,ecranizare_dorita);
                    } else System.out.println("Trebuie sa va logati");
                }
                if(optiune == 'f') {
                    if (logatManager != null) {
                        Staff a;
                        a = m.alegeAngajat(staff, sali);
                        System.out.println("Alegeti o alta functie pentru angajat din lista:");
                        m.afiseazaFunctiiStaff();
                        int index = scanner.nextInt();
                        scanner.nextLine();
                        FunctieStaff functieNoua = FunctieStaff.values()[index];
                        m.reasignareFunctieAngajat(a, functieNoua);
                        System.out.println("Functia a fost modificata cu succes. Angajatul " + a.getNume() + " este acum " + a.getFunctie().name().replace('_', ' '));
                    } else System.out.println("Trebuie sa va logati");
                }
                if(optiune == 'z') {
                    if (logatManager != null) {
                        Staff a;
                        a = m.alegeAngajat(staff, sali);
                        System.out.println("Alegeti o alta zona pentru angajat din lista:");
                        m.afiseazaZone();
                        int index = scanner.nextInt();
                        scanner.nextLine();
                        Zone zonaNoua = Zone.values()[index];
                        m.reasignareZonaFestivalAngajat(a, zonaNoua);
                        System.out.println("Zona a fost modificata cu succes. Angajatul " + a.getNume() + " este asignat acum zonei " + a.getZonaFestival().name().replace('_', ' '));
                    }
                    else System.out.println("Trebuie sa va logati");
                }
                if(optiune =='p'){
                    if(logatManager != null) {
                        Staff a;
                        a = m.alegeAngajat(staff, sali);
                        m.promovare(a);
                        System.out.println("Angajatul" + a.getNume() + " este acum " + a.getFunctie().name().replace('_', ' '));
                    }
                    else System.out.println("Trebuie sa va logati");
                }
                if(optiune == 's'){
                    if(logatManager != null) {
                        Staff a;
                        a = m.alegeAngajat(staff, sali);
                        m.asociereSupraveghetoriEcranizari(a, ecranizari, zile, filme);
                    }
                    else System.out.println("Trebuie sa va logati");
                }
                else if(optiune == 'e')
                    break;
            }
            else if(optiune == 'e')
                break;
        }

    }

    private static String capitalize(String text) {
        if (text == null || text.isEmpty()) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

}

