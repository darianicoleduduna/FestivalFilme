package clases;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InterfataTest {

    public static void main(String[] args) {
        Film [] films = new Film[5];
        films[0] = new Film("HP1", "2001", "Daria", 120, RestrictiiFilme.AcordParental13ani, CategoriiFilme.Fantasy);
        films[1] = new Film("HP2", "2002", "Daria", 120, RestrictiiFilme.AcordParental13ani, CategoriiFilme.Fantasy);
        films[2] = new Film("HP3", "2004", "Daria", 120, RestrictiiFilme.AcordParental13ani, CategoriiFilme.Fantasy);
        films[3] = new Film("HP4", "2005", "Daria", 120, RestrictiiFilme.AcordParental13ani, CategoriiFilme.Fantasy);
        films[4] = new Film("HP5", "2007", "Daria", 120, RestrictiiFilme.AcordParental13ani, CategoriiFilme.Fantasy);

        Sala [] sals = new Sala[5];
        sals[0] = new Sala("Sala1", 10, 20);
        sals[1] = new Sala("Sala2", 20, 30);
        sals[2] = new Sala("Sala3", 30, 40);
        sals[3] = new Sala("Sala4", 40, 50);
        sals[4] = new Sala("Sala5", 50, 60);

        Ecranizare e1 = new Ecranizare("13:00", "14:30", "13.5.2025");
        e1.adaugaEcranizare(films[0], sals[0]);
        e1.adaugaEcranizare(films[1], sals[1]);




        Ecranizare e2 = new Ecranizare("14:15", "16:00", "13.5.2025");
        //System.out.println(s1.alocareSala(e2));
        Ecranizare e3 = new Ecranizare("12:15", "13:15", "13.5.2025");
        //System.out.println(s1.alocareSala(e3));
        Ecranizare e4 = new Ecranizare("13:15", "14:15", "13.5.2025");
        //System.out.println(s1.alocareSala(e4));
        Ecranizare e5 = new Ecranizare("12:30", "14:45", "13.5.2025");
        //System.out.println(s1.alocareSala(e5));
        Ecranizare e6 = new Ecranizare("11:30", "12:45", "13.5.2025");
        //System.out.println(s1.alocareSala(e6));
        Ecranizare e7 = new Ecranizare("14:45", "16:45", "13.5.2025");
        //System.out.println(s1.alocareSala(e7));



        Client c1 = new Client("Duduna", "Daria", "c", "0745682515", "17.04.2003");
        Client c2 = new Client("Enache", "Vlad", "a", "0745682123", "02.07.2003");
        c1.setareRestrictiiFilme();
        System.out.println(c1.determinareVarsta() + " " +c1.getPermisiuneFilme() + " " + c1.getClientID() + " " + c2.getClientID());


        Sala s1 = new Sala("Daria", 17, 12);


        ArrayList<Client> clienti = new ArrayList<Client>();
        ArrayList<Staff> staff = new ArrayList<Staff>();
        ArrayList<Bilet> bilete = new ArrayList<Bilet>();
        ArrayList<Ecranizare> ecranizari = new ArrayList<Ecranizare>();
        ArrayList<Film> filme = new ArrayList<Film>();
        ArrayList<Sala> sali = new ArrayList<Sala>();
        ArrayList<Plata> plati = new ArrayList<Plata>();
        ArrayList<Rezervare> rezervari = new ArrayList<Rezervare>();
        ArrayList<Zi> zile = new ArrayList<Zi>();

        char optiune;
        String text;
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            System.out.println("Alegeti o optiune: ");
            System.out.println("c - optiuni de client");
            System.out.println("a - optiuni de administrare");
            System.out.println("s - optiuni de staff");
            System.out.println("m - optiuni de manager");
            System.out.println("e - iesire");
            optiune = scanner.next().charAt(0);
            if(optiune == 'c')
            {
                System.out.println("Alegeti o optiune de client: ");
                System.out.println("a - adauga client");
                System.out.println("d - detalii clienti");
                System.out.println("c - cumpara bilet");
                System.out.println("r - rezerva loc");
                System.out.println("e - iesire");
                optiune = scanner.next().charAt(0);
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
                    clienti.add(c);
                    System.out.println("Client creat cu succes: " + c);
                }
                else if (optiune == 'c')
                {

                }
                else if (optiune == 'd')
                {

                }
                else if (optiune == 'r')
                {

                }
                else if(optiune == 'e')
                    break;
            }
            else if(optiune == 'a')
            {

            }
            else if(optiune == 's')
            {

            }
            else if(optiune == 'm')
            {

            }
            else if(optiune == 'e')
                break;

            System.out.println("c - adauga un client");
            System.out.println("s - adauga o sala");
            System.out.println("e - adauga o ecranizare");
            System.out.println("f - adauga un film");
            System.out.println("Alegeti o optiune: ");
        }

    }

    private static String capitalize(String text) {
        if (text == null || text.isEmpty()) return text;
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }

}

