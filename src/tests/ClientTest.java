package tests;

import classes.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    //test Daria
    @Test
    void testCumparaBiletMaiMulteCazuri() {

        ArrayList<Film> filme = new ArrayList<>();
        Film inception = new Film("Inception", "2010", "Christopher Nolan", 148,
                RestrictiiFilme.Restrictionat16ani, CategoriiFilme.Thriller);
        Film frozen = new Film("Frozen", "2013", "Lee", 102,
                RestrictiiFilme.AudientaGenerala, CategoriiFilme.Animatie);
        filme.add(inception);
        filme.add(frozen);

        Sala sala1 = new Sala("Sala1", 5, 5);
        Sala sala2 = new Sala("Sala2", 5, 5);
        Zi zi1 = new Zi("17.04.2025");
        Zi zi2 = new Zi("18.04.2025");

        Ecranizare e1 = new Ecranizare("12:00", "14:28");
        e1.adaugaEcranizare(inception, sala1, zi1);
        zi1.adauga_ecranizare(e1);

        Ecranizare e2 = new Ecranizare("15:00", "16:42");
        e2.adaugaEcranizare(frozen, sala2, zi2);
        zi2.adauga_ecranizare(e2);

        ArrayList<Zi> zile = new ArrayList<>();
        zile.add(zi1);
        zile.add(zi2);

        Client client = new Client("Dan", "Nicu", "m@email.com", "0725353535", "10.02.2000");

        //Caz 1: Bilet pentru film cu rezervare
        int locuriLibere1 = e1.getNrLocuriLibere();
        ArrayList<Bilet> bileteFilm = client.cumparaBilet(
                filme, zile,
                1,
                new ArrayList<>(),
                new ArrayList<>(List.of(0)),
                new ArrayList<>(List.of(0)),
                new ArrayList<>(List.of(1)),
                new ArrayList<>(List.of(1)),
                true
        );

        assertEquals(1, bileteFilm.size());
        Bilet b1 = bileteFilm.get(0);
        assertEquals(CategorieBilet.Bilet_film, b1.verifica_tip_bilet());
        assertEquals(1, b1.nrRezervari());
        assertEquals(locuriLibere1 - 1, e1.getNrLocuriLibere());

        //Caz 2: Bilet pentru zi fara rezervare
        ArrayList<Bilet> bileteZi = client.cumparaBilet(
                filme, zile,
                2,
                new ArrayList<>(List.of(1)),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>(),
                false
        );

        assertEquals(1, bileteZi.size());
        Bilet b2 = bileteZi.get(0);
        assertEquals(CategorieBilet.Bilet_zi, b2.verifica_tip_bilet());
        assertEquals(0, b2.nrRezervari());

        // Caz 3: Bilet pentru festival cu 2 rezervari
        int locuriLibere2 = e2.getNrLocuriLibere();
        ArrayList<Bilet> bileteFestival = client.cumparaBilet(
                filme, zile,
                3,
                new ArrayList<>(List.of(0, 1)),
                new ArrayList<>(),
                new ArrayList<>(List.of(0, 0)),
                new ArrayList<>(List.of(2, 3)),
                new ArrayList<>(List.of(2, 3)),
                true
        );

        assertEquals(1, bileteFestival.size());
        Bilet b3 = bileteFestival.get(0);
        assertEquals(CategorieBilet.Bilet_festival, b3.verifica_tip_bilet());
        assertEquals(2, b3.nrRezervari());
        assertEquals(locuriLibere2 - 1, e2.getNrLocuriLibere()); // e2 a primit o rezervare
    }
}