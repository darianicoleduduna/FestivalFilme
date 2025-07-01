package tests;

import static org.junit.jupiter.api.Assertions.*;

import classes.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ClientRezervareTest {

    private Client client;
    private ArrayList<Film> filme;
    private ArrayList<Zi> zile;

    @BeforeEach
    public void setUp() {
        client = new Client("Maria", "Popovici", "maria.popovici@gmail.com", "07450111333", "12.03.2003");
        client.setareRestrictiiFilme();

        filme = new ArrayList<>();
        Film inception = new Film("Inception", "2010", "Christopher Nolan", 148,
                RestrictiiFilme.Restrictionat16ani, CategoriiFilme.Thriller);
        Film frozen = new Film("Frozen", "2013", "Lee", 102,
                RestrictiiFilme.AudientaGenerala, CategoriiFilme.Animatie);
        filme.add(inception);
        filme.add(frozen);

        Sala sala1 = new Sala("Sala1", 10, 10);
        Sala sala2 = new Sala("Sala2", 10, 10);
        Zi zi1 = new Zi("17.04.2025");
        Zi zi2 = new Zi("18.04.2025");

        Ecranizare e1 = new Ecranizare("12:00", "14:28");
        e1.adaugaEcranizare(inception, sala1, zi1);
        zi1.adauga_ecranizare(e1);

        Ecranizare e2 = new Ecranizare("15:00", "16:42");
        e2.adaugaEcranizare(frozen, sala2, zi2);
        zi2.adauga_ecranizare(e2);

        zile = new ArrayList<>();
        zile.add(zi1);
        zile.add(zi2);
    }

    @Test
    public void testAdaugaRezervareInBiletFilm() {
        // 1. Cumpăr biletul
        ArrayList<Bilet> bilete = client.cumparaBilet(
                filme, zile, 2,
                new ArrayList<>(List.of(0)),
                new ArrayList<>(List.of(0)),
                new ArrayList<>(List.of(0)),
                new ArrayList<>(List.of(3)),
                new ArrayList<>(List.of(4)),
                false
        );

        assertFalse(bilete.isEmpty(), "Lista de bilete nu trebuie să fie vidă");

        Bilet biletFilm = bilete.get(0);

        // 2. Adaug rezervare nouă prin client
        int randNou = 5;
        int coloanaNoua = 6;
        String mesaj = client.adaugaRezervareInBilet(filme, zile, 0, null, 0, randNou, coloanaNoua);

        assertEquals("Rezervarea a fost adăugată cu succes.", mesaj);

        boolean existaRezervarea = false;
        Rezervare r = biletFilm.getRezervari()[0];
        if (r.getRand() == randNou-1 && r.getColoana() == coloanaNoua-1) {
            existaRezervarea = true;
            }

        assertTrue(existaRezervarea, "Biletul trebuie sa contina rezervarea adaugata");
    }

    @Test
    public void testAnuleazaBiletCuSucces() {
        // 1. Cumpăr biletul
        ArrayList<Bilet> bilete = client.cumparaBilet(
                filme, zile, 2,
                new ArrayList<>(List.of(0)),
                new ArrayList<>(List.of(0)),
                new ArrayList<>(List.of(0)),
                new ArrayList<>(List.of(3)),
                new ArrayList<>(List.of(4)),
                false
        );

        assertFalse(bilete.isEmpty(), "Lista de bilete nu trebuie să fie vidă");

        Bilet bilet = bilete.get(0);

        // 2. Adaug rezervare pe bilet
        String mesajRez = client.adaugaRezervareInBilet(filme, zile, 0, null, 0, 5, 6);
        assertEquals("Rezervarea a fost adăugată cu succes.", mesajRez);

        // 3. Verific că rezervarea există în bilet
        Rezervare[] rezervariInainte = bilet.getRezervari();
        assertNotNull(rezervariInainte[0]);
        assertEquals(4, rezervariInainte[0].getRand()); // 0-based
        assertEquals(5, rezervariInainte[0].getColoana());

        // 4. Anulez biletul
        String mesajAnulare = client.anuleazaBilet(filme, zile, 0);
        assertEquals("Biletul a fost anulat cu succes.", mesajAnulare);

        // 5. Verific că biletul nu mai este în lista clientului
        assertTrue(client.getBilete_disponibile().isEmpty(), "Biletul ar trebui eliminat după anulare");

        // 6. Verific că rezervarea a fost eliminată din ecranizare
        Zi zi = zile.get(0);
        Ecranizare ecranizare = zi.getEcranizari().get(0);
        assertFalse(ecranizare.getRezervari().contains(rezervariInainte[0]), "Rezervarea ar trebui eliminată din ecranizare");
        assertFalse(ecranizare.getBileteCumparate().contains(bilet), "Biletul ar trebui eliminat din bilete cumpărate");
    }

    @Test
    public void testAnuleazaRezervareBiletCuSucces() {
        // 1. Cumpără bilet
        ArrayList<Bilet> bilete = client.cumparaBilet(
                filme, zile, 2,
                new ArrayList<>(List.of(0)),
                new ArrayList<>(List.of(0)),
                new ArrayList<>(List.of(0)),
                new ArrayList<>(List.of(3)),
                new ArrayList<>(List.of(4)),
                false
        );

        assertFalse(bilete.isEmpty(), "Trebuie să existe un bilet cumpărat");
        Bilet bilet = bilete.get(0);

        // 2. Adaugă o rezervare
        String msgRez = client.adaugaRezervareInBilet(filme, zile, 0, null, 0, 5, 6);
        assertEquals("Rezervarea a fost adăugată cu succes.", msgRez);

        Rezervare[] rezervari = bilet.getRezervari();
        Rezervare rez = rezervari[0];
        assertNotNull(rez, "Rezervarea trebuie să fie prezentă în bilet");

        // 3. Confirmăm că rezervarea este în ecranizare
        Ecranizare ecranizare = zile.get(0).getEcranizari().get(0);
        assertTrue(ecranizare.getRezervari().contains(rez), "Rezervarea trebuie să fie în ecranizare");

        // 4. Apelăm metoda logică fără sali
        ArrayList<Sala> sali = new ArrayList<>(); // transmis dar nefolosit
        String rezultat = client.anuleazaRezervareBilet(filme, zile, sali, 0, 0);
        assertEquals("Rezervarea a fost anulată cu succes.", rezultat);

        // 5. Verificăm că rezervarea a fost ștearsă din bilet
        boolean maiExista = false;
        for (Rezervare r : bilet.getRezervari()) {
            if (r != null && r.getRezervareId() == rez.getRezervareId()) {
                maiExista = true;
                break;
            }
        }
        assertFalse(maiExista, "Rezervarea nu trebuie să mai fie prezentă în bilet");

        // 6. Verificăm că a fost eliminată și din ecranizare
        assertFalse(ecranizare.getRezervari().contains(rez), "Rezervarea trebuie eliminată din ecranizare");
    }
}
