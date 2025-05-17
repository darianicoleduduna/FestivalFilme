package clases;

import java.util.ArrayList;
import static clases.InterfataTest.profit_festival;

public class Staff extends Persoana {
    private static int last_id=0;
    private int staffID;
    private int salariu;
    private FunctieStaff functie;
    private Zone zonaFestival;
    private String oraInceputLucru;
    private int nrOreLucru;
    private ArrayList<Ecranizare> ecranizari;

    public Staff(String nume, String prenume, String email, String telefon, int staffID, int salariu, FunctieStaff functie, String oraInceputLucru, int nrOreLucru, Zone zonaFestival) {
        super(nume, prenume, email, telefon);
        this.staffID = ++last_id;
        this.salariu = salariu;
        this.functie = functie;
        this.nrOreLucru = nrOreLucru;
        this.zonaFestival = zonaFestival;
        this.ecranizari = new ArrayList<>();
    }

    //getters

    public FunctieStaff getFunctie() {
            return functie;
        }
    public int getSalariu() {
            return salariu;
        }
    public Zone getZonaFestival() {
            return zonaFestival;
        }
    public int getstaffID(){
            return this.staffID;
        }
    public int getNrOreLucru()
        {
            return this.nrOreLucru;
        }
    public int getNorma(){
            return this.norma;
        }

    public ArrayList<Ecranizare> getEcranizari(){

        return this.ecranizari;
        }
    public void setNorma(int norma) { this.norma = norma; }
    //functii speciale

    public String angajat_tostring(){
            return super.getNume() + " "+ super.getPrenume() +" functie: " + this.functie +" la zona: " +this.zonaFestival ;
        }
    public void vizualizareProgram(ArrayList<Sala> sali_disponibile)
    {
        System.out.println("Zona alocata este : "+ this.getZonaFestival());
        System.out.println("Numarul minim de ore este "+norma);
        String salaStr;
        if(this.functie==FunctieStaff.Supraveghetor_Sala){
            for (Ecranizare ecranizare : ecranizari){
                for(Staff angajat : ecranizare.getSupraveghetori()){
                    salaStr = "necunoscută";
                    if (this.staffID == angajat.getstaffID())

                    {
                        for (Sala sala : sali_disponibile) {
                            if (sala.getProgramEcranizari() != null &&
                                    sala.getProgramEcranizari().contains(ecranizare)) {
                                salaStr = sala.getNume();
                                break;
                            }
                        }
                    }
                    System.out.println("---------------------------------------");
                    System.out.println("Supraveghetor pentru "+ salaStr+ " " + ecranizare.tostring_ecranizare() );
                }
            }
        }
    }
    public void modificaSalariu(int salariu_nou){
        this.salariu = salariu_nou;
    }
    public void vizualizareProfitFestival(){
        if (profit_festival>0)
                System.out.println("Festivalul are pana in prezent " + profit_festival +".00  lei  profit");
        else
            System.out.println("Festivalul nu a iesit pana in acest moment in profit.");
    }
    public void schimbaFunctie(FunctieStaff functie_noua)
    {
        this.functie=functie_noua;
    }
    public void reasignareZonaFestival(Zone zonaFestival_noua)
    {
        this.zonaFestival=zonaFestival_noua;
    }
    public void aplicaPentruBonus(Manager m){
        m.bonus(this);
    }
    public void adaugaOredeLucru(int ore){
        this.nrOreLucru+=ore;
        System.out.println("Ati adaugat cu succces "+ore +" ore lucrate!");
        System.out.println("Ati lucrat in total "+ this.getNrOreLucru()+ " ore.");
    }
    public boolean valideazaManager() {
            if (this.functie == FunctieStaff.Manager_Festival)
                return true;
            else
                return false;
        }
}
