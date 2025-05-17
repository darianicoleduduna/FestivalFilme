package clases;

import java.util.Objects;

public class Rezervare {
    private static int last_id=0;
    private int rezervareId;
    private int loc_ales_r;
    private int loc_ales_c;
    private StatusRezervare statusRezervare;

    public Rezervare(int r, int c) {
        this.rezervareId = ++last_id;
        statusRezervare = StatusRezervare.Activa;
        loc_ales_r = r;
        loc_ales_c = c;

    }


    public int getRand()
    {
        return loc_ales_r;
    }
    public int getColoana(){
        return loc_ales_c;
    }
    public int getRezervareId() {
        return rezervareId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Rezervare other = (Rezervare) obj;
        return loc_ales_r == other.loc_ales_r && loc_ales_c == other.loc_ales_c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(loc_ales_r, loc_ales_c);
    }
}
