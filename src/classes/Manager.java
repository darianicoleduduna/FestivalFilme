package classes;

import java.util.ArrayList;
import java.util.Scanner;

public class Manager extends Staff {


    public Manager(String nume, String prenume, String email, String telefon, int salariu, FunctieStaff functie, int nrOreLucru, Zone zonaFestival, int norma)
    {
        super(nume, prenume, email, telefon,  salariu, functie,  nrOreLucru, zonaFestival, norma);
    }

    public boolean aprobareBonus(Staff angajat)
    {
        return angajat.getNrOreLucru() > angajat.getNorma();
    }

    public void bonus(Staff angajat){
        if(aprobareBonus(angajat)){
            modificareSalariuAngajat(angajat, (angajat.getSalariu() + 100*(angajat.getNrOreLucru() - angajat.getNorma())));
            angajat.setNorma(angajat.getNrOreLucru());
            System.out.println("Bonusul a fost aprobat.");
        }
        else System.out.println("Bonusul nu a fost aprobat.");
    }

    public void modificareSalariuAngajat(Staff angajat, int salariuNou){
        angajat.modificaSalariu(salariuNou);
    }

    public void reasignareFunctieAngajat(Staff angajat, FunctieStaff functie){
        angajat.schimbaFunctie(functie);
    }

    public void reasignareZonaFestivalAngajat(Staff angajat, Zone zonaFestival){
        angajat.reasignareZonaFestival(zonaFestival);
    }

    public void promovare(Staff angajat){
        if(angajat.getFunctie()!=FunctieStaff.Director)
        {
            modificareSalariuAngajat(angajat,10000);
            angajat.schimbaFunctie(FunctieStaff.Director);
        }
        else
            System.out.println("Angajatul nu este eligibil pentru promotie");
    }

    public Staff alegeAngajat(ArrayList<Staff> angajati, ArrayList<Sala> sali_disponibile){
        int indice;
        Scanner sc = new Scanner(System.in);
        System.out.println("Alegeti un angajat: ");
        int i = 1;
        for(Staff a : angajati){
            System.out.println(i + " "+ a.angajat_tostring());
            a.vizualizareProgram(sali_disponibile);
            i++;
        }
        indice = sc.nextInt() -1 ;
        sc.nextLine();
        return angajati.get(indice);
    }

    public void afiseazaFunctiiStaff() {
        for (FunctieStaff functie : FunctieStaff.values()) {
            System.out.println(functie.ordinal() + " - " + functie.name().replace('_', ' '));
        }
    }
    
    public void afiseazaZone(){
        for (Zone zona : Zone.values()) {
            System.out.println(zona.ordinal() + " - " + zona.name().replace('_', ' '));
        }
    }

    public void asociereSupraveghetoriEcranizari(Staff angajat, ArrayList<Ecranizare> ecranizari , ArrayList<Zi>  zile, ArrayList<Film> filme_disponibile){
        System.out.println("Alegeti ziua dorita :");
        Scanner s = new Scanner(System.in);
        int i = 1;
        for (Zi zi : zile) {
            System.out.println(i + " : " + zi.tostring());
            i++;
        }
        int zidorita = s.nextInt() - 1;
        s.nextLine();
        Zi zi_temp = zile.get(zidorita);
        zi_temp.afiseaza_ecranizari_pe_zi(filme_disponibile,RestrictiiFilme.Interzis18ani);
        System.out.println("Alegeti nr ecranizarii dorite: ");
        int ind = s.nextInt() - 1;
        s.nextLine();
        schimbaFunctie(FunctieStaff.Supraveghetor_Sala);
        reasignareZonaFestivalAngajat(angajat, Zone.Zona_Sali);
        zile.get(zidorita).getEcranizari().get(ind).adaugaSupraveghetor(angajat);
        angajat.getEcranizari().add(zile.get(zidorita).getEcranizari().get(ind));
        System.out.println("Supraveghetorul a fost asignat cu succes.");

    }
    public void adaugaEcranizare(Ecranizare e, ArrayList<Ecranizare> ecranizari)
    {
        ecranizari.add(e);
        System.out.println("Ati adaugat ecranizarea cu succes");
    }
    public void anuleazaEcranizare(Zi ziua_ecranizarii, ArrayList<Ecranizare> ecranizari, int index)
    {
        Ecranizare es =ziua_ecranizarii.getEcranizari().get(index);
        ziua_ecranizarii.anuleazaEcranizare(es);
        ecranizari.remove(es);
        System.out.println("Ecranizarea a fost ștearsă complet din zi și din lista globală.");

    }
}
