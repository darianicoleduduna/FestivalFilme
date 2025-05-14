package clases;

public class Rezervare {
    private int rezervareId;
    private int loc_ales_r;
    private int loc_ales_c;
    private StatusRezervare statusRezervare;

    public Rezervare(int r, int c) {
        rezervareId = 0;
        statusRezervare = StatusRezervare.Activa;
        loc_ales_r = r;
        loc_ales_c = c;

    }
}
