package tests;

import classes.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientCumparaBiletTest {

    @Test
    void testCumparaBilet() {

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
        client.setareRestrictiiFilme();

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

        // Caz 2: Client prea mic pentru film restrictionat
        Client copil = new Client("Mara", "Pop", "copil@email.com", "0712345678", "10.01.2015"); // are   10 ani
        copil.setareRestrictiiFilme();
        ArrayList<Bilet> bileteRefuzate = copil.cumparaBilet(
                filme, zile,
                1,
                new ArrayList<>(), //zile
                new ArrayList<>(List.of(0)), //primul film
                new ArrayList<>(List.of(0)),//ecranizari
                new ArrayList<>(List.of(2)),//rand
                new ArrayList<>(List.of(1)),//col
                true
        );

        assertTrue(bileteRefuzate.isEmpty());

        //Caz 3: Bilet pentru zi fara rezervare
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

        // Caz 4: Bilet pentru zi cu o rezervare
        int locuriLibere3 = e2.getNrLocuriLibere();
        ArrayList<Bilet> bileteZi1Rez = client.cumparaBilet(
                filme, zile,
                2,
                new ArrayList<>(List.of(1)),
                new ArrayList<>(),
                new ArrayList<>(List.of(0)),
                new ArrayList<>(List.of(2)),
                new ArrayList<>(List.of(2)),
                true
        );

        assertEquals(1, bileteZi1Rez.size());
        Bilet b2Rez = bileteZi1Rez.get(0);
        assertEquals(CategorieBilet.Bilet_zi, b2Rez.verifica_tip_bilet());
        assertEquals(1, b2Rez.nrRezervari());
        assertEquals(locuriLibere3 - 1, e2.getNrLocuriLibere());

        // Caz 5: Bilet pentru zi cu mai multe rezervari
        int locuriLibere4 = e2.getNrLocuriLibere();
        ArrayList<Bilet> bileteZi2Rez = client.cumparaBilet(
                filme, zile,
                2,
                new ArrayList<>(List.of(1)),
                new ArrayList<>(),
                new ArrayList<>(List.of(0, 0)),
                new ArrayList<>(List.of(3, 4)),
                new ArrayList<>(List.of(3, 4)),
                true
        );

        assertEquals(1, bileteZi2Rez.size());
        Bilet b2MultiRez = bileteZi2Rez.get(0);
        assertEquals(CategorieBilet.Bilet_zi, b2MultiRez.verifica_tip_bilet());
        assertEquals(2, b2MultiRez.nrRezervari());
        assertEquals(locuriLibere4 - 2, e2.getNrLocuriLibere());

        //  Bilet pentru festival cu 2 rezervari
        int locuriLibere2 = e2.getNrLocuriLibere();
        ArrayList<Bilet> bileteFestival = client.cumparaBilet(
                filme, zile,
                3,
                new ArrayList<>(List.of(0, 1)),
                new ArrayList<>(),
                new ArrayList<>(List.of(0, 0)),
                new ArrayList<>(List.of(2, 2)),
                new ArrayList<>(List.of(2, 3)),
                true
        );

        assertEquals(1, bileteFestival.size());
        Bilet b3 = bileteFestival.get(0);
        assertEquals(CategorieBilet.Bilet_festival, b3.verifica_tip_bilet()); //verifica tip bilet ales
        assertEquals(2, b3.nrRezervari()); //nr de rezervari


        // Caz 7: două bilete film
        int locuriLibere5 = e2.getNrLocuriLibere();
        ArrayList<Bilet> bilet1 = client.cumparaBilet(
                filme, zile,
                1,
                new ArrayList<>(),
                new ArrayList<>(List.of(1)),
                new ArrayList<>(List.of(0)),
                new ArrayList<>(List.of(1)),
                new ArrayList<>(List.of(4)),
                true
        );
        ArrayList<Bilet> bilet2 = client.cumparaBilet(
                filme, zile,
                1,
                new ArrayList<>(),
                new ArrayList<>(List.of(1)),
                new ArrayList<>(List.of(0)),
                new ArrayList<>(List.of(1)),
                new ArrayList<>(List.of(3)),
                true
        );
        assertEquals(1, bilet1.size());
        assertEquals(1, bilet2.size());
        assertEquals(CategorieBilet.Bilet_film, bilet1.get(0).verifica_tip_bilet());
        assertEquals(CategorieBilet.Bilet_film, bilet2.get(0).verifica_tip_bilet());
        assertEquals(1, bilet1.get(0).nrRezervari());
        assertEquals(1, bilet2.get(0).nrRezervari());
        assertEquals(locuriLibere5 - 2, e2.getNrLocuriLibere());


        // Caz 8: bilet festival cu o singura rezervare
        int locuriLibere6 = e2.getNrLocuriLibere();
        ArrayList<Bilet> biletFestival1Rez = client.cumparaBilet(
                filme, zile,
                3,
                new ArrayList<>(List.of(1)),
                new ArrayList<>(),
                new ArrayList<>(List.of(0)),
                new ArrayList<>(List.of(1)),
                new ArrayList<>(List.of(1)),
                true
        );

        assertEquals(1, biletFestival1Rez.size());
        Bilet b6 = biletFestival1Rez.get(0);
        assertEquals(CategorieBilet.Bilet_festival, b6.verifica_tip_bilet());
        assertEquals(1, b6.nrRezervari());
        assertEquals(locuriLibere6 - 1, e2.getNrLocuriLibere());


        // Caz 10: rezervare pe un loc deja ocupat
        int locuriLibere8 = e1.getNrLocuriLibere();

        ArrayList<Bilet> biletValid = client.cumparaBilet(
                filme, zile,
                1,
                new ArrayList<>(),
                new ArrayList<>(List.of(0)),
                new ArrayList<>(List.of(0)),
                new ArrayList<>(List.of(3)),
                new ArrayList<>(List.of(2)),
                true
        );
        assertEquals(1, biletValid.size());
        assertEquals(1, biletValid.get(0).nrRezervari());
        assertEquals(locuriLibere8 - 1, e1.getNrLocuriLibere());

        ArrayList<Bilet> biletConflict = client.cumparaBilet(
                filme, zile,
                1,
                new ArrayList<>(),
                new ArrayList<>(List.of(0)),
                new ArrayList<>(List.of(0)),
                new ArrayList<>(List.of(3)),
                new ArrayList<>(List.of(2)),
                true
        );


        assertEquals(1, biletConflict.size());
        Bilet b8 = biletConflict.get(0);
        assertEquals(CategorieBilet.Bilet_film, b8.verifica_tip_bilet());
        assertTrue(b8.nrRezervari() == 0); // al doilea bilet nu a creat o rezervare


        //test Plata
        Bilet[] bileteArray = new Bilet[] { b1, b3 }; // b1 - film, b3 - festival
        Plata plata = client.plateste(bileteArray, true, MetodaPlata.Card);

        assertEquals(StatusPlata.In_asteptare, plata.getStatusPlata());
        assertEquals(40 + 250, plata.getSuma());
        assertEquals(MetodaPlata.Card, plata.getMetoda());
        assertEquals(2, plata.getBilete().length);
    }

}
