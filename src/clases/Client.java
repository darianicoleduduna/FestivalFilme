package clases;

import java.util.ArrayList;
import java.util.Calendar;

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

    public void cumparaBilet(int tip_bilet, String data, int ecranizareID)
    {
        if(tip_bilet == 3) //festival
        {
            Bilet b = new Bilet(null);
            bilete_disponibile.add(b);
        }
        else if(tip_bilet == 2)
        {
            Bilet b = new Bilet(data);
            bilete_disponibile.add(b);
        }
        else {
            Bilet b = new Bilet(data);
            bilete_disponibile.add(b);
        }
    }
}
